package com.zilin.zaccount.ui;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

public class ZnApp extends Application {
    private static Context mContext;
    private static ZnApp mInstance;
    private static Handler mMainThreadHandler;
    private static int mMainThreadId;

    public static ZnApp getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        mMainThreadHandler = new Handler();
        mMainThreadId = Process.myTid();
    }


}
