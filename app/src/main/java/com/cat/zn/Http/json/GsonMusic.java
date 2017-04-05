package com.cat.zn.Http.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by zxl96 on 2017/3/28.
 */

public class GsonMusic {
    private static final String TAG = "GsonMusic";

    public static List<Musics> parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<Musics> musicList = gson.fromJson(jsonData, new TypeToken<List<Musics>>() {
        }.getType());
//        for (Musics music : musicList) {
//            Log.d(TAG, "parseJSONWithGSON: " + music.getResult() );
//            Log.d(TAG, "parseJSONWithGSON: " + music.getCode() );
//            Log.d(TAG, "parseJSONWithGSON: " + music.hashCode() );
//            Log.d(TAG, "parseJSONWithGSON: "+new Musics.ResultBean().getSongCount());
//            Log.d(TAG, "parseJSONWithGSON: 555555555555"+music.getResult().getSongCount());
//        }
        return musicList;
    }
}
