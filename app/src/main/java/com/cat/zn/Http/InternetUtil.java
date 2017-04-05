package com.cat.zn.Http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 网络通信工具类
 * Created by SmileSB101 on 2016/11/1 0001.
 */

public class InternetUtil{
    /**
     * 网络请求队列
     */
    private static RequestQueue mRequestqueue;
    public static RequestQueue getmRequestqueue(Context context)
    {
        if(mRequestqueue == null)
        {
            mRequestqueue = Volley.newRequestQueue(context);
            return mRequestqueue;
        }
        else{
            return mRequestqueue;
        }
    }
}