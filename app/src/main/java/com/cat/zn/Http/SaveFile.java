package com.cat.zn.Http;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.android.volley.Response;
import com.cat.zn.util.MyApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by zxl96 on 2017/3/30.
 */

public class SaveFile {
    private static final String TAG = "SaveFile";

    public static void saveMusicFile(String response, String songs) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        File file;
        try {
            Log.d(TAG, "saveMusicFile: " + Environment.getExternalStorageDirectory());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
                File saveFile = new File(sdCardDir, songs + ".mp3");
                FileOutputStream outStream = new FileOutputStream(saveFile);
//                outStream.write(musicFile.getBytes());
                outStream.close();
                Log.d(TAG, "saveMusicFile: ________________________");
            }
//            file = new File(MyApplication.getContext().getFilesDir(), songs + ".mp3");
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    new FileInputStream(file)));
//            out=MyApplication.getContext().openFileOutput(file, Context.MODE_PRIVATE);
//            writer = new BufferedWriter(new OutputStreamWriter(out));
//            writer.write(musicFile);
//            Log.d(TAG, "saveMusicFile: 0000000000000000000000000000");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "saveMusicFile: 1111111111111111111");
        } catch (IOException e) {
            Log.d(TAG, "saveMusicFile: 333333333333333333333");
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
