package com.cat.zn.music;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.cat.zn.util.MyApplication;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 进行扫描本地音乐，
 * Created by zxl96 on 2017/3/23.
 */

public class SearchMusic {
    private static final String TAG = "SearchMusic";

    public int query() {
        Context mContext = MyApplication.getContext();
        //创建ArryList
        ArrayList<Music> arrayList;
        //实例化ArryList对象
        arrayList = new ArrayList<Music>();
        //创建一个扫描游标
        Cursor c = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        Log.d(TAG, "query: 开始扫描");
        if (c != null) {

            //创建Model对象
            Music model;
            //循环读取
            //实例化Model对象

            while (c.moveToNext()) {

                model = new Music();
                //扫描本地文件，得到歌曲的相关信息
//                String music_name = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
//                String music_singer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
//                long length = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
//                String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM)); //专辑
//                String id = c.getString(c.getColumnIndex(MediaStore.Audio.Media._ID));
                long id = c.getLong(c.getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
                String title = c.getString((c.getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
                String artist = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
                long duration = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
                long size = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
                String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));  //文件路径
                String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片
                long album_id = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)); //唱片图片ID
                int isMusic = c.getInt(c.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐

                //设置值到Model的封装类中
                model.setId(id);
                model.setTitle(title);
                model.setArtist(artist);
                model.setDuration(duration);
                model.setSize(size);
                model.setPath(path);
                Log.d(TAG, "扫描到的音乐名字 " + title + "\talbum=" + album + "\tid=" + id
                +"\tartist"+artist+"\tduration"+duration+"\tsize"+size+"\talbum_id"+album_id
                        +"\tisMusic"+isMusic);
                model.setAlbum_id(album_id);
                model.setIsMusic(isMusic);
                //将model值加入到数组中
                model.save();
                arrayList.add(model);
            }
            //打印出数组的长度
        }
        Log.d(TAG, "扫描到" + arrayList.size() + "首音乐");
//        List<Music> mus = DataSupport.findAll(Music.class);
//        Log.d(TAG, "query: 000");
//        for (Music mu : mus) {
//            Log.d(TAG, "query: "+mu.getName());
//            Log.d(TAG, "query: "+mu.getAuthor());
//            Log.d(TAG, "query: "+mu.getSourece());
//        }
        // findMusic();
        //得到一个数组的返回值
        return arrayList.size();

    }

    public List findMusic() {
        //调用查询前先进行扫描，第二次扫描时要删除 sql 里的所有信息
        DataSupport.deleteAll(Music.class);//从删库到跑路
        query();//重新进行查询
        List<Music> musics = DataSupport.findAll(Music.class);
        ArrayList<Music> arrayListMusic = new ArrayList<Music>();
        for (Music music : musics) {
//            Log.d(TAG, "query: "+mu.getName());
//            Log.d(TAG, "query: "+mu.getAuthor());
//            Log.d(TAG, "query: "+mu.getSourece());
            long id = music.getId();
            String title = music.getTitle();
            String artist = music.getArtist();
            long duration = music.getDuration();
            long size = music.getSize();
            String path = music.getPath();
            String album = music.getAlbum();
            long album_id = music.getAlbum_id();
            int isMusic = music.getIsMusic();
            arrayListMusic.add(new Music(id, title, artist, duration, size, path, album, album_id, isMusic));
        }
        return arrayListMusic;
    }
    public Music nextMusic(Music music) {
        List<Music> musics = DataSupport.findAll(Music.class);
        int temp = 0;//music 在集合中的索引
        for (Music SQLMusic : musics) {
            temp++;
            if (SQLMusic.getPath().equals(music.getPath())
                    && SQLMusic.getId() == music.getId()) {
                break;//结束后便是下一曲的music
            }
        }
        if (temp == musics.size()) {
            return musics.get(0);
        }
        return musics.get(temp);
    }
}
