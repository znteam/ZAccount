package com.zilin.zaccount.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.zilin.zaccount.R;

import java.util.ArrayList;

public class MainAnalysisFragment extends BaseFragment {

    private PieChart intoPc;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_analysis;
    }

    @Override
    protected void initView(View rootView) {
        intoPc = (PieChart) rootView.findViewById(R.id.analysis_pie_chart_into);
        initChart(intoPc, "center", "des");
        intoPc.setData(getPieData(""));
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

    private PieData getPieData(String type) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new PieEntry((float) i,
                    "i" + i));
        }
        PieDataSet dataSet = new PieDataSet(entries, type);
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
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
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }

    @Override
    protected void doBusiness(Context context) {

    }

}
