package com.zilin.zaccount.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zning on 2017/5/19.
 */

public class Global {

    public static final int TO_GO = 0;//支出
    public static final int TO_IN = 1;//收入
    public static final String NAME_GO = "支出";
    public static final String NAME_IN = "收入";

    public static List<String> getIntoDataList() {
        List<String> dataList = new ArrayList<>();
        dataList.add("黑钱");
        dataList.add("工资");
        return dataList;
    }

    public static List<String> getGotoDataList() {
        List<String> dataList = new ArrayList<>();
        dataList.add("吃饭");
        dataList.add("泡妞");
        dataList.add("喝酒");
        return dataList;
    }
}
