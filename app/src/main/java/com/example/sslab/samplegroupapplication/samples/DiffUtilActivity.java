package com.example.sslab.samplegroupapplication.samples;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.sslab.samplegroupapplication.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by SSLAB on 2017-10-30.
 */

public class DiffUtilActivity extends AppCompatActivity implements View.OnClickListener,DatabaseErrorHandler{


    static final int REQUEST_CODE=  100;

    ImageView ivFromLocal;
    ImageView ivFromServer;
    ImageView ivFromDB;
    Drawable loadingImage;

    byte resultTarget [];

    String dbName= "imageDB";
    String tableName= "imageTable";

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;

    SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {


            ivFromLocal.setImageBitmap( resource );
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            resource.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            resultTarget = outputStream.toByteArray();
            Log.d("TAG","length " + resultTarget.length);

            if ( sqLiteDatabase.isOpen() ){

                ContentValues contentValues = new ContentValues();
                contentValues.put("IMAGE",resultTarget);

                sqLiteDatabase.insert(tableName,null,contentValues);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView ( R.layout.activity_diff_util);

        ivFromLocal = (ImageView) findViewById(R.id.ivFromLocal);
        ivFromServer = (ImageView) findViewById(R.id.ivFromServer);
        ivFromDB = ( ImageView )findViewById( R.id.ivFromDB );
//        findViewById( R.id.ivFromLocal).setOnClickListener( this );
//        findViewById( R.id.ivFromServer).setOnClickListener( this );
        findViewById( R.id.tvLoadingServer).setOnClickListener( this );
        findViewById( R.id.tvLoadingLocal).setOnClickListener( this );
        findViewById( R.id.tvLocalDB).setOnClickListener( this );


        loadingImage = getResources().getDrawable(R.drawable.progress_dialog_icon,null);

        dbHelper = new DBHelper(this,dbName,null,1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE:

                if( resultCode == RESULT_OK ) {
                    Glide.with(this).asBitmap().load(data.getData()).into(target);


//                    Glide.with(this).load(data.getData())

//                            .into(ivFromLocal)
//                            .onLoadFailed(loadingImage);

                }

                break;
        }
    }

    @Override
    public void onClick(View v) {


        switch ( v.getId()){
            case R.id.tvLoadingLocal :
                showGallery();
                break;
            case R.id.tvLoadingServer :
                break;

            case R.id.tvLocalDB:
                getImage();

                break;
        }
    }


    public void showGallery(){
        Intent showImages = new Intent(Intent.ACTION_GET_CONTENT);

        showImages.setType("image/*");
        startActivityForResult(showImages,REQUEST_CODE);
    }

    public void getImage(){
        if ( sqLiteDatabase.isOpen() ){
            final Cursor cursor = sqLiteDatabase.query(
                    tableName,null,null,null,null,null,null,null
            );

            int columnCount = cursor.getColumnCount();

//            while( !cursor.isLast()){
//
//                Log.d("TAG<","cursor Pos" + cursor.getPosition() );
////            Log.d("TAG","getInt "+ cursor.getInt(0) );
//                cursor.moveToNext();
//            }

            cursor.moveToNext();

            byte loadFromDB []= cursor.getBlob(1);

            Glide.with(this).load(loadFromDB).into(ivFromDB).onLoadFailed( loadingImage );

        }
    }

    @Override
    public void onCorruption(SQLiteDatabase dbObj) {

    }


    private class EmployeeDiffCallback extends DiffUtil.Callback{

        /**
         * 이전 목록의 갯수를 반환한다.
         * @return
         */
        @Override
        public int getOldListSize() {
            return 0;
        }

        /**
         * 새로운 목록의 갯수를 반환한다.
         * @return
         */
        @Override
        public int getNewListSize() {
            return 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
    private class Employee{
        public int id;
        public String name;
        public String rold;
    }


    public class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.create( null );
//            db.openOrCreateDatabase(DiffUtilActivity.this.getFilesDir(),null);
            db.execSQL("CREATE TABLE "+tableName+" (" +
                    "IDX INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "IMAGE BLOB)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    }
}
