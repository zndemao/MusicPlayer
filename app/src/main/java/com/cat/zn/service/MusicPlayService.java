package com.cat.zn.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.cat.zn.Controller.MusicControl;
import com.cat.zn.R;
import com.cat.zn.util.MyApplication;

import java.io.IOException;

public class MusicPlayService extends Service {
    private static final String TAG = "MusicPlayService";

    private static MediaPlayer mediaPlayer;//

    private MusicBinder musicBinder = new MusicBinder();

    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind:绑定服务");
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return musicBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: 服务启动");
        //使用通知
        new NotificationService().Notification();
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_music);
//        Log.d(TAG, "onCreate: " + getPackageName());
//        Notification notification = new NotificationCompat.Builder(MyApplication.getContext()).setContent(remoteViews)
//                .setContent(remoteViews)
//                .setSmallIcon(R.drawable.ic_album_black_18dp)
//                .build();
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(1, notification);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 服务onStartCommand");
//        if (mediaPlayer == null) {
//            mediaPlayer = new MediaPlayer();
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//在此销毁对象
        Log.d(TAG, "onDestroy: 服务停止");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public static void musicPrepare(String path) {
        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放或者继续播放的时候
     */
    public static void musicStart() {
        //音乐准备好的时候
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        //音乐发生错误的时候
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        //播放完毕时候的监听
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MusicControl.nextMusic();
            }
        });
    }

    /**
     * 暂停播放，回继续播放
     */
    public static void musicPause() {
        mediaPlayer.pause();
    }

    /**
     * 从指定位置开始播放
     */
    public void seekTo() {

    }

    /**
     * 停止播放，回重新播放
     */
    public static void musicStop() {
        if (mediaPlayer == null) {
            Log.d(TAG, "musicStop: null");
            return;
        }
        mediaPlayer.stop();
    }

    /**
     * 释放掉相关资源
     */
    public static void musicRelease() {
        mediaPlayer.release();
    }

    /**
     * @return 播放状态
     */
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    /**
     * @return 音乐的时长
     */
    public long getDuration() {
        return mediaPlayer.getDuration();
    }

    public static class MusicBinder extends Binder {

        public static void prepare(String path) {
            musicPrepare(path);
        }

        /**
         * 播放或者继续播放的时候
         */
        public static void start() {
            musicStart();
        }

        /**
         * 暂停播放，回继续播放
         */
        public static void pause() {
            musicPause();
        }

        /**
         * 从指定位置开始播放
         */
        public static void seekTo() {

        }

        /**
         * 停止播放，回重新播放
         */
        public static void stop() {
            musicStop();
        }

        /**
         * 释放掉相关资源
         */
        public static void release() {
            musicRelease();
        }

        /**
         * @return 播放状态
         */
        public static boolean isPlaying() {
            if (mediaPlayer == null) {
                return false;
            }
            return mediaPlayer.isPlaying();
        }

        /**
         * @return 音乐的时长
         */
        public static long getDuration() {
            return mediaPlayer.getDuration();
        }
    }
}
