package com.cat.zn.util;

import android.widget.Toast;

/**
 * Created by Lot on 2017/3/21.
 */

public class ToastUtil {
    private static Toast toast = Toast.makeText(MyApplication.getContext(), "", Toast.LENGTH_SHORT);

    public static void makeText(String msg) {
        toast.setText(msg);
        toast.show();
    }

}
