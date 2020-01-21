package com.iindicar.indicar.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.iindicar.indicar.R;
import com.iindicar.indicar.view.main.splash.SplashActivity;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    //메세지 수신은 다음과 같은 경우에 쓰인다.
    //1. 이벤트 공지: 이벤트 공지 Activity로 넘어간다.
    //2. 견적서에 대한 공임업체의 응답: 역시 해당 Activity로 넘어간다.
    //추후 notification 서버와 연결 시, 어떤 종류의 메세지인지를 받아서 작업할 필요가 있다.

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),1);
            Log.d("Indicar Tuning","1");
        } else if(remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),0);
            // sendNotification(remoteMessage.getData().get("message"),0);
            Log.d("Indicar Tuning","0");
        }
    }

    //switcher: 0이면 백그라운드, 1이면 포어그라운드
    private void sendNotification(String title, String messageBody,int switcher) {
        if(switcher == 0) {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,notificationBuilder.build());
        } else if(switcher == 1) {
            //추후 수정 시, 세션 얻어서 전송하는 코드 추가할 것.
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);

            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,notificationBuilder.build());
        }
    }
}
