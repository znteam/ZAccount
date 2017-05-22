package com.zilin.zaccount.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zilin.zaccount.R;
import com.zilin.zaccount.adapter.MainFragmentPagerAdapter;
import com.zilin.zaccount.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingPwdActivity extends AppCompatActivity {

    private View oldLayout;
    private EditText oldEt, newEt, againEt;
    private TextView submitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pwd);
        initView();
    }

    private void initView() {
        oldLayout = findViewById(R.id.pwd_layout_old);
        oldEt = (EditText) findViewById(R.id.pwd_et_old);
        newEt = (EditText) findViewById(R.id.pwd_et_new);
        againEt = (EditText) findViewById(R.id.pwd_et_again);
        submitTv = (TextView) findViewById(R.id.pwd_tv_submit);

        submitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePwd();
            }
        });
    }

    private void savePwd() {
        String oldPwd = oldEt.getText().toString().trim();
        String newPwd = newEt.getText().toString().trim();
        String againPwd = againEt.getText().toString().trim();

        String pwd = PreferencesUtils.getPwd(this);
        if (!TextUtils.equals(pwd, oldPwd)) {
            Toast.makeText(this, "旧密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if(newPwd.length()!=6){
            Toast.makeText(this, "新密码必须为6位数字组成", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.equals(newPwd, againPwd)) {
            Toast.makeText(this, "两次输入密码不对，请重新确认", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferencesUtils.setPwd(this, newPwd);
        Toast.makeText(this, "新密码设置成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String pwd = PreferencesUtils.getPwd(this);
        if (TextUtils.isEmpty(pwd)) {
            oldLayout.setVisibility(View.GONE);
        }else{
            oldLayout.setVisibility(View.VISIBLE);
        }
    }
}
