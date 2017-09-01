package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.BaseActivity;
import com.example.sslab.samplegroupapplication.R;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MarketPlaceActivity extends BaseActivity implements View.OnClickListener{

    TextView resultTextView;
    File sampleExFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markep_place);



        resultTextView = ( TextView ) findViewById( R.id.resultTextView);


        findViewById(R.id.marketPlaceButton).setOnClickListener( this ) ;
        findViewById(R.id.loadFileButton).setOnClickListener( this ) ;


        findViewById(R.id.goFacebook).setOnClickListener( this ) ;
        findViewById(R.id.goInstagram).setOnClickListener( this ) ;

        sampleExFile = new File(getExternalCacheDir().getPath()+"sample.png");
    }

    @Override
    public void responseData(JSONObject jsonObject) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.marketPlaceButton:

                if( !checkHasPackage("com.kakao.talk")){
                    showToast("카카오 설치 실패! ");
                }

                break;
            case R.id.goFacebook:
//                LoginManager login = LoginManager.getInstance();
//                login.registerCallback();
                break;
            case R.id.goInstagram:


                Uri senFileUri = Uri.fromFile(sampleExFile);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, senFileUri);
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, " extra subject");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, " extra text");
                startActivity(Intent.createChooser( intent,"share_to" ));
//                if( checkHasPackage ("com.instagram.android") ) {
//                    Uri senFileUri = FileProvider.getUriForFile(this,getPackageName()+".fileprovider", sampleExFile );
//
////                            Uri.fromFile(sampleExFile);
//
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.putExtra(Intent.EXTRA_STREAM, senFileUri);
//                    intent.setPackage("com.instagram.android");
//
//                    startActivity( intent );
//                }else {
//                    showToast("package name not found");
//                }
                break;
            case R.id.loadFileButton:

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!sampleExFile.exists()) {
                                sampleExFile.createNewFile();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.share_bitmap);
                                OutputStream outputStream = new FileOutputStream(sampleExFile);

                                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                                outputStream.close();
                            }

                        }catch ( IOException e ){
                            e.printStackTrace();
                            resultTextView.setText(e.getMessage());
                        }
                    }
                };
                Thread t = new Thread(run);
                t.run();
                break;
        }
    }

    private boolean checkHasPackage( String packageName ) {
        try {
            getPackageManager().getPackageInfo( packageName, PackageManager.GET_ACTIVITIES );
            ApplicationInfo info = getPackageManager().getApplicationInfo( packageName, 0 );
            if ( !info.enabled ) {
                Intent ittStore = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=" + packageName ) );
                startActivity( ittStore );
                return false;
            } else {
                return true;
            }
        } catch ( PackageManager.NameNotFoundException e ) {
            e.printStackTrace();
            try {
                Intent ittStore = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=" + packageName ) );
                startActivity( ittStore );
                finish();
                return false;
            } catch ( ActivityNotFoundException e1 ) {
                e.printStackTrace();
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://play.google.com/store/apps/details?id=" + packageName ) );
                startActivity( intent );
                return false;
            }
        }
    }
}
