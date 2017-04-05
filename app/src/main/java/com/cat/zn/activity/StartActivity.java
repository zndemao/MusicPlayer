package com.cat.zn.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.cat.zn.R;
import com.cat.zn.util.BaseActivity;
import com.cat.zn.util.LogUtil;
import com.cat.zn.util.MyApplication;
import com.cat.zn.util.ToastUtil;

/**
 * 应用开启的第一界面
 * 功能： 获取权限后，跳转到第二界面
 */
public class StartActivity extends BaseActivity {
    private static final String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取读取本地文件权限
        if (ContextCompat.checkSelfPermission(
                MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    StartActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //进行下一个页面跳转
            changeActivity();
        }
    }

    private void changeActivity() {
        LogUtil.d("", "从开启画面");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));//跳转到主页面
                finish();//将活动清除，此活动为启动画面不需要保留
            }
        }, 1000 * 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1://获取本地文件
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    changeActivity();
                } else {
                    ToastUtil.makeText("拒绝访问权限，无法运行");
                    finish();
                }
                break;
        }
    }

}
//执行下一页面
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        startActivity(new Intent(this, MainActivity.class));//跳转到主页面
//        finish();//将活动清除，此活动为启动画面不需要保留

//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(new Intent(StartActivity.this, MainActivity.class));//跳转到主页面
//                finish();//将活动清除，此活动为启动画面不需要保留
//            }
//        };
//        timer.schedule(timerTask,1000*1);