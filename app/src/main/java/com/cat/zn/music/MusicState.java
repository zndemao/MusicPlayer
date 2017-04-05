package com.cat.zn.music;

import com.cat.zn.util.SPutils;

/**
 * 音乐的状态，提供音乐的播放状态，正在播放的音乐的内容，
 * Created by zxl96 on 2017/3/23.
 */

public class MusicState {

    private static Music music;
    private static boolean musicState;
    private static String musicName;
    private static String musicAuthor;
    private static String musicPath;
    private static long musicLength;


    public MusicState() {
        music = SPutils.getSPMusic();
        musicName = music.getTitle();
        musicAuthor = music.getArtist();
        musicPath = music.getPath();
        musicLength = music.getDuration();
    }


    public static boolean isMusicState() {
        return musicState;
    }

    /**
     * 设置音乐的播放状态
     *
     * @param musicState 音乐的播放状态
     */
    public static void setMusicState(boolean musicState) {
        MusicState.musicState = musicState;
    }

    public static String getMusicName() {
        return musicName;
    }

    public static void setMusicName(String musicName) {
        MusicState.musicName = musicName;
    }

    public static String getMusicAuthor() {
        return musicAuthor;
    }

    public static void setMusicAuthor(String musicAuthor) {
        MusicState.musicAuthor = musicAuthor;
    }

    public static String getMusicPath() {
        return musicPath;
    }

    public static void setMusicPath(String musicPath) {
        MusicState.musicPath = musicPath;
    }

    public static long getMusicLength() {
        return musicLength;
    }

    public static void setMusicLength(long musicLength) {
        MusicState.musicLength = musicLength;
    }

}

