package com.zilin.zaccount.manager;

import android.util.Log;

import com.github.mikephil.charting.data.PieEntry;
import com.zilin.zaccount.dao.AccountsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */

public class AccountManager {

    private static AccountManager accountManager;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    public List<String> getMonthList() {
        List<String> monthList = new ArrayList<>();
        List<String> timeList = AccountsDao.getInstance().getAllTimeBean();
        for (int i = 0; i < timeList.size(); i++) {
            try {
                String month = timeList.get(i).substring(0, timeList.get(i).lastIndexOf("-"));
                if (!monthList.contains(month)) {
                    monthList.add(month);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return monthList;
    }

    public ArrayList<PieEntry> getGoMapData(String timePre) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        Map<String, Float> goMap = AccountsDao.getInstance().getAllGotoMap( timePre);
        for (Map.Entry<String, Float> entry : goMap.entrySet()) {
            Log.i("zning", "支出 key= " + entry.getKey() + " and value= " + entry.getValue());
            entries.add(new PieEntry(entry.getValue(), entry.getKey() ));
        }
        return entries;
    }

    public ArrayList<PieEntry> getInMapData(String timePre) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        Map<String, Float> inMap = AccountsDao.getInstance().getAllIntoMap(timePre);
        for (Map.Entry<String, Float> entry : inMap.entrySet()) {
            Log.i("zning", "收入 key= " + entry.getKey() + " and value= " + entry.getValue());
            entries.add(new PieEntry(entry.getValue(), entry.getKey() ));
        }
        return entries;
    }
}
