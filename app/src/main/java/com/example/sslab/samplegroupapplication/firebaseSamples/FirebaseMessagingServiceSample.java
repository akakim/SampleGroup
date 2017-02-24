package com.example.sslab.samplegroupapplication.firebaseSamples;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.common.GlobalApplication;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;


/**
 * Created by SSLAB on 2017-02-24.
 */

public class FirebaseMessagingServiceSample extends FirebaseMessagingService {
    private static final String TAG = "MyFMService";

    public static Handler h= null;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " +
                remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());
        Map map = remoteMessage.getData();
        Log.d(TAG,"1111");
       // Log.d(TAG,map.get("1111").toString());

        boolean isFoground = false;
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningTaskInfos = manager.getRunningAppProcesses();
        String packages ="";
        for (ActivityManager.RunningAppProcessInfo processInfo : runningTaskInfos){
            if(processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                packages = processInfo.processName;
                isFoground =  true;
            }
        }

            sendNotification("title","mssssssage");

    }


    /**
     * 실제 디바에스에 GCM으로부터 받은 메세지를 알려주는 함수이다. 디바이스 Notification Center에 나타난다.
     * @param title
     * @param message
     */
    private void sendNotification(String title, String message) {


//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("sendNotification",Constants.fm.TAG_NOTICE_FRAGMENT);

        // notification이 클릭되면 어떠한 Intent가 실행될지 결정한다.
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);

        // Notification의 view를 만들어준다.
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_item_background);
        remoteViews.setTextViewText(R.id.notification_title,title);
        remoteViews.setTextViewText(R.id.notification_body,message);
        remoteViews.setTextColor(R.id.notification_title, Color.parseColor("#000000"));
        remoteViews.setTextColor(R.id.notification_body, Color.parseColor("#435463"));

        // Notification을 띄울때 알람음을 설정한다.
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alarm_icon_a)
                .setCustomContentView(remoteViews)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);
//                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
