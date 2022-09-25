package com.example.iot_project.NewProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iot_project.DBHelper;
import com.example.iot_project.MyProduct.MyProductActivity;
import com.example.iot_project.R;

import java.util.HashMap;
import java.util.Map;

public class NewProductActivity extends AppCompatActivity {

    private EditText editTextNewProduct_Name;
    private TextView textViewNewProduct_describe;
    private TextView textViewNewProduct_classification;
    private TextView textViewNewProduct_setPrice;
    private TextView textViewNewProduct_shippiingFee;
    private Button button_newProduct_launch;
    private TextView textViewNewProduct_NameLength;
    private TextView textViewNewProduct_describeLength;
    private String NewProductNameLength;
    private String NewProductName;
    private TextView textViewNewProduct_Inventory;
    private int inventory=0;
    private TextView textViewNewProduct_newPicture;
    private Uri outputFileUri;
    private static final int SELECT_FROM_CAMERA = 100;
    private static final int SELECT_FROM_ALBUM = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        SharedPreferences sp = getSharedPreferences("newProduct",MODE_PRIVATE);
        textViewNewProduct_NameLength = (TextView)findViewById(R.id.textView_newProduct_NameLength);
        textViewNewProduct_describeLength = (TextView)findViewById(R.id.textView_newProduct_describeLength);
        textViewNewProduct_describe = (TextView)findViewById(R.id.textView_newProduct_Describe);
        textViewNewProduct_classification = (TextView)findViewById(R.id.textView_newProduct_Classification);
        textViewNewProduct_setPrice = (TextView)findViewById(R.id.textView_newProduct_SetPrice);
        textViewNewProduct_shippiingFee = (TextView)findViewById(R.id.textView_newProduct_shippiingFee);
        textViewNewProduct_Inventory = (TextView)findViewById(R.id.textView_newProductNum);
        textViewNewProduct_newPicture = (TextView)findViewById(R.id.textView_newPicture);
        editTextNewProduct_Name = (EditText)findViewById(R.id.editTextText_newProduct_Name);
        //------------------------------------------------------------------------------------------
        textViewNewProduct_newPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newPictureDlg = new Dialog(NewProductActivity.this);
                newPictureDlg.setContentView(R.layout.new_picture_dialog);
                newPictureDlg.show();
                newPictureDlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                TextView textViewCamara = (TextView) newPictureDlg.findViewById(R.id.textView_camara);
                TextView textViewAlbum = (TextView) newPictureDlg.findViewById(R.id.textView_album);
                textViewCamara.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NewProductActivity.this, SelectImageActivity.class);
                        intent.putExtra("imageFromWhere", SELECT_FROM_CAMERA);
                        startActivityForResult(intent, 1);
                    }
                });
                textViewAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NewProductActivity.this, SelectImageActivity.class);
                        intent.putExtra("imageFromWhere", SELECT_FROM_ALBUM);
                        startActivityForResult(intent, 1);
                    }
                });
            }

        });
        //------------------------------------------------------------------------------------------
        NewProductNameLength=sp.getString("productNameLength","0");
        String productName = sp.getString("productName", "");
        editTextNewProduct_Name.setText(productName);
        editTextNewProduct_Name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        editTextNewProduct_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                NewProductName =  s.toString();
                NewProductNameLength = String.valueOf(NewProductName.length());
                sp.edit().putString("productName",NewProductName).commit();
            }
        });
        textViewNewProduct_NameLength.setText(NewProductNameLength);

        //------------------------------------------------------------------------------------------
        String  describeLength = sp.getString("DescribeWordNum","0");
        textViewNewProduct_describe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, ProductDescribeActivity.class);
                startActivity(intent);
            }
        });
        textViewNewProduct_describeLength.setText(describeLength);

        //------------------------------------------------------------------------------------------
        textViewNewProduct_classification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, NewProductClassificationActivity.class);
                startActivity(intent);
            }
        });
        //-----------------------------------------------------------------------------------------
        textViewNewProduct_setPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, SetPriceActivity.class);
                startActivity(intent);
            }
        });

        //------------------------------------------------------------------------------------------
        textViewNewProduct_shippiingFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProductActivity.this, ShippingFeeActivity.class);
                startActivity(intent);
            }
        });


        //------------------------------------------------------------------------------------------

        button_newProduct_launch = (Button) findViewById(R.id.button_newProduct_launch);
        button_newProduct_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = sp.getString("productName","");
                String describe = sp.getString("productDescribe","");
                int pLength = sp.getInt("productLength",0);
                int pWidth = sp.getInt("productWidth",0);
                int pHeight = sp.getInt("productHeight",0);
                int shippingSeven = sp.getInt("shippingFlag_seven",0);
                int shippingFamilyMart =  sp.getInt("shippingFlag_familyMart",0);
                int shippingPostOffice =  sp.getInt("shippingFlag_blackCat",0);
                int shippingBlackCat =  sp.getInt("shippingFlag_postOffice",0);
                int shippiingSevenFee =  sp.getInt("shippingFee_seven",0);
                int shippingFamilyMartFee =  sp.getInt("shippingFee_familyMart",0);
                int shippingPostOfficeFee =  sp.getInt("shippingFee_postOffice",0);
                int shippingBlackCatFee =  sp.getInt("shippingFee_blackCat",0);
                DBHelper dbHelper = new DBHelper(NewProductActivity.this);
                SQLiteDatabase newProductDatabase = dbHelper.getWritableDatabase();


                //----------------------------------------------------------------------------------
                //    資料表名稱 : goods
                //    欄位中文名稱            欄位名稱          Cursor Index
                //    * 商品_id            goods_id             0
                //    # 賣家_id            seller_id            1
                //      商品名稱            gName                2
                //      描述               info                 3
                //      包裹長度            packageLength        4
                //      包裹寬度            packageWidth         5
                //      包裹高度            packageHeight        6
                //      庫存量              inventory            7
                // *     售出數量           soldQuantity         8
                //      運送方法7-11        seven                9
                //      運送方法全家         familyMart          10
                //      運送方法郵局         postOffice          11
                //      運送方法黑貓         blackCat            12
                //      運費7-11            sevenFee            13
                //      運費全家            familyMartFee        14
                //      運費郵局            postOfficeFee        15
                //      運費黑貓            blackCatFee          16
                //      商品狀態            gState               17
                //      創建時間            createTime           18
                //----------------------------------------------------------------------------------
                ContentValues cv = new ContentValues();
                cv.put("gName",name);
                cv.put("info",describe);
                cv.put("packageLength",pLength);
                cv.put("packageWidth",pWidth);
                cv.put("packageHeight",pHeight);
                cv.put("seven",shippingSeven);
                cv.put("familyMart",shippingFamilyMart);
                cv.put("postOffice",shippingPostOffice);
                cv.put("blackCat",shippingBlackCat);
                cv.put("sevenFee",shippiingSevenFee);
                cv.put("familyMartFee",shippingFamilyMartFee);
                cv.put("postOfficeFee",shippingPostOfficeFee);
                cv.put("blackCatFee",shippingBlackCatFee);
                cv.put("gState","審核中");
                long id =newProductDatabase.insert("goods",null,cv);
                Log.d("main","id = "+id);
                Cursor goodCursor= newProductDatabase.rawQuery("select * from goods;", null);
                Map<String, Object> GoodInfoMap = new HashMap<>();
                goodCursor.moveToFirst();
                while(!goodCursor.isAfterLast()) {
                    int goods_id = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("goods_id"));
                    String gName = goodCursor.getString(goodCursor.getColumnIndexOrThrow("gName"));
                    String info = goodCursor.getString(goodCursor.getColumnIndexOrThrow("info"));
                    int packageLength = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("packageWidth"));
                    int packageWidth = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("packageWidth"));
                    int packageHeight = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("packageHeight"));
                    int seven = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("seven"));
                    int familyMart = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("familyMart"));
                    int postOffice = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("postOffice"));
                    int blackCat = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("blackCat"));
                    int sevenFee = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("sevenFee"));
                    int familyMartFee = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("familyMartFee"));
                    int postOfficeFee = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("postOfficeFee"));
                    int blackCatFee = goodCursor.getInt(goodCursor.getColumnIndexOrThrow("blackCatFee"));
                    String gState = goodCursor.getString(goodCursor.getColumnIndexOrThrow("gState"));
                    String createTime = goodCursor.getString(goodCursor.getColumnIndexOrThrow("createTime"));

                    GoodInfoMap.put("goods_id",goods_id);
                    GoodInfoMap.put("gName",gName);
                    GoodInfoMap.put("info",info);
                    GoodInfoMap.put("packageLength",packageLength);
                    GoodInfoMap.put("packageWidth",packageWidth);
                    GoodInfoMap.put("packageHeight",packageHeight);
                    GoodInfoMap.put("seven",seven);
                    GoodInfoMap.put("familyMart",familyMart);
                    GoodInfoMap.put("postOffice",postOffice);
                    GoodInfoMap.put("blackCat",blackCat);
                    GoodInfoMap.put("sevenFee",sevenFee);
                    GoodInfoMap.put("familyMartFee",familyMartFee);
                    GoodInfoMap.put("postOfficeFee",postOfficeFee);
                    GoodInfoMap.put("blackCatFee",blackCatFee);
                    GoodInfoMap.put("gState",gState);
                    GoodInfoMap.put("createTime",createTime);
                    Log.d("main","GoodInfoMap = "+GoodInfoMap);
                    goodCursor.moveToNext();
                }

                //----------------------------------------------------------------------------------
                //    資料表名稱 : goodsType
                //    欄位中文名稱                         欄位名稱          Cursor Index
                //    * 商品分類_id                       goodsType_id             0
                //     商品名稱                           goods_name                1
                //*    fragment代號                       fragType                  2
                //     分類                               type                      3
                //*    創的fragment個數(包括刪掉的)         count                     4
                //     創建時間                            createTime                5
                //----------------------------------------------------------------------------------

                Cursor typeCursor = newProductDatabase.rawQuery("select * from goodsType WHERE goods_name='"+name+"';", null);
                Map<String, Object> GoodTypeInfoMap = new HashMap<>();
                typeCursor.moveToFirst();
                while(!typeCursor.isAfterLast()) {
                    int goodsType_id = typeCursor.getInt(typeCursor.getColumnIndexOrThrow("goodsType_id"));
                    String goods_name = typeCursor.getString(typeCursor.getColumnIndexOrThrow("goods_name"));
                    String fragType = typeCursor.getString(typeCursor.getColumnIndexOrThrow("fragType"));
                    String type = typeCursor.getString(typeCursor.getColumnIndexOrThrow("type"));
                    int count = typeCursor.getInt(typeCursor.getColumnIndexOrThrow("count"));
                    String createTime = typeCursor.getString(typeCursor.getColumnIndexOrThrow("createTime"));

                    GoodTypeInfoMap.put("goodsType_id",goodsType_id);
                    GoodTypeInfoMap.put("goods_name",goods_name);
                    GoodTypeInfoMap.put("fragType",fragType);
                    GoodTypeInfoMap.put("type",type);
                    GoodTypeInfoMap.put("count",count);
                    GoodTypeInfoMap.put("createTime",createTime);
                    Log.d("main","GoodTypeInfoMap = "+GoodTypeInfoMap);
                    typeCursor.moveToNext();
                }

                //----------------------------------------------------------------------------------
                //    資料表名稱 : goodsNorm
                //    欄位中文名稱                         欄位名稱          Cursor Index
                //  * 商品規格_id                       goodsNorm_id             0
                //     商品名稱                         goods_name               1
                //*    fragment代號                       fragNum               2
                //     商品價格                            price                 3
                //     商品規格                            norm                  4
                //     商品數量                            normNum               5
                //*    創的fragment個數(包括刪掉的)          count                 6
                //     創建時間                          createTime               7
                //----------------------------------------------------------------------------------
                Cursor normCursor = newProductDatabase.rawQuery("select * from goodsNorm WHERE goods_name='"+name+"';", null);
                Map<String, Object> GoodNormInfoMap = new HashMap<>();
                normCursor.moveToFirst();
                while(!normCursor.isAfterLast()) {
                    int goodsNorm_id = normCursor.getInt(normCursor.getColumnIndexOrThrow("goodsNorm_id"));
                    String goods_name = normCursor.getString(normCursor.getColumnIndexOrThrow("goods_name"));
                    String fragNum = normCursor.getString(normCursor.getColumnIndexOrThrow("fragNum"));
                    int price = normCursor.getInt(normCursor.getColumnIndexOrThrow("price"));
                    String norm = normCursor.getString(normCursor.getColumnIndexOrThrow("norm"));
                    int normNum = normCursor.getInt(normCursor.getColumnIndexOrThrow("normNum"));
                    int count = normCursor.getInt(normCursor.getColumnIndexOrThrow("count"));
                    String createTime = normCursor.getString(normCursor.getColumnIndexOrThrow("createTime"));

                    GoodNormInfoMap.put("goodsNorm_id",goodsNorm_id);
                    GoodNormInfoMap.put("goods_name",goods_name);
                    GoodNormInfoMap.put("fragNum",fragNum);
                    GoodNormInfoMap.put("price",price);
                    GoodNormInfoMap.put("norm",norm);
                    GoodNormInfoMap.put("normNum",normNum);
                    GoodNormInfoMap.put("count",count);
                    GoodNormInfoMap.put("createTime",createTime);
                    Log.d("main","GoodNormInfoMap = "+GoodNormInfoMap);
                    normCursor.moveToNext();
                }

                newProductDatabase.close();
                dbHelper.close();
                Intent intent = new Intent(NewProductActivity.this, MyProductActivity.class);
                startActivity(intent);

            }
        });
    }
    
}