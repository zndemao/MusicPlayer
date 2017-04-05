package com.cat.zn.Controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cat.zn.Http.MusicNetWork;
import com.cat.zn.Http.json.GsonMusic;
import com.cat.zn.Http.json.Musics;
import com.cat.zn.util.MyApplication;

import java.util.List;

/**
 * Created by zxl96 on 2017/3/29.
 */

public class NetWorkSearch {

    private static final String TAG = "NetWorkSearch";



    public static void searchMusic(String musicName) {
        Log.d(TAG, "searchMusic: ");
        MusicNetWork.SearchMusic(MyApplication.getContext(), musicName, 10, 1, 0);
    }
}
