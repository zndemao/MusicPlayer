package com.cat.zn.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

import com.cat.zn.Controller.NetWorkSearch;
import com.cat.zn.Http.Music.NetworkMusic;
import com.cat.zn.Http.MusicDataAnalysis;
import com.cat.zn.Http.json.GsonMusic;
import com.cat.zn.Http.json.Musics;
import com.cat.zn.R;
import com.cat.zn.activity.adapter.NetworkMusicAdapter;
import com.cat.zn.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class NetworkMusicFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "NetworkMusicFragment";
    private View view;
    private WebView webView;
    private EditText et_search;
    private ImageButton searchView;
    private static RecyclerView searchList;

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://searchMusic的返回
                    String jsonData = (String) msg.obj;
                    List<Musics> musicses = GsonMusic.parseJSONWithGSON(jsonData);
                    //将有用数据去除
                    ArrayList<NetworkMusic> networkMusics = MusicDataAnalysis.musicSearchAnalysis(musicses);
                    //更新list   给list
                    Log.d(TAG, "handleMessage: " + networkMusics.size());
                    searchList.setVisibility(View.VISIBLE);
                    initRecycler(networkMusics);
                    break;

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_network_music, container, false);
        initUI();
        initWebView();
        initSearchListener();
        return view;
    }

    private static void initRecycler(ArrayList<NetworkMusic> networkMusics) {
        Log.d(TAG, "initRecycler: sta");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchList.setLayoutManager(linearLayoutManager);

        Log.d(TAG, "initRecycler: manager");
        searchList.setAdapter(new NetworkMusicAdapter(MyApplication.getContext(), networkMusics));
    }

    private void initUI() {
        et_search = (EditText) view.findViewById(R.id.et_search);
        searchView = (ImageButton) view.findViewById(R.id.search_view);
        searchList = (RecyclerView) view.findViewById(R.id.search_music);
    }

    private void initWebView() {
        webView = (WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.bilibili.com");
    }

    private void initSearchListener() {
//        //焦点监听
//        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    searchView.setVisibility(View.VISIBLE);
//                } else {
//                    searchView.setVisibility(View.GONE);
//                }
//            }
//        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String musicName = et_search.getText().toString().trim();
                if (!TextUtils.isEmpty(musicName)) {
                    NetWorkSearch.searchMusic(musicName);
                }
            }
        });
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String musicName = et_search.getText().toString().trim();
                    if (!TextUtils.isEmpty(musicName)) {
                        Log.d(TAG, "onKey: 你点击了回车");
                        NetWorkSearch.searchMusic(musicName);
                    }
                }
                return true;
            }
        });
    }
}
