package com.cat.zn.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by Lot on 2017/3/21.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        LitePalApplication.initialize(context);
        super.onCreate();
    }
    public static Context getContext() {
        return context;
    }
}

