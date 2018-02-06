package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataActivity extends AppCompatActivity {


    private TextView item;
    private SharedPreferences f;
    private String numTry;
    private int i;
    private Intent intent = new Intent();
    private LineGraphSeries<DataPoint> mSeries1;
    private List<String> trialList;
    private List<String> trialListInt;
    private String nChart;

    ListView mListView;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        numTry = f.getString("numTry", "");

        data = new String[Integer.parseInt(numTry)];
        for (i = 0; i < Integer.parseInt(numTry); i++){
            data[i] = f.getString(Integer.toString(i) + "title", "");
        }

        List<String> list = Arrays.asList(data);
        Collections.reverse(list);
        data = (String[]) list.toArray();

        mListView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataActivity.this,
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                f.edit().putString("nChart",Integer.toString(i)).commit();
                intent.setClass(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v, int i, long l) {

                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
                intent.putExtra("subject", f.getString(Integer.toString(i) + "title", ""));
                intent.putExtra("body", f.getString(Integer.toString(i), ""));
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}
