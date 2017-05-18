package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.MainActivity;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.Person;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class LambdaExpressionActivity extends AppCompatActivity implements View.OnClickListener{


    Button showCameraButton;
//    public static void printPersonsOlderTahn(List<Person> roster, int age){
//        for( Person p : roster ){
//            if (p.getAge() >= age ){
//                p.printPerson();
//            }
//        }
//    }

    String Path = "";
    String dir;
    File dirFile;


    ArrayAdapter<String> adapter;
    MyAsyncTask mTask;
    String dbID, dbPW;
    String query;
    String userID, userPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda_expression);
        showCameraButton = ( Button ) findViewById( R.id.showCameraButton );
        showCameraButton.setOnClickListener(this);
        dir = Environment.getExternalStorageDirectory() + "/hello/samplePng.png";
        dirFile = new File (dir );

    }

    @Override
    protected void onStart() {
        super.onStart();

        handler.sendEmptyMessage(0);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.showCameraButton :
//                Intent cameraIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
//                cameraIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( dirFile ));
//
//
//                startActivityForResult( cameraIntent, 1 );

                new MyAsyncTask().execute();
                 break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(requestCode == RESULT_OK ) {
                    Toast.makeText(this,"" + dirFile.length(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this,  requestCode + "  file length () : " + dirFile.length(), Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    class MyAsyncTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        protected Boolean doInBackground(String... params){

            boolean flag = false;

            ResultSet reset = null;
            Connection conn = null;



            try {
//                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://:;databaseName=", "", "SABISSA");
             //   Statement stmt = conn.createStatement();
            }catch (Exception e ){
                e.printStackTrace();
            }


            return flag;
        }

        @Override
        protected void onPostExecute(Boolean flag){

            if(flag){
                Intent i = new Intent(LambdaExpressionActivity.this, MainActivity.class);

                //i.putExtra( /*뭔가 데이터를 줘야한다면..*/ ) ;
                startActivity ( i );
            }else{
                //.. 오류처리 ..
            }

        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
        }
    }


    public Handler handler = new Handler(){
        public void handleMessage( Message msg){
            super.handleMessage(msg);

            mTask = new MyAsyncTask();

            mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }
    };

}
