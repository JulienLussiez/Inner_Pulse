package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
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

    ListView mListView;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        numTry = f.getString("numTry", "");

        String[] data = new String[Integer.parseInt(numTry)];
        for (i = 0; i < Integer.parseInt(numTry); i++){
            data[i] = f.getString(Integer.toString(i) + "title", "");
        }

        mListView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataActivity.this,
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                item.setText(Integer.toString(i));
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
//                intent.putExtra("subject", f.getString(Integer.toString(i) + "title", ""));
//                intent.putExtra("body", f.getString(Integer.toString(i), ""));
//                startActivity(intent);


                trialList = Arrays.asList(f.getString(Integer.toString(i), "").split(","));
                Log.d("list", trialList.toString());

                final GraphView graph = (GraphView) findViewById(R.id.graph);
                graph.removeAllSeries();
                mSeries1 = new LineGraphSeries<>(generateData());
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
//                staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
                staticLabelsFormatter.setVerticalLabels(new String[] {"Slow", "Good", "Fast"});
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                graph.addSeries(mSeries1);
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

    private DataPoint[] generateData() {
        int count = trialList.size() - 1;
        Log.d("test", String.valueOf(count));
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
