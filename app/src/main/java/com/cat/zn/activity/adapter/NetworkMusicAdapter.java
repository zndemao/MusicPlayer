package com.cat.zn.activity.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cat.zn.Http.DownloadMusic;
import com.cat.zn.Http.Music.NetworkMusic;
import com.cat.zn.R;
import com.cat.zn.activity.MainActivity;
import com.cat.zn.music.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxl96 on 2017/3/30.
 */

public class NetworkMusicAdapter extends RecyclerView.Adapter<NetworkMusicAdapter.ViewHolder> {
    private static final String TAG = "NetworkMusicAdapter";
    private ArrayList<NetworkMusic> networkMusics;
    private LayoutInflater layoutInflater;

    public NetworkMusicAdapter(Context context, ArrayList<NetworkMusic> musics) {
        this.layoutInflater = LayoutInflater.from(context);
        this.networkMusics = musics;
        Log.d(TAG, "NetworkMusicAdapter: " + networkMusics.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(
                R.layout.item_search_list, parent, false
        );
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 点击播放音乐");
                int adapterPosition = viewHolder.getAdapterPosition();
                NetworkMusic networkMusic = networkMusics.get(adapterPosition);
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                Log.d(TAG, "需要下载的音乐URL:                    "+networkMusic.getAudio());
//                DownloadMusic.requestDownloadMusic(networkMusic.getAudio(),networkMusic.getSongs(),url);
                Message message = new Message();
                message.what = 3;
                message.obj = networkMusic.getAudio();
                MainActivity.handler.sendMessage(message);

            }
        });
        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 点击下载音乐");
            }
        });
        viewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 点击打开音乐详细页面");
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NetworkMusic networkMusic = networkMusics.get(position);
        holder.songs.setText(networkMusic.getSongs());
        holder.artist.setText(networkMusic.getArtist());
    }

    @Override
    public int getItemCount() {
        return networkMusics.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View musicView;
        TextView songs;
        TextView artist;
        ImageView download;
        ImageView info;

        public ViewHolder(View itemView) {
            super(itemView);
            musicView = itemView;
            songs = (TextView) itemView.findViewById(R.id.tv_network_name);
            artist = (TextView) itemView.findViewById(R.id.tv_network_artist);
            download = (ImageView) itemView.findViewById(R.id.iv_network_download);
            info = (ImageView) itemView.findViewById(R.id.iv_network_info);
        }
    }

    //添加数据
    public void addItem(ArrayList<NetworkMusic> netMusics) {
        netMusics.addAll(networkMusics);
        networkMusics.remove(networkMusics);
        networkMusics.addAll(netMusics);
        notifyDataSetChanged();
    }

    public void addMoreItem(ArrayList<NetworkMusic> netMusics) {
        networkMusics.addAll(networkMusics);
        notifyDataSetChanged();
    }
}
