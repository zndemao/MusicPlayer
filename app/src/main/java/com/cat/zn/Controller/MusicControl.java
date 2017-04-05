package com.cat.zn.Controller;

import android.graphics.Bitmap;
import android.util.Log;

import com.cat.zn.music.Music;
import com.cat.zn.music.MusicInfo;
import com.cat.zn.music.SearchMusic;
import com.cat.zn.service.MusicPlayService;
import com.cat.zn.util.MyApplication;
import com.cat.zn.util.SPutils;

import java.util.List;
import java.util.Random;

/**
 * Created by zxl96 on 2017/3/24.
 */

public class MusicControl {
    private static final String TAG = "MusicControl";
    private static boolean isMusicPlaying = false;
    private static Bitmap artwork;
    private static Mode mode;//播放模式

    public static void startPlay(Music music) {
        /**分析，点击时进行播放，此后发生三种状况。
         * 1，在此点击相同item时暂停，如果再次点击就继续播放。
         * 2.点击其他item，进行播放
         * 3.点击相同item，暂停，然后在点击其他item，进行播放*/
        Log.d(TAG, "startPlay: " + music.getPath());
        boolean isPlaying = MusicPlayService.MusicBinder.isPlaying();//当前播放状态
        if (music.getPath() == SPutils.isSPMusicPlaying()) {//与共享参数中的相等
            if (isPlaying) {
                MusicPlayService.MusicBinder.pause();//进行暂停
                Log.d(TAG, "startPlay: 暂停");
                isMusicPlaying = false;
            } else {
                MusicPlayService.MusicBinder.prepare(music.getPath());//进行准备
                MusicPlayService.MusicBinder.start();
                Log.d(TAG, "startPlay: 继续播放");
                isMusicPlaying = true;
            }
        } else {//清除对象，重设path
            MusicPlayService.MusicBinder.stop();
            MusicPlayService.MusicBinder.prepare(music.getPath());
            MusicPlayService.MusicBinder.start();
            Log.d(TAG, "startPlay: 播放");
            isMusicPlaying = true;
        }
        SPutils.setSPMusic(music);//将当前播放信息放入共享参数
        Log.d(TAG, "startPlay: " + MusicPlayService.MusicBinder.isPlaying());
        artwork = MusicInfo.getArtwork(MyApplication.getContext(), music.getId(), music.getAlbum_id(), true);
        UpdateUI.BarUpDate(music);
    }

    /**
     * 仅支持不更改播放来源，仅修改播放状态
     */
    public static void startPlay() {
        Music spMusic = SPutils.getSPMusic();
        boolean isPlaying = MusicPlayService.MusicBinder.isPlaying();//当前播放状态
        if (isPlaying) {
            MusicPlayService.MusicBinder.pause();//进行暂停
            Log.d(TAG, "startPlay: 暂停");
            isMusicPlaying = false;
        } else {
            MusicPlayService.MusicBinder.prepare(spMusic.getPath());
            MusicPlayService.MusicBinder.start();
            Log.d(TAG, "startPlay: 继续播放");
            isMusicPlaying = true;
        }
        UpdateUI.BarUpDate(SPutils.getSPMusic());
    }
    public static void networkPlay(String path) {
        Music music = new Music(1, "net", "net", 1000 * 60, 1000, path, null, 1000, 1);
        startPlay(music);
    }

    /**
     * 下一曲
     */
    public static void nextMusic() {
        //获取四种播放状态，根据不同播放状态进行歌曲切换
        //假数据，进行实验
        mode = Mode.RANDOM_LOOP;
        switch (mode) {
            case SINGLE_LOOP://单曲循环
                singleLoop();
                Log.d(TAG, "nextMusic: 单曲循环");
                break;
            case LIST_LOOP://列表循环
                listLoopOROrderPlay();
                Log.d(TAG, "nextMusic: 列表循环");
                break;
            case RANDOM_LOOP://随机播放
                randomLoop();
                Log.d(TAG, "nextMusic: 随机播放");
                break;
            case ORDER_PLAY://顺序播放
                listLoopOROrderPlay();
                Log.d(TAG, "nextMusic: 列表循环");
                break;
            default:
                break;
        }
    }

    private static void singleLoop() {//等同于把音乐停止，然后点击相同音乐进行播放
        MusicPlayService.MusicBinder.stop();
        startPlay();
    }

    private static void listLoopOROrderPlay() {
        Music music = new SearchMusic().nextMusic(SPutils.getSPMusic());
        startPlay(music);
    }
    private static void randomLoop() {
        List musics = new SearchMusic().findMusic();
        int size = musics.size();
        Random random = new Random();
        int temp = random.nextInt(size);
        Music music = (Music) musics.get(temp);
        startPlay(music);
    }

    public static boolean isPlaying() {
        return isMusicPlaying;
    }

    public static Bitmap getArtwork() {
        if (artwork == null) {
            Music spMusic = SPutils.getSPMusic();
            artwork = MusicInfo.getArtwork(MyApplication.getContext(), spMusic.getId(), spMusic.getAlbum_id(), true);
        }
        return artwork;
    }

    public static Music getMusicInfo() {
        return SPutils.getSPMusic();
    }
}
