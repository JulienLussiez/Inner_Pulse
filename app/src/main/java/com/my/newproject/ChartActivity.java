package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.List;

public class ChartActivity extends AppCompatActivity {


    private TextView item;
    private SharedPreferences f;
    private String numTry;
    private String nChart;
    private int i;
    private Intent intent = new Intent();
    private LineGraphSeries<DataPoint> mSeries1;
    private List<String> trialList;
    private List<String> trialListInt;

    ListView mListView;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        numTry = f.getString("numTry", "");
        nChart = f.getString("nChart", "");
        Log.d("test", nChart);

        String[] data = new String[Integer.parseInt(numTry)];

        trialList = Arrays.asList(f.getString(nChart, "").split(","));

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        mSeries1 = new LineGraphSeries<>(generateData());
        graph.addSeries(mSeries1);

    }

    @Override
    public void onBackPressed() {
        intent.setClass(getApplicationContext(), DataActivity.class);
        startActivity(intent);
        finish();
    }

    private DataPoint[] generateData() {
        int count = trialList.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double y = (1000/Double.parseDouble(trialList.get(i)))*60;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }


}
