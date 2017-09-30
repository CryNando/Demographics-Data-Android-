package com.example.root.workthread;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private BarChart barChart;
    private List<BarEntry> entries = new ArrayList<>();
    private List<String> labels = new ArrayList<>();
    private BarDataSet barDataSet;
    private BarData barData;
    private InfoDemo infoDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        barChart = (BarChart) findViewById(R.id.barChart);
        infoDemo = (InfoDemo) getIntent().getSerializableExtra("results");

        if (infoDemo == null) {
            Toast.makeText(this, "Something went really wrong! Run to the hills!",
                    Toast.LENGTH_SHORT).show();
        } else {
            setEntriesAndLabels();
            setUpChart();
        }

    }

    private void setEntriesAndLabels() {
        int count = 0;
        for (int i = infoDemo.getStartYear(); i <= infoDemo.getEndYear(); i++) {
            if (infoDemo.getResult(i) != null) {
                entries.add(new BarEntry(Float.valueOf(infoDemo.getResult(i)), count++));
                labels.add(String.valueOf(i));
            }

        }

    }

    private void setUpChart() {
        barDataSet = new BarDataSet(entries, null);
        barData = new BarData(labels, barDataSet);

        barChart.setData(barData);
        barChart.setDescription("Results");
        barChart.getLegend().setEnabled(true);
        barChart.animateY(1200);
        barChart.invalidate();
    }
}
