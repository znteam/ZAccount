package com.zilin.zaccount.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.zilin.zaccount.R;
import com.zilin.zaccount.manager.AccountManager;
import com.zilin.zaccount.view.MonthDialog;

import java.util.ArrayList;
import java.util.List;

public class MainAnalysisFragment extends BaseFragment implements View.OnClickListener{

    private PieChart intoPc, gotoPc;
    private TextView monthTv, alldataTv;
    private MonthDialog monthDialog;
    private String currMonth;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_analysis;
    }

    @Override
    protected void initView(View rootView) {
        intoPc = (PieChart) rootView.findViewById(R.id.analysis_pie_chart_into);
        gotoPc = (PieChart) rootView.findViewById(R.id.analysis_pie_chart_goto);
        monthTv = (TextView) rootView.findViewById(R.id.analysis_tv_month);
        alldataTv = (TextView) rootView.findViewById(R.id.analysis_tv_all_data);

        monthTv.setOnClickListener(this);
        alldataTv.setOnClickListener(this);
    }

    private void initChart(PieChart mChart, String centerStr, String desStr) {
        mChart.setCenterText(centerStr);
        Description des = new Description();
        des.setText(desStr);
        mChart.setDescription(des);
        Legend l = mChart.getLegend();
        l.setEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    @Override
    protected void doBusiness(Context context) {
        List<String> monthList = AccountManager.getInstance().getMonthList();
        if (monthList.size() > 0) {
            setMonthData(monthList.get(0));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.analysis_tv_month:
                showMonthDialog();
                break;
            case R.id.analysis_tv_all_data:
                toAllDataAct();
                break;
        }
    }

    private void toAllDataAct() {
        startActivity(new Intent(getContext(), AllDataActivity.class));
    }

    private void showMonthDialog() {
        monthDialog = new MonthDialog(getContext(), "月份", AccountManager.getInstance().getMonthList());
        monthDialog.setIDataDialogListener(new MonthDialog.IDataDialogListener() {
            @Override
            public void toConfirm(String item) {
                setMonthData(item);
            }
        });
        monthDialog.show();
    }

    public void setMonthData(String monthData) {
        this.currMonth = monthData;
        monthTv.setText(monthData);
        ArrayList<PieEntry> inList = AccountManager.getInstance().getInMapData(monthData);
        initChart(intoPc, "总收入", "");
        intoPc.setData(getPieData(inList));
        intoPc.invalidate();

        ArrayList<PieEntry> goList = AccountManager.getInstance().getGoMapData(monthData);
        initChart(gotoPc, "总支出", "");
        gotoPc.setData(getPieData(goList));
        gotoPc.invalidate();
    }

    private PieData getPieData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        /*for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);*/
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "¥"+value;
            }
        });
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }
}
