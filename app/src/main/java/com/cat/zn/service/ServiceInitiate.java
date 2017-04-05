package com.cat.zn.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.cat.zn.util.MyApplication;

/**
 * Created by zxl96 on 2017/3/24.
 */

public class ServiceInitiate {
    private static final String TAG = "ServiceInitiate";
    private static MusicPlayService.MusicBinder musicBinder;

    public static void initiate() {
        //绑定服务
        Log.d(TAG, "initiate: 绑定服务");
        Intent intent = new Intent(MyApplication.getContext(), MusicPlayService.class);
        MyApplication.getContext().startService(intent);
        MyApplication.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
//        MyApplication.getContext().unbindService(connection);
////    unbindService(connection);
    }

    //服务
    private static ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (MusicPlayService.MusicBinder) service;
//            musicBinder.start("/storage/emulated/0/Music/薛之谦 - 绅士.mp3");
            //绑定成功调用
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //绑定失败调用
        }
    };
}
