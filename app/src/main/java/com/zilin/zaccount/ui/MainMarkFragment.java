package com.zilin.zaccount.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zilin.zaccount.R;
import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.common.Global;
import com.zilin.zaccount.dao.AccountsDao;
import com.zilin.zaccount.view.DateDialog;
import com.zilin.zaccount.view.InfoDataDialog;

public class MainMarkFragment extends BaseFragment implements View.OnClickListener {

    private TextView intoTv, infoTitleTv, gotoTv, infoTv, timeTv, submitTv;
    private EditText moneyEt, descEt;
    private int currTo = Global.TO_GO;
    private InfoDataDialog gotoInfoDataDialog, intoInfoDataDialog;
    private DateDialog dateDialog;

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
        infoTitleTv = (TextView) rootView.findViewById(R.id.mark_tv_info_title);
        submitTv = (TextView) rootView.findViewById(R.id.mark_tv_submit);
        timeTv = (TextView) rootView.findViewById(R.id.mark_tv_time);
    }

    @Override
    protected void doBusiness(Context context) {
        initListeners(this, intoTv, gotoTv, infoTv, submitTv, timeTv);
        initToStatus();
        initTimeDialog();
    }

    private void initToStatus() {
        if (currTo == Global.TO_GO) {
            gotoTv.setSelected(true);
            intoTv.setSelected(false);
            infoTitleTv.setText(R.string.mark_info_go);
        } else {
            gotoTv.setSelected(false);
            intoTv.setSelected(true);
            infoTitleTv.setText(R.string.mark_info_in);
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
            gotoInfoDataDialog = new InfoDataDialog(getContext(), Global.NAME_GO, Global.getGotoDataList());
            gotoInfoDataDialog.setIDataDialogListener(new InfoDataDialog.IDataDialogListener() {
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
            intoInfoDataDialog = new InfoDataDialog(getContext(), Global.NAME_IN, Global.getIntoDataList());
            intoInfoDataDialog.setIDataDialogListener(new InfoDataDialog.IDataDialogListener() {
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
        String time = timeTv.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(getContext(), "请选择时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String info = infoTv.getText().toString().trim();
        if (TextUtils.isEmpty(info)) {
            Toast.makeText(getContext(), infoTitleTv.getText().toString()+"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String moneyStr = moneyEt.getText().toString();
        double money;
        try {
            money = Double.parseDouble(moneyStr);
        } catch (Exception e) {
            Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
        if (money <= 0) {
            Toast.makeText(getContext(), "金额不能小于等于0", Toast.LENGTH_SHORT).show();
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
        bean.setTime(time);
        if(AccountsDao.getInstance().insertAccountBean(bean)){
            Toast.makeText(getContext(), "成功记一笔", Toast.LENGTH_SHORT).show();
            clearData();
        }else{
            Toast.makeText(getContext(), "失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData() {
        infoTv.setText("");
        moneyEt.setText("");
        descEt.setText("");
    }

    private void initTimeDialog() {
        if (dateDialog == null) {
            dateDialog = new DateDialog(getContext());
            dateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    timeTv.setText(dateDialog.getDateStr());
                }
            });
            timeTv.setText(dateDialog.getDateStr());
        }
    }

    private void showTimeDialog() {
        initTimeDialog();
        if (!dateDialog.isShowing()) {
            dateDialog.show();
        }
    }
}
