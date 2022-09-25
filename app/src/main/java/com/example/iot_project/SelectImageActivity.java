package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.firebase.database.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectImageActivity extends AppCompatActivity {


    private static final int SELECT_FROM_CAMERA = 100;
    private String currentPhotoPath;
    private static final int SELECT_FROM_ALBUM = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        int imageWhere = getIntent().getIntExtra("imageFromWhere", 0);
        switch (imageWhere) {
            case SELECT_FROM_CAMERA:  // 拍攝新圖片
                selectImageFromCamera();
                break;
            case SELECT_FROM_ALBUM:  // 從相簿中選取
                openAlbum();
                break;
        }
    }
    // 呼叫相機
    private void selectImageFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  // 初始化開啟相機的意圖
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.encryption.fileProvider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, SELECT_FROM_CAMERA);
            }
        }
    }


    // 建立圖片檔案
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  // 檔名以時間命名
        String imageFileName = "JPEG_" + timeStamp + "_";  // 完善檔名
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);  // 獲取檔案儲存路徑
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image; // 返回檔案
    }

    // 詢問使用者是否授權
    private void selectImageFromAlbum() {
        // 詢問使用者是否授予許可權
        if (ContextCompat.checkSelfPermission(SelectImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SelectImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);  // 請求許可權
        } else {  // 已經授予許可權
            openAlbum();  // 呼叫相簿
        }

    }

    // 判斷使用者是否授予許可權
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // 使用者給予許可權
                openAlbum();
            } else {
                Toast.makeText(SelectImageActivity.this, "請授予讀取相簿許可權!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 從相簿選取圖片處理
    private String handleImage(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        return imagePath;  // 使用者選擇圖片的真實地址
    }


    // 獲得相簿中圖片的真實路徑
    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(externalContentUri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    // 開啟相簿
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FROM_ALBUM); // 跳轉意圖
    }

    //Activity返回結果處理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imagePath = null;
        if (resultCode == RESULT_OK) {  // 返回碼為真
            if (requestCode == SELECT_FROM_ALBUM) {
                assert data != null;
                currentPhotoPath = handleImage(data);
            }
            returnImagePath(currentPhotoPath);
        }else {
            finish();  // 如果返回值不是RESULT_OK, 這種情況可能是使用者放棄選擇圖片,此時直接結束該activity否則會顯示當前activity
        }
    }
    private void returnImagePath(String imgPath) {
        Intent intent = new Intent();
        intent.putExtra("imagePath", imgPath);
        setResult(RESULT_OK, intent);
        finish();
    }
}