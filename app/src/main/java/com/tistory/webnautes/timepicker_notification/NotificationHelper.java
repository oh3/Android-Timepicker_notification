package com.tistory.webnautes.timepicker_notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channeID = "channelID";
    public static final String channeNm = "channelNm";

    private NotificationManager notiManager;

    public NotificationHelper(Context base) {
        super(base);

        //안드로이드 버전이 오레오거나 이상이면 채널생성성
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(){

        NotificationChannel channel1 = new NotificationChannel(channeID, channeNm, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.design_default_color_on_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    public NotificationManager getManager(){
        if(notiManager == null){
            notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notiManager;
    }

    public NotificationCompat.Builder getChannelNotification(){

        return new NotificationCompat.Builder(getApplicationContext(), channeID)
                .setContentTitle("알람")
                .setContentText("알람매니저 실행중")
                .setSmallIcon(R.drawable.ic_launcher_background);
    }
}
