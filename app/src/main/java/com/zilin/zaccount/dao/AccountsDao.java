package com.zilin.zaccount.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zilin.zaccount.bean.AccountBean;

import java.util.ArrayList;
import java.util.List;

public class AccountsDao{

    private static AccountsDao instance;

    public static AccountsDao getInstance() {
        if (instance == null) {
            synchronized (AccountsDao.class) {
                if (instance == null) {
                    instance = new AccountsDao();
                }
            }
        }
        return instance;
    }

    //id TEXT PRIMARY KEY, name TEXT, info TEXT, money INTEGER, des TEXT, time INTEGER
    public void insertAccountBean(AccountBean bean) {
        synchronized (AccountsDao.class) {
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sSql = "select id from my_accounts where id = ?;";
                Cursor cursor = db.rawQuery(sSql, new String[]{bean.getId()});
                if (cursor.moveToFirst()) {
                    String sql = "update my_accounts set name=?, info=?, money=?, des=?, time=? where id = ?;";
                    db.execSQL(
                            sql,
                            new String[]{bean.getName(), bean.getInfo(), bean.getMoney()+"", bean.getDes(), bean.getTime()+"", bean.getId()});

                }else {
                    String sql = "insert into my_accounts (id, name, info, money, des, time) values (?, ?, ?, ?, ?, ?);";
                    db.execSQL(
                            sql,
                            new String[]{bean.getId(), bean.getName(), bean.getInfo(), bean.getMoney()+"", bean.getDes(), bean.getTime()+""});
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManager.getInstance().closeDatabase();
            }
        }
    }

    public void delAccountBean(AccountBean bean) {
        synchronized (AccountsDao.class) {
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sql = "delete from my_accounts where id = ?;";
                db.execSQL(sql, new Object[]{bean.getId()});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManager.getInstance().closeDatabase();
            }
        }
    }

    public List<AccountBean> getAllAccountBean() {
        synchronized (AccountsDao.class) {
            List<AccountBean> beanList = new ArrayList<>();
            AccountBean bean;
            Cursor cursor = null;
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sql = "select id, name, info, money, des, time from my_accounts;";
                cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    bean = new AccountBean();
                    bean.setId(cursor.getString(0));
                    bean.setName(cursor.getString(1));
                    bean.setInfo(cursor.getString(2));
                    bean.setMoney(cursor.getDouble(3));
                    bean.setDes(cursor.getString(4));
                    bean.setTime(cursor.getLong(5));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
                DBManager.getInstance().closeDatabase();
            }
            return beanList;
        }
    }
}
