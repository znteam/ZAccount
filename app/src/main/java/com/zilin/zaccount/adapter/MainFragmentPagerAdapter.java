package com.zilin.zaccount.adapter;

import android.support.v4.app.FragmentManager;


import com.zilin.zaccount.ui.BaseFragment;

import java.util.List;

/**
 * Created by zning on 2016/6/14.
 */
public class MainFragmentPagerAdapter extends BaseFragmentPagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm, list);
    }
}
