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
;
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
    public static final int CAMERA_ACTION_CODE=1;
    public static final int IMAGE_REQUEST_CODE=2;
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
                //打開相簿
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //選擇相片後接回傳
                startActivityForResult(intent, 1);

            }

        });

        return v;
    }
}


