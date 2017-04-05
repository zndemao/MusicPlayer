package com.cat.zn.Http.Music;

/**
 * Created by zxl96 on 2017/3/29.
 */

public class NetworkMusic {
    private int songsID;//歌曲ID   重要
    private String songs;//歌曲名
    private String artist;//歌手
    private int artistID;//歌手id
    private String picUrl;//专辑图片
    private String audio;//音乐下载地址
    private String page;//网页

    public NetworkMusic(int songsID, String songs, String artist, int artistID, String picUrl, String audio, String page) {
        this.songsID = songsID;
        this.songs = songs;
        this.artist = artist;
        this.artistID = artistID;
        this.picUrl = picUrl;
        this.audio = audio;
        this.page = page;
    }

    public int getSongsID() {
        return songsID;
    }

    public void setSongsID(int songsID) {
        this.songsID = songsID;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
