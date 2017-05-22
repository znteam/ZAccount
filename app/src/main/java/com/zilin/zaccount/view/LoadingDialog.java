package com.zilin.zaccount.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zilin.zaccount.R;

/**
 * Created by user03 on 2017/3/10.
 */

public class LoadingDialog extends BaseDialog {

    public LoadingDialog(Context context) {
        super(context, R.style.DefaultDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindowParams();
    }

    @Override
    protected void initView() {
        View view = inflater.inflate(R.layout.dialog_loading, null);
        setContentView(view);
    }

}
