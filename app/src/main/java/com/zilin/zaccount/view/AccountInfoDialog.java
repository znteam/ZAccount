package com.zilin.zaccount.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zilin.zaccount.R;
import com.zilin.zaccount.adapter.InfoAdapter;
import com.zilin.zaccount.bean.AccountBean;

import java.util.List;


/**
 * Created by zning on 2016/5/9.
 */
public class AccountInfoDialog extends Dialog {
    private Context mContext;
    private TextView titleTv, infoTv, timeTv, moneyTv, descTv;

    public AccountInfoDialog(Context context) {
        super(context, R.style.DefaultDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_account_info, null);
        setContentView(view);

        titleTv = (TextView) view.findViewById(R.id.dialog_tv_title);
        infoTv = (TextView) view.findViewById(R.id.account_tv_info);
        timeTv = (TextView) view.findViewById(R.id.account_tv_time);
        moneyTv = (TextView) view.findViewById(R.id.account_tv_money);
        descTv = (TextView) view.findViewById(R.id.account_tv_desc);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void show(AccountBean bean) {
        super.show();
        titleTv.setText(bean.getName());
        descTv.setText(bean.getDes());
        timeTv.setText(bean.getTime());
        moneyTv.setText("¥"+bean.getMoney());
        infoTv.setText(bean.getInfo());
    }
}
