package com.zilin.zaccount.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected static final String BUNDLE_TITLE="title";
    protected Context mContext = null;
    private View rootView = null;
    private String fragmentTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.fragmentTitle = args.getString(BUNDLE_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        // 渲染视图View(防止切换时重绘View)
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(bindLayout(), null);
        }
        initView(rootView);// 控件初始化
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doBusiness(getActivity());// 业务处理
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mContext = getActivity();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    protected void initListeners(View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    protected abstract int bindLayout();
    protected abstract void initView(View rootView);
    protected abstract void doBusiness(Context context);

}
