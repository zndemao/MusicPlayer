package com.cat.zn.music;

import org.litepal.crud.DataSupport;

/**
 * 音乐的对象，同时兼备数据库功能，使用litePal数据库
 * Created by zxl96 on 2017/3/23.
 */

public class Music extends DataSupport {
    private long id;
    private String title;
    private String artist;
    private long duration;
    private long size;
    private String path;
    private String album;
    private long album_id;
    private int isMusic;

    public Music() {
    }

    public Music(long id, String title, String artist, long duration, long size, String path, String album, long album_id, int isMusic) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.path = path;
        this.album = album;
        this.album_id = album_id;
        this.isMusic = isMusic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }
}
