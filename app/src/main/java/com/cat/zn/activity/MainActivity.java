package com.cat.zn.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.cat.zn.Controller.UpdateUI;
import com.cat.zn.Http.Download.DownloadService;
import com.cat.zn.R;
import com.cat.zn.Controller.MusicControl;
import com.cat.zn.music.Music;
import com.cat.zn.service.ServiceInitiate;
import com.cat.zn.util.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private PagerSlidingTabStrip pager_tabs;//页导航活动标签
    private ViewPager pager;//视图页导航
    public final int NUM_PAGER = 2;

    private DrawerLayout mDrawerLayout;

    //Handler更新UI
    public final static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://初始化页面更新
                    break;
                case 1://播放栏更新
                    Music music = (Music) msg.obj;
                    barUpdate(music);
                    break;
                case 2://list update
                    break;
                case 3://下载音乐服务启动
                    String url = (String)msg.obj;
                    Log.d(TAG, "handleMessage: 进入下载线程"+url);
                    downloadBinder.startDownload(url);
                    break;
                case 4://下载完成开城播放
                    String musicPath = (String)msg.obj;
                    MusicControl.networkPlay(musicPath);
                    break;


            }
        }
    };

    //    //牛油刀
//    @BindView(R.id.iv_musicImage)
//    ImageView musicImage;//音乐中显示的专辑信息
//    @BindView(R.id.tv_musicName)
//    TextView musicName;//音乐的名字
//    @BindView(R.id.tv_musicAuthor)
//    TextView musicAuthor;//音乐的歌手
//    @BindView(R.id.ib_musicStartOrStop)
//    ImageButton ib_musicStartOrStop;
    private static ImageView musicImage;
    private static TextView musicName;
    private static TextView musicAuthor;
    private static ImageButton ib_musicStartOrStop;

    @OnClick(R.id.playBar)
    void playBar() {
        //进入到音乐详细页面
        Toast.makeText(this, "进入到音乐详细页面,待添加", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "playBar: ");
    }

    @OnClick(R.id.ib_musicStartOrStop)
    void startOrStop() {
        //音乐 暂停 开始 继续
        Log.d(TAG, "startOrStop: ");
        MusicControl.startPlay();//执行播放
        //页面更新

//        if (MusicControl.isPlaying()) {
//            Log.d(TAG, "startOrStop: TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
//            ib_musicStartOrStop.setImageResource(R.drawable.ic_pause_circle_outline_black_48dp);
//        } else {
//            Log.d(TAG, "startOrStop: FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
//            ib_musicStartOrStop.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);
//        }
//        musicImage.setImageBitmap(MusicControl.getArtwork());

    }

    @OnClick(R.id.ib_nextTrack)
    void nextMusic() {
        //下一曲
        Log.d(TAG, "nextTRack: ");
        MusicControl.nextMusic();
    }


    private static DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent); // 启动服务
        bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务

        //牛油刀
        ButterKnife.bind(this);
        //设置标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolBar);
        setSupportActionBar(toolbar);

        //初始化DrawerLayout控件，
        initDrawerLayout();
        initBarUI();
        initUI();
        //启动服务
        ServiceInitiate.initiate();
    }

    private void initUI() {
        pager_tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pager_tabs.setViewPager(pager);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, displayMetrics);
    }

    /**
     * 初始化DrawerLayout控件
     */
    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_view_headline_black_36dp);
        }
        navView.setCheckedItem(R.id.nav_allMusic);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_allMusic:
                        Toast.makeText(MainActivity.this, "All Music", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_listMusic:
                        Toast.makeText(MainActivity.this, "List Music", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_search:
                Toast.makeText(this, "搜索，待添加", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onOptionsItemSelected: ");
                break;
        }
        return true;
    }

    //对Pager的适配器
    private class PagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"我的音乐", "个性推荐"};
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagerFragment;
            if (position == 0) {
                pagerFragment = new MyMusicFragment();
            } else {
                pagerFragment = new NetworkMusicFragment();
            }
            bundle.putInt("page_num", position);
            pagerFragment.setArguments(bundle);
            return pagerFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGER;
        }

    }

    @Override
    protected void onStart() {
        UpdateUI.BarUpDate();
        super.onStart();
    }

    private void initBarUI() {
        musicImage=(ImageView) findViewById(R.id.iv_musicImage);
        musicName=(TextView) findViewById(R.id.tv_musicName);
        musicAuthor=(TextView) findViewById(R.id.tv_musicAuthor);
        ib_musicStartOrStop=(ImageButton) findViewById(R.id.ib_musicStartOrStop);
    }

    //更新内容
    private static void barUpdate(Music music) {
        //播放状态图片
        if (MusicControl.isPlaying()) {
            ib_musicStartOrStop.setImageResource(R.drawable.ic_pause_circle_outline_black_48dp);
        } else {
            ib_musicStartOrStop.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);
        }
        //专辑图片
        musicImage.setImageBitmap(MusicControl.getArtwork());
        //歌名和歌手
        musicName.setText(music.getTitle());
        musicAuthor.setText(music.getArtist());
    }
}
