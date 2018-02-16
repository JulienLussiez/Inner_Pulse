package com.my.newproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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


    private TextView noData;
    private SharedPreferences f;
    private SharedPreferences g;
    private String numTry;
    private int i;
    private Intent intent = new Intent();
    private LineGraphSeries<DataPoint> mSeries1;
    private List<String> trialList;
    private List<String> trialListInt;
    private String nChart;

    ListView mListView;
    private String[] data;
    private String toleranceString;
    private AlertDialog.Builder d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        g = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        noData = findViewById(R.id.noData);
        d = new AlertDialog.Builder(this);

        if(f.getString("numTry", "").equals("")){
        }
        else {
            noData.setVisibility(View.GONE);
            numTry = f.getString("numTry", "");

            data = new String[Integer.parseInt(numTry)];
            for (i = 0; i < Integer.parseInt(numTry); i++){
                data[i] = f.getString(Integer.toString(i) + "title", "");
            }

            List<String> list = Arrays.asList(data);
            Collections.reverse(list);
            data = (String[]) list.toArray();

            mListView = (ListView) findViewById(R.id.listView);

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataActivity.this, android.R.layout.simple_list_item_1, data);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    f.edit().putString("nChart",Integer.toString(Integer.parseInt(numTry) - i - 1)).apply();
                    intent.setClass(getApplicationContext(), ChartActivity.class);
                    intent.putExtra("afterSession", "0");
                    startActivity(intent);
                    finish();
                }
            });

            //        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            public boolean onItemLongClick(AdapterView<?> arg0, View v, int i, long l) {
//
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
//                intent.putExtra("subject", f.getString(Integer.toString(i) + "title", ""));
//                intent.putExtra("body", f.getString(Integer.toString(i), ""));
//                startActivity(intent);
//                return false;
//            }
//        });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_data, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.clear_data:
                d.setTitle("Clear Data");
                d.setMessage("Are you sure you want to clear all data?");
                d.setCancelable(true);
                d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        f.edit().clear().apply();
                        g.edit().clear().apply();
                        recreate();
                    }
                });
                d.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                d.create().show();
                return true;
        }
        return false;}

    @Override
    public void onBackPressed() {
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}
