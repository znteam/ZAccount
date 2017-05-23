package com.zilin.zaccount.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zilin.zaccount.R;
import com.zilin.zaccount.adapter.AccountAdapter;
import com.zilin.zaccount.adapter.InfoAdapter;
import com.zilin.zaccount.adapter.MainFragmentPagerAdapter;
import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.dao.AccountsDao;
import com.zilin.zaccount.view.AccountInfoDialog;

import java.util.ArrayList;
import java.util.List;

public class AllDataActivity extends AppCompatActivity {

    private RecyclerView contentRv;
    private AccountAdapter adapter;
    private AccountInfoDialog accountInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);

        initView();
    }

    private void initView() {
        contentRv = (RecyclerView) findViewById(R.id.alldata_rv_content);
        adapter = new AccountAdapter(new AccountAdapter.IListener() {
            @Override
            public void onClick(AccountBean item) {
                showAccountDialog(item);
            }

            @Override
            public void onDel(AccountBean item) {
                AccountsDao.getInstance().delAccountBean(item);
            }
        });
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        contentRv.setLayoutManager(mLinearLayoutManager);
        contentRv.setAdapter(adapter);
        adapter.setData(getAllDataList());

    }

    private void showAccountDialog(AccountBean item) {
        if (accountInfoDialog == null) {
            accountInfoDialog = new AccountInfoDialog(this);
        }
        if (!accountInfoDialog.isShowing()) {
            accountInfoDialog.show(item);
        }
    }

    public List<AccountBean> getAllDataList() {
        return AccountsDao.getInstance().getAllAccountBean();
    }
}
