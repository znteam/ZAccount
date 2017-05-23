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
        dataList.add("工资");
        dataList.add("兼职");
        dataList.add("理财");
        dataList.add("中奖");
        dataList.add("礼金");
        dataList.add("其他");
        return dataList;
    }

    public static List<String> getGotoDataList() {
        List<String> dataList = new ArrayList<>();
        dataList.add("交通");
        dataList.add("餐饮");
        dataList.add("购物");
        dataList.add("娱乐");
        dataList.add("健康");
        dataList.add("其他");
        return dataList;
    }
}
