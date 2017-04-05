package com.cat.zn.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.cat.zn.R;
import com.cat.zn.util.LogUtil;
import com.cat.zn.util.MyApplication;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by zxl96 on 2017/3/25.
 */

public class NotificationService {
    public void Notification() {
        LogUtil.d("通知","开启");
        RemoteViews remoteViews = new RemoteViews("com.cat.zn", R.layout.notification_music);
        Notification notification = new NotificationCompat.Builder(MyApplication.getContext()).setContent(remoteViews)
                .setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_album_black_18dp)
                .build();
        NotificationManager manager = (NotificationManager) MyApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
