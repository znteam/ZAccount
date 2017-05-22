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

import java.util.List;


/**
 * Created by zning on 2016/5/9.
 */
public class InfoDataDialog extends Dialog {
    private Context mContext;
    private TextView titleTv;
    private RecyclerView contentRv;
    private IDataDialogListener idl;
    private InfoAdapter adapter;
    private String title;
    private List<String> dataList;

    public InfoDataDialog(Context context, String title, List<String> dataList) {
        super(context, R.style.DefaultDialog);
        this.mContext = context;
        this.title = title;
        this.dataList = dataList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_info_data, null);
        setContentView(view);

        titleTv = (TextView) view.findViewById(R.id.dialog_tv_title);
        contentRv = (RecyclerView) view.findViewById(R.id.dialog_rv_content);

        adapter = new InfoAdapter(new InfoAdapter.IListener() {
            @Override
            public void onClick(String item) {
                if (idl != null) {
                    idl.toConfirm(item);
                }
                dismiss();
            }
        });
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        contentRv.setLayoutManager(mLinearLayoutManager);
        contentRv.setAdapter(adapter);
        adapter.setData(dataList);
        titleTv.setText(title);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setIDataDialogListener(IDataDialogListener idl) {
        this.idl = idl;
    }

    public interface IDataDialogListener {
        void toConfirm(String item);
    }
}
