package com.cat.zn.Http;

import android.os.Environment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cat.zn.util.MyApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by zxl96 on 2017/3/30.
 */

public class DownloadMusic {
    private static final String TAG = "DownloadMusic";

    /**
     * 此为下载方法，暂不做下载继续等
     * 完成条件良好情况下，音乐下载与播放任务
     * 后续添加断点续传
     */
    public static void requestDownloadMusic(String audio, String songs, String... params) {
//        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
//        StringRequest stringRequest = new StringRequest(audio, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "onResponse: " + response.toString());
//                SaveFile.saveMusicFile(response, songs);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try {
            //判断文件是否存在
            long downloadLength = 0;//记录已下载的文件长度
            String downloadURL = params[0];
            String fileName = downloadURL.substring(downloadURL.lastIndexOf("/"));

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(path + fileName);
            if (file.exists()) {
                downloadLength = file.length();
            }
            //获取待下载的文件长度如果是0，说明错误，如果和downloadLength一样说明下载完成


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(audio).build();
            okhttp3.Response response = client.newCall(request).execute();
            if (request != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    total += len;
                    savedFile.write(b, 0, len);

                }
            }
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
