package com.example.sslab.samplegroupapplication.samples;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class URLConnectionSampleActivity extends AppCompatActivity implements View.OnClickListener {

    Button requestURLConnection;
    Button requestData;

    TextView URLConnectionResult;
    TextView requestDataResult;

    final String urlstr = "http://ins.autoground.tripath.work/common/pushList";
    JSONObject jsonObject = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlconnection_sample);


        requestURLConnection = ( Button ) findViewById( R.id.requestURLConnection );
        requestData          = ( Button ) findViewById( R.id.requestData );
        URLConnectionResult  = ( TextView ) findViewById( R.id.requestURLConnectionResult);
        requestDataResult    = ( TextView ) findViewById( R.id.requestDataResult );

        requestURLConnection.setOnClickListener(this);
        requestData.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.requestURLConnection:
                URLConnectionSampleTask task =new URLConnectionSampleTask();
                try {
                    URL url1 = new URL(urlstr);
                    URL url2 = new URL(urlstr);
                    URL url3 = new URL(urlstr);
                    task.execute(url1);
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.requestData:
                break;
        }
    }

    public void setJsonObject(JSONObject jsonObject){
        this.jsonObject = jsonObject;

        try {
            Log.d("jsonObject", this.jsonObject.toString());
            URLConnectionResult.setText(this.jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void showToastMessage(String s,int length){
        Toast.makeText(this,s,length);
    }

    private class URLConnectionSampleTask extends AsyncTask<URL,String,String>{
        JSONArray  outputArr = new JSONArray();
        JSONObject output = new JSONObject();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL list [] = urls.clone();

            HttpURLConnection connection [] = new HttpURLConnection[list.length];

            try {
                for(int i = 0;i<list.length;i++) {
                    connection[i] = (HttpURLConnection)list[i].openConnection();

                    Log.d(getApplicationContext().getClass().getSimpleName(),"connectin Success");
                    //Timeout 시간(서버 접속시 연결시간.
                    connection[i].setConnectTimeout(10000);
                    //Timeout 시간 Read시 연결시간
                    connection[i].setReadTimeout(10000);
                    connection[i].setRequestMethod("POST");

                    // 서버 Response Data를 JSON 형식의 타입으로 요청.
                    connection[i].setRequestProperty("Accept", "application/json");
                    // 타입설정을 (application/json)형식으로 전송 (RequestBody 전달시 application/json으로 서버에 전달.
                    connection[i].setRequestProperty("Content-Type", "application/json");

                    connection[i].setRequestProperty("Cache-Control","no-cache");
                    connection[i].setDoOutput(true);
                    connection[i].setDoInput(true);




                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ikenId","llooooll24");
                    jsonObject.put("listCnt","1");
                    jsonObject.put("beginIdx",String.valueOf(i));

                    HttpEntity entity = new StringEntity(jsonObject.toString());

                    Log.d(getApplicationContext().getClass().getSimpleName(),"connection outputSetting start");
                    // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
                    OutputStream outputstream = connection[i].getOutputStream();
                    outputstream.write(jsonObject.toString().getBytes());
                    outputstream.flush();

                    Log.d(getApplicationContext().getClass().getSimpleName(),"outputStreamFlushed");
                    String response="";
                    int responseCode = connection[i].getResponseCode();


                    Map<String, List<String>> map =  connection[i].getHeaderFields();
                    Set<String> keySet = map.keySet();

                    ArrayList<ArrayList> listoflist = new ArrayList<>();

                    Log.d("keySet",keySet.toString());

                    //[null, Connection, Content-Type, Date, Keep-Alive, Server, Transfer-Encoding, Vary, X-Android-Received-Millis, X-Android-Response-Source, X-Android-Selected-Protocol, X-Android-Sent-Millis]
                    List<String> connectionList= map.get("Connection");
                    
                    for(String s: connectionList){
                        Log.d("connection",s);
                    }
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream input = connection[i].getInputStream();
                        ByteArrayOutputStream bout = new ByteArrayOutputStream();
                        byte [] byteBuffer = new byte[1024];
                        byte [] byteData = null;
                        int nLength = 0;
                        while((nLength = input.read(byteBuffer,0,byteBuffer.length)) != -1){
                            bout.write(byteBuffer,0,nLength);
                        }
                        byteData = bout.toByteArray();

                        response = new String(byteData);
                        output.put(String.valueOf(i),response);

                        Log.d("response",response);
                    }else {
                        return String.valueOf(responseCode)+"error occurred";
                    }
                }

            }catch (IOException e){
                e.printStackTrace();
                return "IOException";
            }catch (Exception e){
                e.printStackTrace();
                return "Exception";
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            showToastMessage(s,Toast.LENGTH_LONG);
            setJsonObject(output);

            /**
             * public static final int HTTP_ACCEPTED = 202;
             public static final int HTTP_BAD_GATEWAY = 502;
             public static final int HTTP_BAD_METHOD = 405;
             public static final int HTTP_BAD_REQUEST = 400;
             public static final int HTTP_CLIENT_TIMEOUT = 408;
             public static final int HTTP_CONFLICT = 409;
             public static final int HTTP_CREATED = 201;
             public static final int HTTP_ENTITY_TOO_LARGE = 413;
             public static final int HTTP_FORBIDDEN = 403;
             public static final int HTTP_GATEWAY_TIMEOUT = 504;
             public static final int HTTP_GONE = 410;
             public static final int HTTP_INTERNAL_ERROR = 500;
             public static final int HTTP_LENGTH_REQUIRED = 411;
             public static final int HTTP_MOVED_PERM = 301;
             public static final int HTTP_MOVED_TEMP = 302;
             public static final int HTTP_MULT_CHOICE = 300;
             public static final int HTTP_NOT_ACCEPTABLE = 406;
             public static final int HTTP_NOT_AUTHORITATIVE = 203;
             public static final int HTTP_NOT_FOUND = 404;
             public static final int HTTP_NOT_IMPLEMENTED = 501;
             public static final int HTTP_NOT_MODIFIED = 304;
             public static final int HTTP_NO_CONTENT = 204;
             public static final int HTTP_OK = 200;
             public static final int HTTP_PARTIAL = 206;
             public static final int HTTP_PAYMENT_REQUIRED = 402;
             public static final int HTTP_PRECON_FAILED = 412;
             public static final int HTTP_PROXY_AUTH = 407;
             public static final int HTTP_REQ_TOO_LONG = 414;
             public static final int HTTP_RESET = 205;
             public static final int HTTP_SEE_OTHER = 303;
             */
        }
    }

}


/*// HttpURLConnection 객체 생성.
HttpURLConnection conn = null;

// URL 연결 (웹페이지 URL 연결.)
conn = (HttpURLConnection)url.openConnection();

// TimeOut 시간 (서버 접속시 연결 시간)
conn.setConnectTimeout(CONN_TIMEOUT * 1000);

// TimeOut 시간 (Read시 연결 시간)
conn.setReadTimeout(READ_TIMEOUT * 1000);

// 요청 방식 선택 (GET, POST)
conn.setRequestMethod(GET);

// Request Header값 셋팅 setRequestProperty(String key, String value)
conn.setRequestProperty("NAME", "name");
conn.setRequestProperty("MDN", "mdn");
conn.setRequestProperty("APPID", "appid");

// 서버 Response Data를 xml 형식의 타입으로 요청.
conn.setRequestProperty("Accept", "application/xml");

// 서버 Response Data를 JSON 형식의 타입으로 요청.
conn.setRequestProperty("Accept", "application/json");

// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 text/html로 서버에 전달.)
conn.setRequestProperty("Content-Type", "text/html");

// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 application/xml로 서버에 전달.)
conn.setRequestProperty("Content-Type", "application/xml");

// 타입설정(application/json) 형식으로 전송 (Request Body 전달시 application/json로 서버에 전달.)
conn.setRequestProperty("Content-Type", "application/json");

// 컨트롤 캐쉬 설정
conn.setRequestProperty("Cache-Control","no-cache");

// 타입길이 설정(Request Body 전달시 Data Type의 길이를 정함.)
conn.setRequestProperty("Content-Length", "length")

// User-Agent 값 설정
conn.setRequestProperty("User-Agent", "test");

// OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
conn.setDoOutput(true);

// InputStream으로 서버로 부터 응답을 받겠다는 옵션.
conn.setDoInput(true);

// Request Body에 Data를 담기위해 OutputStream 객체를 생성.
OutputStream os = conn.getOutputStream();

// Request Body에 Data 셋팅.
os.write(body.getBytes("euc-kr"));

// Request Body에 Data 입력.
os.flush();

// OutputStream 종료.
os.close();

// 실제 서버로 Request 요청 하는 부분. (응답 코드를 받는다. 200 성공, 나머지 에러)
int responseCode = conn.getResponseCode();

// 접속해지
conn.disconnect();
*/