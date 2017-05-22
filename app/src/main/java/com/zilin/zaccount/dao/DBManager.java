package com.zilin.zaccount.dao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import com.zilin.zaccount.ui.ZnApp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zning on 2016/7/27.
 */
public class DBManager {
    private AtomicInteger dbOpenCount = new AtomicInteger();//计数器
    private static DBManager instance;
    private static ZnDBHelper dbHelper;
    private SQLiteDatabase database;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    private DBManager() {
        dbHelper = new ZnDBHelper(ZnApp.getAppContext());
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (dbOpenCount.incrementAndGet() == 1) {
            database = dbHelper.getWritableDatabase();
            if (Build.VERSION.SDK_INT >= 11) {
                database.enableWriteAheadLogging();// 允许读写同时进行
            }
        }
        return database;
    }

    public synchronized void closeDatabase() {
        if (dbOpenCount.decrementAndGet() == 0) {
            if (database != null) {
                try {
                    if (database.inTransaction()) {
                        database.endTransaction();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                database.close();
            }
            database = null;
        }
    }
}
