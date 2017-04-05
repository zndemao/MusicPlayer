package com.cat.zn.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.zn.R;
import com.cat.zn.music.Music;
import com.cat.zn.Controller.MusicControl;

import java.util.List;

/**
 * Created by zxl96 on 2017/3/23.
 */

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.ViewHolder> {
    private static final String TAG = "LocalMusicAdapter";
    public  List<Music> mMusic;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View musicView;
        TextView name;
        TextView singer;
        ImageView setting;

        public ViewHolder(View itemView) {
            super(itemView);
            musicView = itemView;
            name = (TextView) itemView.findViewById(R.id.tv_name);
            singer = (TextView) itemView.findViewById(R.id.tv_singer);
            setting = (ImageView) itemView.findViewById(R.id.iv_list);
        }
    }

    public LocalMusicAdapter(List<Music> musicStates) {
        mMusic = musicStates;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Music music = mMusic.get(position);
        holder.name.setText(music.getTitle());
        holder.singer.setText(music.getArtist());
    }

    @Override
    public int getItemCount() {
//        int size = mMusic.size();
        return mMusic.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //设置点击事件
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放音乐
                Log.d(TAG, "onClick: 点击播放音乐");
                int position = holder.getAdapterPosition();
                Music music = mMusic.get(position);
                MusicControl.startPlay(music);
//                int position = holder.getAdapterPosition();
//                Music music = mMusic.get(position);
////                String sourece = music.getSourece();
//                MusicPlayAuxiliary.play(parent.getContext(),music);
//                Toast.makeText(parent.getContext(),music.getName()+"", Toast.LENGTH_SHORT).show();
////                new MainActivity().refreshBar();
//
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                    }
////                }).start();
//                //将播放的位置给music供其在列表播放/循环时候使用
//                musicPosition = position;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        message.what= MainActivity.UPDATE;
//                        MainActivity.handler.sendMessage(message);
//                    }
//                }).start();
            }
        });
        holder.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击对音乐进行操作，删除收藏等
                Toast.makeText(parent.getContext(), "Image setting", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
}
