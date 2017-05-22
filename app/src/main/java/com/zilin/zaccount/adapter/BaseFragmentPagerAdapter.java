package com.zilin.zaccount.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zilin.zaccount.ui.BaseFragment;

import java.util.List;


/**
 * Created by zning on 2016/6/14.
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    protected List<BaseFragment> fragmentList;
    protected BaseFragment currFragment;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof BaseFragment) {
            currFragment = (BaseFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    public BaseFragment getCurrFragment() {
        return currFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

}
