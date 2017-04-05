package com.cat.zn.Http;

import android.util.Log;

import com.cat.zn.Http.Music.NetworkMusic;
import com.cat.zn.Http.json.Musics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxl96 on 2017/3/29.
 */

public class MusicDataAnalysis {
    private static final String TAG = "MusicDataAnalysis";

    public static ArrayList<NetworkMusic> musicSearchAnalysis(List<Musics> musicses) {
        ArrayList<NetworkMusic> networkMusics = new ArrayList<>();
        for (Musics musics : musicses) {
            List<Musics.ResultBean.SongsBean> songs = musics.getResult().getSongs();
            for (Musics.ResultBean.SongsBean song : songs) {
                int id = song.getId();
                String name = song.getName();
                int albumID = song.getAlbum().getId();
                String albumName = song.getAlbum().getName();
                //歌手
                List<Musics.ResultBean.SongsBean.ArtistsBean> artists = song.getArtists();
//                for (Musics.ResultBean.SongsBean.ArtistsBean artist : artists) {
//                    artistsName+=artist.getName();
//                }
                int artistsID = artists.get(0).getId();
                String artistsName = artists.get(0).getName();
                String picUrl = song.getAlbum().getPicUrl();
                String audio = song.getAudio();
                String page = song.getPage();
                Log.d(TAG, "musicSearchAnalysis: " + name + artistsName);
                networkMusics.add(new NetworkMusic(
                        id, name, artistsName, artistsID, picUrl, audio, page
                ));
            }

        }

        return networkMusics;
    }

}
