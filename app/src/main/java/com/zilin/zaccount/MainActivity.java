package com.zilin.zaccount;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zilin.zaccount.adapter.MainFragmentPagerAdapter;
import com.zilin.zaccount.ui.BaseFragment;
import com.zilin.zaccount.ui.MainAnalysisFragment;
import com.zilin.zaccount.ui.MainMarkFragment;
import com.zilin.zaccount.ui.MainSettingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager containerVp;
    private TabLayout topTabLayout;
    private MainFragmentPagerAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        topTabLayout = (TabLayout) findViewById(R.id.main_tl_tab);
        containerVp = (ViewPager) findViewById(R.id.main_vp_container);
        fragmentAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), getFragmentList());
        containerVp.setAdapter(fragmentAdapter);
        containerVp.setOffscreenPageLimit(3);
        containerVp.setCurrentItem(1);
        topTabLayout.setupWithViewPager(containerVp);

        setTabIcons();
    }

    private void setTabIcons() {
        topTabLayout.getTabAt(0).setCustomView(R.layout.view_tab_analysis);
        topTabLayout.getTabAt(1).setCustomView(R.layout.view_tab_mark);
        topTabLayout.getTabAt(2).setCustomView(R.layout.view_tab_setting);
    }

    public List<BaseFragment> getFragmentList() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        BaseFragment analysisFragment = new MainAnalysisFragment();
        BaseFragment markFragment = new MainMarkFragment();
        BaseFragment settingFragment = new MainSettingFragment();
        fragmentList.add(analysisFragment);
        fragmentList.add(markFragment);
        fragmentList.add(settingFragment);
        return fragmentList;
    }
}
