package com.cat.zn.Controller;

import android.os.Message;
import android.util.Log;

import com.cat.zn.activity.MainActivity;
import com.cat.zn.music.Music;
import com.cat.zn.util.SPutils;

/**
 * Created by zxl96 on 2017/3/28.
 */

public class UpdateUI {
    private static final String TAG = "UpdateUI";

    /**
     * 控制发送消息更新界面时
     * @param music
     */
    public static void BarUpDate(Music music) {
        Message message = new Message();
        message.what = 1;
        message.obj = music;
        MainActivity.handler.sendMessage(message);
    }

    /**
     * UI更新界面时
     */
    public static void BarUpDate() {
        Message message = new Message();
        message.what = 1;
        message.obj = SPutils.getSPMusic();
        MainActivity.handler.sendMessage(message);
    }
}
