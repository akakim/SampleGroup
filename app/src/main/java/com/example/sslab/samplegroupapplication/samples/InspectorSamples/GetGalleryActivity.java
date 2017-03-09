package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.bitmapsSVGAnim.ExampleActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class GetGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG= this.getClass().getSimpleName();
    Button showGallery;
    ImageView imageView;
    TextView fileName;
    AQuery aQuery;

    File getFile;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_gallery);

        aQuery = new AQuery(this);

        showGallery = ( Button )findViewById(R.id.showGallery);
        showGallery.setOnClickListener( this );
        imageView = ( ImageView )findViewById( R.id.showImageView);
        fileName = (TextView )findViewById( R.id.fileName );

    }


    @Override
    protected void onStart() {
        super.onStart();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what){
                    case 0:
                       int width = getResources().getDisplayMetrics().widthPixels;
                        aQuery.id( R.id.showImageView ).width(width,false).image((File)msg.obj,500);
                        break;
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
        handler = null;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch ( i ){
            case R.id.showGallery :
               showGallery();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 0:

                Log.d("onActivityResult","0");
                try {


                }
                catch (Exception e ){
                    e.printStackTrace();
                }
                break;
            case 3:
                if ( Build.VERSION.SDK_INT < 19 ) {
                    final Uri dataUri = data.getData();
                    Log.d("Uri", dataUri.toString());
                    try {
                        File getFile = new File(getRealPathFromURI(dataUri));

                        int width = getResources().getDisplayMetrics().widthPixels;
                        aQuery.image(getFile, 500).width(width, false);
                        fileName.setText(getFile.getName() + "size : " + getFile.length());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    String appPath = "" ;
                    String fileName = "";
                    final Uri dataUri = data.getData();

                    if("content".equals(dataUri.getScheme())) {
                        try {
                            Cursor cursor;
                            cursor = getContentResolver().query(dataUri, null, null, null, null);
                            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

                            if (cursor != null && cursor.moveToFirst()) {
                                fileName = cursor.getString(nameIndex);
                            }

                            appPath = this.getFilesDir().toString() + "/";
                            new FileCopyTask(this, dataUri, this.handler).execute(appPath + fileName);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if("file".equals(dataUri.getScheme())){

                        appPath = this.getFilesDir().toString() + "/";
                        fileName = dataUri.getLastPathSegment();
                        new FileCopyTask(this, dataUri, this.handler).execute(appPath + fileName);
                        Log.d(TAG,"");
                        Log.d("getPath()",dataUri.getPath());
                        Log.d("getLastPathSegment()",dataUri.getLastPathSegment());
                        Log.d("getAuthority()",dataUri.getAuthority());
                        Log.d("getHost()",dataUri.getHost());

                    }
//                    Log.d("uri",dataUri.toString());
//                        Log.d("uri",dataUri.getPath());
//                        Log.d("Uri",dataUri.getLastPathSegment());
//                    try {
//                        Log.d("getRealPath", getRealPathFromURILollipop());
//
//                        String result = getTargetPathFromUri(dataUri);
//
//                        File getFile = new File(getRealPathFromURILollipop());
//                        int width = getResources().getDisplayMetrics().widthPixels;
//                        aQuery.id( R.id.showImageView).image(getFile, 500).width(width,false);
//                        fileName.setText(getFile.getName() + "size : " + getFile.length());


//                        int width = getResources().getDisplayMetrics().widthPixels;
//                        aQuery.image(getFile, 500).width(width, false);
//                        fileName.setText(getFile.getName() + "size : " + getFile.length());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
//                try {
//                    dataUri = data.getData();
//
//                    if( getFile.exists() ){
//                        int width = getResources().getDisplayMetrics().widthPixels;
//                        aQuery.image(getFile, 500).width(width, false);
//                        fileName.setText(getFile.getName() + "size : " + getFile.length());
//                    }
//                }catch (Exception e ){
//                    e.printStackTrace();
//                }
//                try {
//
//
//                dataUri = data.getData();
//            	Intent intent = new Intent(Intent.ACTION_SEND);
//            	intent.setType("image/jpeg");
//            	intent.putExtra(Intent.EXTRA_STREAM, dataUri);
//            	startActivityForResult(Intent.createChooser(intent, "Share via:"), 0);
//
//
//                }catch ( Exception e ){
//                    e.printStackTrace();
//                }
                break;
        }
    }

    private String getRealPathFromURILollipop(){
        String result="";
        String[] cols = new String[]{ MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        Cursor cursor = getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cols, null , null , null );

        if(cursor == null){
            return result;
        }else {
            cursor.moveToFirst();
            String [] strs = cursor.getColumnNames();
            String [] results = new String[strs.length];
            int Index = 0;


            while ( cursor.moveToNext() ) {
                try {
                    result = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
            cursor.close();
            return  result;
        }
    }

    private String getTargetPathFromUri(Uri contentUri){
        String result="";
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String [] strs = cursor.getColumnNames();
        String [] results = new String[strs.length];
        int Index = 0;
        Log.d("cursorSize",cursor.getColumnCount()+"");
        for(String col :strs){
            Log.d("column ",col + ": "+cursor.getColumnIndex(col));
            results[Index] = cursor.getString(Index);
            Log.d("result",results[Index]);
            Index ++;
        }

        return result;
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result="";
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        Log.d("getCursor","");
        if (cursor == null) { // Source is Dropbox or other similar local file path
            Log.d("cusoris NULL","");
            result = contentURI.getPath();

        } else {
            Log.d("moveToFirst()","");
            cursor.moveToFirst();
            String [] strs = cursor.getColumnNames();
            String [] results = new String[strs.length];
            int Index = 0;
            for(String col :strs){
                Log.d(col,""+cursor.getColumnIndex(col));
                results[Index] = cursor.getString(Index);
                Log.d("result",results[Index]);
                Index ++;
            }
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            Log.d("IDX",String.valueOf(idx));
            Log.d("cursor",cursor.getString(0));
            if(idx == -1 ){
                Toast.makeText(this,"Foo... file find failed",Toast.LENGTH_SHORT).show();
            }else {
                result = cursor.getString(0);
                Toast.makeText(this,"Foo... file find success",Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
        return result;
    }

    private void showGallery() {
        if ( Build.VERSION.SDK_INT < 19 ) {
            Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, 3 );
        } else {
//            	Intent intent = new Intent(Intent.ACTION_SEND);
//            	intent.setType("image/jpeg");
//            	intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(getFile));
//            	startActivityForResult(Intent.createChooser(intent, "Share via:"), 3);
            Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, 3 );
        }
    }
}
