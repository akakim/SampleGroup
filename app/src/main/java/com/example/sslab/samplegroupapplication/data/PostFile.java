package com.example.sslab.samplegroupapplication.data;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sslab.samplegroupapplication.widget.DialogBuilder;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * TODO: ProgressBar 보여주는 예제 참고해서 덧붙이기 .
 */

public class PostFile extends AsyncTask<String,Integer,String> {

    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private StringBuffer stringBuffer;
    private String resultLine;

    private DataOutputStream dataOutputStream;
    private final String crlf="\r\n";
    private final String twoHyphens="--";
    private final String boundary = "*****";

    private Context context;
    private String urlAddr;
    private File file;
    private String param;
    private Handler h;
    String res="";
    private int id;
    private int percentage = 0;

    ProgressBar progressBar;
    /**
     * 사진 업로드 클래스
     * @param context
     * @param urlAddr
     * @param file
     * @param param
     * @param h
     * @param id
     */
    public PostFile(Context context, String urlAddr, File file, String param, Handler h, int id ){
        this.context = context;
        this.urlAddr = urlAddr;
        this.file = file;
        this.param = param;
        this.id = id;
        this.h = h;
        Log.d( "Tag", "param : " + param );
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        ProgressDialog builder= new DialogBuilder(context);
//        builder.create().setTitle("파일을 업로딩합니다.");
//        builder.setMessage("진행률 : " + percentage + "%");
//        builder.

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyle); // progressBarStyle
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);

    }

    @Override
    protected String doInBackground(String... params) {
        final String TAG= "doInBackground";
        try {
            Log.d(TAG,"");
            httpURLConnection = (HttpURLConnection) new URL(urlAddr).openConnection();
            Log.d(TAG,"openedConnection");
            httpURLConnection.setDoInput( true );   // 검색 요망
            httpURLConnection.setDoOutput( true );  // 검색 요망
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setRequestProperty( "connection", "close" );
            httpURLConnection.setRequestProperty( "Content-Type", "multipart/form-data;boundary=" + boundary );

            dataOutputStream = new DataOutputStream ( httpURLConnection.getOutputStream() );
            Log.d(TAG,"outputStream Instance Created");
            //1. 파라미터
            dataOutputStream.writeBytes( "--*****\r\n" );
            dataOutputStream.writeBytes( "Content-Disposition: form-data; name=\"params\"" + "\r\n" );
            dataOutputStream.writeBytes( "Content-Type: application/json;" + "\r\n" );
            dataOutputStream.writeBytes( "\r\n" );
            dataOutputStream.write( param.getBytes( "UTF-8" ) );
            dataOutputStream.writeBytes( "\r\n" );

            //2. raw 이미지 데이터
            dataOutputStream.writeBytes("--****\r\n");
            long size = file.length();
            float rate = size/1024;
            FileInputStream mFileInputStream = new FileInputStream( file );
            dataOutputStream.writeBytes( "Content-Disposition: form-data; name=\"file\";filename=\"" + file.toURI() + "\"" + "\r\n" );
            dataOutputStream.writeBytes( "Content-Type: image/png;" + "\r\n" );
            dataOutputStream.writeBytes("Content-Transfer-Encoding: binary" + "\r\n");
            dataOutputStream.writeBytes( "\r\n" );

            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min( bytesAvailable, maxBufferSize );
            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read( buffer, 0, bufferSize );
            long count = 0;
            while ( bytesRead > 0 ) {

                dataOutputStream.write( buffer, 0, bufferSize );
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min( bytesAvailable, maxBufferSize );
                bytesRead = mFileInputStream.read( buffer, 0, bufferSize );
                count+=bytesRead;
                rate *= bytesRead ;
               publishProgress((int)rate);
            }
            Log.d(TAG,"count : "+ count );
            dataOutputStream.writeBytes( "\r\n" );
            mFileInputStream.close();
            dataOutputStream.flush();
            dataOutputStream.writeBytes( "--*****--\r\n" );
            dataOutputStream.close();

            // 파일 입력 끝 . 만약 여러게의 파일을 올린다면 아랫 부분에 더 추가해준다.

            Log.d("requestSuccesfully","Now get response ");
            Log.d(TAG,   httpURLConnection.getResponseCode()+"");
            // 이제부턴 서버에서 받아온 response를 세팅한다.
            BufferedReader br = new BufferedReader( new InputStreamReader( httpURLConnection.getInputStream(), "UTF-8" ) );

            StringBuffer sb = new StringBuffer();

            String resultLine;
            while ( ( resultLine = br.readLine() ) != null ) {
                sb.append( resultLine );
            }
            res = sb.toString();
            br.close();
            httpURLConnection.disconnect();


        }catch ( MalformedURLException e){
            e.printStackTrace();
            res = "MalformedException";
            return res;
        } catch ( FileNotFoundException fn){
            fn.printStackTrace();
            res  = "FileNotFoundException";
            return res;

        }catch ( IOException io){
            io.printStackTrace();
            res = "IOException";
            return res;
        }catch ( Exception e){
            e.printStackTrace();

            return res;
        }finally {
            httpURLConnection.disconnect();
        }

        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        final String TAG ="onPostExecute";
        if("".equals(s)){
            if(progressBar.isShown()){
                progressBar.setVisibility(View.GONE);
            }
            DialogBuilder builder = new DialogBuilder( context );
            builder.setMessage("알수 없는에러발생 . ");

            Dialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if("MalformedException".equals(s)){
            if(progressBar.isShown()){
                progressBar.setVisibility(View.GONE);
            }
            DialogBuilder builder = new DialogBuilder( context );
            builder.setMessage("URL이 잘못되었습니다. ");

            Dialog alertDialog = builder.create();
            alertDialog.show();

        }else {
            try {
                JSONObject response = new JSONObject( s );
                Log.d(TAG,s.toString());
                Message msg = Message.obtain();
                msg.what = id;
                msg.obj = response.toString();
                h.sendMessage( msg );
//            if ( response.getString( "rescode" ).equals( "0000" ) ) {
//                Toast.makeText( context, "파일을 업로드했습니다.", Toast.LENGTH_SHORT ).show();
//            }
            } catch ( Exception e ) {
                e.printStackTrace();
                Dialog dialog = new Dialog( context );
                dialog.setTitle("JSONException ");
            }
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
