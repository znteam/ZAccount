package com.zilin.zaccount.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.common.Global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean insertAccountBean(AccountBean bean) {
        synchronized (AccountsDao.class) {
            boolean flag;
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sSql = "select id from my_accounts where id = ?;";
                Cursor cursor = db.rawQuery(sSql, new String[]{bean.getId()});
                if (cursor.moveToFirst()) {
                    String sql = "update my_accounts set name=?, info=?, money=?, des=?, time=? where id = ?;";
                    db.execSQL(
                            sql,
                            new String[]{bean.getName(), bean.getInfo(), bean.getMoney()+"", bean.getDes(), bean.getTime(), bean.getId()});

                }else {
                    String sql = "insert into my_accounts (id, name, info, money, des, time) values (?, ?, ?, ?, ?, ?);";
                    db.execSQL(
                            sql,
                            new String[]{bean.getId(), bean.getName(), bean.getInfo(), bean.getMoney()+"", bean.getDes(), bean.getTime()});
                }
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            } finally {
                DBManager.getInstance().closeDatabase();
            }
            return flag;
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
                    bean.setTime(cursor.getString(5));
                    beanList.add(bean);
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

    public List<String> getAllTimeBean() {
        synchronized (AccountsDao.class) {
            List<String> timeList = new ArrayList<>();
            Cursor cursor = null;
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sql = "select distinct time from my_accounts;";
                cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    timeList.add(cursor.getString(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
                DBManager.getInstance().closeDatabase();
            }
            return timeList;
        }
    }

    public  Map<String, Float> getAllGotoMap(String timePre) {
        synchronized (AccountsDao.class) {
            Map<String, Float> goMap = new HashMap<>();
            Cursor cursor = null;
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sql = "select info, sum(money) from my_accounts where name = ? and time like ? group by info;";
                cursor = db.rawQuery(sql, new String[]{Global.NAME_GO, timePre+"%"});
                while (cursor.moveToNext()) {
                    goMap.put(cursor.getString(0), cursor.getFloat(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
                DBManager.getInstance().closeDatabase();
            }
            return goMap;
        }
    }

    public  Map<String, Float> getAllIntoMap(String timePre) {
        synchronized (AccountsDao.class) {
            Map<String, Float> goMap = new HashMap<>();
            Cursor cursor = null;
            try {
                SQLiteDatabase db = DBManager.getInstance().openDatabase();
                String sql = "select info, sum(money) from my_accounts where name = ? and time like ? group by info;";
                cursor = db.rawQuery(sql, new String[]{Global.NAME_IN, timePre+"%"});
                while (cursor.moveToNext()) {
                    goMap.put(cursor.getString(0), cursor.getFloat(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
                DBManager.getInstance().closeDatabase();
            }
            return goMap;
        }
    }
}
