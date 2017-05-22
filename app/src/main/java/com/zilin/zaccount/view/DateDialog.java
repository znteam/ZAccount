package com.zilin.zaccount.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.DatePicker;

import com.zilin.zaccount.R;

import java.util.Calendar;


/**
 * Created by zning on 2016/5/9.
 */
public class DateDialog extends Dialog implements DatePicker.OnDateChangedListener{
    private Context mContext;
    private DatePicker dateDp;
    private IDialogListener idl;

    public DateDialog(Context context) {
        super(context, R.style.DefaultDialog);
        this.mContext = context;
        init();
    }

    public void setIDialogListener(IDialogListener idl) {
        this.idl = idl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dateDp != null) {
            setContentView(dateDp);
        }
    }

    private void init() {
        dateDp = new DatePicker(mContext);
        dateDp.setBackgroundColor(Color.WHITE);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);      // 注意点，一月是从0开始计算的！！！
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateDp.init(year, month, day, this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (idl != null) {
            idl.toConfirm(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
        }
        dismiss();
    }

    public interface IDialogListener {
        void toConfirm(String item);
    }

    public String getDateStr() {
        if (dateDp != null) {
            return dateDp.getYear()+"-"+(dateDp.getMonth()+1)+"-"+dateDp.getDayOfMonth();
        }else{
            return "";
        }
    }
}
