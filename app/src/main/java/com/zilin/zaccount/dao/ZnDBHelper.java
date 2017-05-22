package com.zilin.zaccount.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ZnDBHelper extends SQLiteOpenHelper {

	public ZnDBHelper(Context context) {
		super(context, "zilin_db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE my_accounts (id TEXT PRIMARY KEY, name TEXT, info TEXT, money INTEGER, des TEXT, time INTEGER);");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
