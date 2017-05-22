package com.zilin.zaccount.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zilin.zaccount.R;

public class MainSettingFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(View rootView) {
        rootView.findViewById(R.id.setting_tv_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettingPwd();
            }
        });
    }

    private void toSettingPwd() {
        Intent intent = new Intent(getContext(), SettingPwdActivity.class);
        startActivity(intent);
    }

    @Override
    protected void doBusiness(Context context) {

    }

}
