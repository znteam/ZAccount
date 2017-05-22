package com.zilin.zaccount.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zilin.zaccount.R;
import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.common.Global;
import com.zilin.zaccount.view.DataDialog;

public class MainMarkFragment extends BaseFragment implements View.OnClickListener {

    private TextView intoTv, gotoTv, infoTv, timeTv, submitTv;
    private EditText moneyEt, descEt;
    private int currTo = Global.TO_GO;
    private long dataTime;
    private DataDialog gotoInfoDataDialog, intoInfoDataDialog;
    private DatePickerDialog timeDialog;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mark;
    }

    @Override
    protected void initView(View rootView) {
        descEt = (EditText) rootView.findViewById(R.id.mark_et_desc);
        moneyEt = (EditText) rootView.findViewById(R.id.mark_et_money);
        gotoTv = (TextView) rootView.findViewById(R.id.mark_tv_goto);
        intoTv = (TextView) rootView.findViewById(R.id.mark_tv_into);
        infoTv = (TextView) rootView.findViewById(R.id.mark_tv_info);
        submitTv = (TextView) rootView.findViewById(R.id.mark_tv_submit);
        timeTv = (TextView) rootView.findViewById(R.id.mark_tv_time);
    }

    @Override
    protected void doBusiness(Context context) {
        initListeners(this, intoTv, gotoTv, infoTv, submitTv, timeTv);
        initToStatus();
    }

    private void initToStatus() {
        if (currTo == Global.TO_GO) {
            gotoTv.setSelected(true);
            intoTv.setSelected(false);
        } else {
            gotoTv.setSelected(false);
            intoTv.setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mark_tv_goto:
                currTo = Global.TO_GO;
                initToStatus();
                break;
            case R.id.mark_tv_into:
                currTo = Global.TO_IN;
                initToStatus();
                break;
            case R.id.mark_tv_info:
                showInfoDialog(currTo);
                break;
            case R.id.mark_tv_submit:
                saveData();
                break;
            case R.id.mark_tv_time:
                showTimeDialog();
                break;
        }
    }

    //显示来源/用途选择框
    private void showInfoDialog(int currTo) {
        if (currTo == Global.TO_GO) {
            showGotoInfoDialog();
        } else {
            showIntoInfoDialog();
        }
    }

    //用途
    private void showGotoInfoDialog() {
        if (gotoInfoDataDialog == null) {
            gotoInfoDataDialog = new DataDialog(ZnApp.getAppContext(), Global.NAME_GO, Global.getGotoDataList());
            gotoInfoDataDialog.setIDataDialogListener(new DataDialog.IDataDialogListener() {
                @Override
                public void toConfirm(String item) {
                    infoTv.setText(item);
                }
            });
        }
        if (!gotoInfoDataDialog.isShowing()) {
            gotoInfoDataDialog.show();
        }
    }

    //来源
    private void showIntoInfoDialog() {
        if (intoInfoDataDialog == null) {
            intoInfoDataDialog = new DataDialog(ZnApp.getAppContext(), Global.NAME_IN, Global.getIntoDataList());
            intoInfoDataDialog.setIDataDialogListener(new DataDialog.IDataDialogListener() {
                @Override
                public void toConfirm(String item) {
                    infoTv.setText(item);
                }
            });
        }
        if (!intoInfoDataDialog.isShowing()) {
            intoInfoDataDialog.show();
        }
    }

    private void saveData() {
        if (dataTime <= 0) {
            Toast.makeText(getContext(), "时间有误", Toast.LENGTH_SHORT).show();
            return;
        }
        String info = infoTv.getText().toString();
        if (TextUtils.isEmpty(info)) {
            Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String moneyStr = moneyEt.getText().toString();
        double money;
        try {
            money = Double.parseDouble(moneyStr);
        } catch (Exception e) {
            Toast.makeText(getContext(), "金额输入有误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
        AccountBean bean = new AccountBean();
        bean.setId(System.currentTimeMillis() + "");
        bean.setInfo(info);
        bean.setDes(descEt.getText().toString());
        if (currTo == Global.TO_GO) {
            bean.setName(Global.NAME_GO);
        } else {
            bean.setName(Global.NAME_IN);
        }
        bean.setMoney(money);
        bean.setTime(dataTime);
    }

    private void showTimeDialog() {
        if (timeDialog == null) {
        }
        if (!timeDialog.isShowing()) {

        }
    }
}
