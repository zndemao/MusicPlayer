package com.cat.zn.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cat.zn.R;
import com.cat.zn.activity.adapter.LocalMusicAdapter;
import com.cat.zn.music.Music;
import com.cat.zn.music.SearchMusic;
import com.cat.zn.util.LogUtil;
import com.cat.zn.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class MyMusicFragment extends Fragment {
    private static final String TAG = "MyMusicFragment";

    private List<Music> music = new ArrayList<>();
    private View view;
    private LocalMusicAdapter localMusicAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //music list 刷新
                    localMusicAdapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);
                    break;
            }
        }
    };
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d("fragment", "当处于: MyMusicFragment");
        view = inflater.inflate(R.layout.fragment_my_music, container, false);
        //在此填入数据
        music.clear();
        music = new SearchMusic().findMusic();
        RecyclerView recyclerView_local = (RecyclerView) view.findViewById(R.id.recyclerView_local);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView_local.setLayoutManager(layoutManager);
        localMusicAdapter = new LocalMusicAdapter(music);
        recyclerView_local.setAdapter(localMusicAdapter);

        //下拉刷新
        refresh();
        return view;
    }
    //下拉刷新
    private void refresh() {
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
