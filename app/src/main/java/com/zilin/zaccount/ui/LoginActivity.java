package com.zilin.zaccount.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zilin.zaccount.R;
import com.zilin.zaccount.adapter.AccountAdapter;
import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.dao.AccountsDao;
import com.zilin.zaccount.utils.PreferencesUtils;
import com.zilin.zaccount.view.AccountInfoDialog;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText pwdEt;
    private TextView submitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        pwdEt = (EditText) findViewById(R.id.login_et_pwd);
        submitTv = (TextView) findViewById(R.id.login_tv_submit);

        submitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = PreferencesUtils.getPwd(LoginActivity.this);
                String input = pwdEt.getText().toString().trim();
                if(TextUtils.equals(pwd, input)){
                    toMainAct();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String pwd = PreferencesUtils.getPwd(LoginActivity.this);
        if (TextUtils.isEmpty(pwd)) {
            toSettingPwdAct();
        }
    }

    private void toSettingPwdAct() {
        Toast.makeText(this, "请先设置密码...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SettingPwdActivity.class));
    }

    private void toMainAct() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
