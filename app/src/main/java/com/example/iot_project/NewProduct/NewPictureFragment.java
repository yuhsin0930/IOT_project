package com.example.iot_project.NewProduct;

import static android.app.Activity.RESULT_OK;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.R;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPictureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    private ImageView imageView_newPicture;
    private NewProductActivity newProductActivity;
    public static final int PICK_PHOTO = 102;
    private Uri imageUri;

    public NewPictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newPictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPictureFragment newInstance(String param1, String param2) {
        NewPictureFragment fragment = new NewPictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_picture, container, false);
        newProductActivity = (NewProductActivity) getActivity();
        imageView_newPicture = (ImageView) v.findViewById(R.id.ImageView_newPicture);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView_newPicture.setImageBitmap(bitmap);
                }
            }
        });
        Dialog newPictureDlg = new Dialog(newProductActivity);
        newPictureDlg.setContentView(R.layout.new_picture_dialog);
        newPictureDlg.show();
        newPictureDlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView textViewCamara = (TextView) newPictureDlg.findViewById(R.id.textView_camara);
        TextView textViewAlbum = (TextView) newPictureDlg.findViewById(R.id.textView_album);
        textViewCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPictureDlg.dismiss();
                Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                if (intent.resolveActivity(newProductActivity.getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(newProductActivity, "no app support", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textViewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPictureDlg.dismiss();

                //動態申請獲取存取 讀寫磁碟的許可權
                if (ContextCompat.checkSelfPermission(newProductActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(newProductActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                } else {
                    //開啟相簿
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO); // 開啟相簿
                }
            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case PICK_PHOTO:
                if (resultCode == RESULT_OK) { // 判斷手機系統版本號
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系統使用這個方法處理圖片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系統使用這個方法處理圖片
                        handleImageBeforeKitKat(data);
                    }
                }

                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(newProductActivity, uri)) {
            // 如果是document型別的Uri，則通過document id處理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                // 解析出數位格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: //downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content型別的Uri，則使用普通方式處理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file型別的Uri，直接獲取圖片路徑即可
            imagePath = uri.getPath();
        }
        // 根據圖片路徑顯示圖片
        displayImage(imagePath);
    }

    /**
     * android 4.4以前的處理方式
     * @param data
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通過Uri和selection來獲取真實的圖片路徑
        Cursor cursor = newProductActivity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView_newPicture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(newProductActivity, "獲取相簿圖片失敗", Toast.LENGTH_SHORT).show();
        }
    }

}


