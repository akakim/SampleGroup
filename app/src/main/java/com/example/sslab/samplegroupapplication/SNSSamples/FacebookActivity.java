package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FacebookActivity extends AppCompatActivity implements View.OnClickListener{
    com.facebook.login.widget.LoginButton loginButton;
    com.facebook.CallbackManager callbackManager;
    com.facebook.login.LoginResult loginResultAcv;
    LoginManager loginManager;

    ImageView sampleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        FacebookSdk.sdkInitialize(getApplicationContext());

        loginManager = LoginManager.getInstance();

//        loginManager.registerCallback( );



        loginButton = ( com.facebook.login.widget.LoginButton )findViewById( R.id.login_button );
        sampleImageView = ( ImageView ) findViewById( R.id.shareBitmapImageView );


        loginButton.setReadPermissions("email, publish_actions");
        callbackManager = com.facebook.CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("facebookCallback", "Success");
                Log.d("facbookCallback", loginResult.toString());

                loginResultAcv = loginResult;
                loginResultAcv.getAccessToken();
                Toast.makeText(FacebookActivity.this,"login success " , Toast.LENGTH_SHORT ).show();
//                Intent i = new Intent(FacebookActivity.this,GetUserInfoActivity.class );
//                i.putExtra("facebook", loginResultAcv.getAccessToken());
//                startActivity( i );
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("onCancel", "");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                exception.printStackTrace();
//                Log.e("facebookException",exception.getMessage());
            }
        });
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.d("facebookCallback","Success");
//                Log.d("facbookCallback",loginResult.toString());
//                loginResultAcv = loginResult;
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                Log.d("onCancel","");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                exception.printStackTrace();
////                Log.e("facebookException",exception.getMessage());
//            }
//        });




        findViewById(R.id.showGallery).setOnClickListener( this );
        sampleImageView = ( ImageView ) findViewById( R.id.sampleImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 ) {
                Log.d(getClass().getSimpleName(),"result Code " + resultCode );
                if( data  != null){
                    Log.d(getClass().getSimpleName(),"get Data  " + data.getData() );

                    Uri dataset = data.getData();

                    String filePath = getExternalCacheDir() + "SampleImage";

                    File getFile = new File(filePath);
                    File sampleFile = new File(dataset.getPath());
                    try {
                        if (!getFile.exists()) {
                            getFile.createNewFile();
                        }


                        new AsyncTask<File, Void, String>() {

                            Bitmap bitmap;
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                            }

                            @Override
                            protected String doInBackground(File... params) {

                                File targetFile = params[0];
                                File hostFile = params[1];


                                try {
                                    FileInputStream inputStream = new FileInputStream(hostFile);
                                    FileOutputStream outputStream = new FileOutputStream(targetFile);


                                    int read= 0;
                                    byte [] buff = new byte[1024];
                                    while ( inputStream.read(buff) != -1 ){

                                        outputStream.write(buff);
                                    }
                                }catch (FileNotFoundException e ){
                                    e.printStackTrace();
                                    return "FileNotFound in AynschTask";
                                }catch( IOException e ){
                                    e.printStackTrace();
                                    return "read or write Exception Occured";
                                }catch ( Exception e ){
                                    e.printStackTrace();
                                    return " unExpectedException";
                                }


                                bitmap = BitmapFactory.decodeFile(targetFile.getPath());
                                return "success";
                            }

                            @Override
                            protected void onPostExecute(String resultMessage) {
                                super.onPostExecute(resultMessage);

                                if("success".equals( resultMessage))
                                    sampleImageView.setImageBitmap( bitmap );
                                Toast.makeText(FacebookActivity.this,"result Message : " + resultMessage , Toast.LENGTH_SHORT ).show();
                            }
                        }.execute(getFile,sampleFile);
                    }catch (IOException e ){
                        Toast.makeText(this,"file IO Exception ",Toast.LENGTH_SHORT ).show();
                    }catch (Exception e ){
                        Toast.makeText(this,"UnExpected Exception ",Toast.LENGTH_SHORT ).show();
                    }
                }
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showGallery:
//                Intent i = new Intent(Intent.ACTION_PICK );
//                i.setType("image/*");
//                startActivityForResult( i , 100);

                Bitmap sample = BitmapFactory.decodeResource(getResources(),R.drawable.share_bitmap);

                SharePhoto sharePhoto = new SharePhoto.Builder()
                        .setBitmap( sample)
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto( sharePhoto ).build();

                ShareDialog dialog = new ShareDialog(this );
                dialog.show(content, ShareDialog.Mode.NATIVE);
                int result = content.describeContents();

                sample.getRowBytes();
                sample.getHeight();

                long fileSizeInByte = sample.getRowBytes() * sample.getHeight();
                long fileSizeInKb = fileSizeInByte / 1024;
                long fileSizeInMb = fileSizeInKb / 1024;

                Toast.makeText(this,"sample mbSize: " + fileSizeInMb ,Toast.LENGTH_SHORT ).show();
                break;
        }
    }
}
