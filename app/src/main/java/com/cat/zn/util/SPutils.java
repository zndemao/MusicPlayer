package com.cat.zn.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cat.zn.music.Music;

/**
 * Created by zxl96 on 2017/3/23.
 */

public class SPutils {
    private static final String TAG = "SPutils";
    /**
     * 将正在播放的音乐存储进共享参数，以便下次获取
     *
     * @param music
     */
    public static void setSPMusic(Music music) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("Music", Context.MODE_PRIVATE).edit();
        editor.putLong("id", music.getId());
        editor.putString("title", music.getTitle());
        editor.putString("artist", music.getArtist());
        editor.putLong("duration", music.getDuration());
        editor.putLong("size", music.getSize());
        editor.putString("path", music.getPath());
        editor.putString("album", music.getAlbum());
        editor.putLong("album_id", music.getAlbum_id());
        editor.putInt("isMusic", music.getIsMusic());
        editor.apply();
    }

    /**
     * 获取在共享参数中存储的播放信息
     *
     * @return new Music
     */
    public static Music getSPMusic() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("Music", Context.MODE_PRIVATE);
        /*private long id;
    private String title;
    private String artist;
    private long duration;
    private long size;
    private String path;
    private String album;
    private long album_id;
    private int isMusic;*/
        long id = preferences.getLong("id", 0);
        String title = preferences.getString("title", "");
        String artist = preferences.getString("artist", "");
        Long duration = preferences.getLong("duration", 0);
        Long size = preferences.getLong("size", 0);
        String path = preferences.getString("path", "");
        String album = preferences.getString("album", "");
        Long album_id = preferences.getLong("album_id", 0);
        int isMusic = preferences.getInt("isMusic", 0);
        preferences.edit();
        return new Music(id, title, artist, duration, size, path, album, album_id, isMusic);
    }

    public static String isSPMusicPlaying() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("Music", Context.MODE_PRIVATE);
//        String name = preferences.getString("name", "");
//        String author = preferences.getString("author", "");
        String path = preferences.getString("path", "");
//        Long length = preferences.getLong("length", 0);
//        preferences.
        return path;
    }
//
//    /**
//     * 设置播放模式
//     * @param context
//     * @param mode
//     */
//    public static void setPlayMode(Context context, PlayMode mode) {
//        SharedPreferences.Editor playMode = context.getSharedPreferences("mode", Context.MODE_PRIVATE).edit();
//        int tempMode = mode(mode);
//        playMode.putInt("playMode", tempMode);
//        playMode.apply();
//    }
//
//    /**
//     * @param context
//     * @return  返回播放模式
//     */
//    public static PlayMode getPlayMode(Context context) {
//        SharedPreferences mode = context.getSharedPreferences("mode", Context.MODE_PRIVATE);
//        int tempMode = mode.getInt("playMode", 2);
//        PlayMode playMode = PlayMode.RANDOM_LOOP;
//        switch (tempMode) {
//            case 0:
//                playMode = PlayMode.SINGLE_LOOP;
//                break;
//            case 1:
//                playMode = PlayMode.LIST_LOOP;
//                break;
//            case 2:
//                playMode = PlayMode.RANDOM_LOOP;
//                break;
//            case 3:
//                playMode = PlayMode.ORDER_LOOP;
//                break;
//        }
//        return playMode;
//    }
//
//    public static int mode(PlayMode mode) {
//        int tempMode = 2;
//        switch (mode) {
//            case SINGLE_LOOP://单曲循环
//                tempMode = 0;
//                break;
//            case LIST_LOOP://list 循环
//                tempMode = 1;
//                break;
//            case RANDOM_LOOP://随机播放
//                tempMode = 2;
//                break;
//            case ORDER_LOOP://顺序播放
//                tempMode = 3;
//                break;
//        }
//        return tempMode;
//    }
}
