package com.zilin.zaccount.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/22.
 */

public class PreferencesUtils {
    private static final String SP_NAME ="zn";
    private static final String T_PWD ="zn";

    public static void setPwd(Context context, String pwd) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(T_PWD, pwd);
        editor.commit();
    }

    public static String getPwd(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(T_PWD, "");
    }
}
