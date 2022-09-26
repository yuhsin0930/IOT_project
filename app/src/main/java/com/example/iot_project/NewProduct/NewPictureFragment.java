package com.example.iot_project.NewProduct;

import static android.app.Activity.RESULT_OK;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iot_project.R;


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
                //動態申請許可權
                if (ContextCompat.checkSelfPermission(newProductActivity, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(newProductActivity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    //執行啟動相簿的方法
                    openAlbum();
                }
            }
        });
        //https://developer.android.com/training/data-storage/shared/media?hl=zh-tw
        return v;
    }
    //獲取許可權的結果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED) openAlbum();
            else Toast.makeText(newProductActivity,"你拒絕了",Toast.LENGTH_SHORT).show();
        }
    }

    //啟動相簿的方法
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    //呼叫相簿網址
    //https://www.it145.com/9/72906.html
}


