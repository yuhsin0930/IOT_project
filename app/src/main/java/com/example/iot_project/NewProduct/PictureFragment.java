package com.example.iot_project.NewProduct;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iot_project.DBHelper;
import com.example.iot_project.R;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private byte[] goodsPicture;
    private ImageView imageViewPic;

    public PictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PictureFragment newInstance(String param1, String param2) {
        PictureFragment fragment = new PictureFragment();
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
        View v = inflater.inflate(R.layout.fragment_picture, container, false);
        NewProductActivity newProductActivity = (NewProductActivity)getActivity();

        imageViewPic = (ImageView)v.findViewById(R.id.imageView_newproductPicture);

        DBHelper dbHelper = new DBHelper(newProductActivity);
        SQLiteDatabase picdataBase = dbHelper.getWritableDatabase();
        Cursor picCursor = picdataBase.rawQuery(" SELECT * FROM goodsPic WHERE fragPic = '"+mParam2+"';", null);
        if(picCursor.getCount()>0) {
            picCursor.moveToFirst();
            goodsPicture = picCursor.getBlob(picCursor.getColumnIndexOrThrow("goodsPicture"));
        }
        Bitmap bm = BitmapFactory.decodeByteArray(goodsPicture,0,goodsPicture.length);
        imageViewPic.setImageBitmap(bm);
        picdataBase.close();
        dbHelper.close();
        return v;
    }
}