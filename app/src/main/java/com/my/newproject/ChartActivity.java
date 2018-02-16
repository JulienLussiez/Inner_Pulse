package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Arrays;
import java.util.List;

public class ChartActivity extends AppCompatActivity {


    private TextView item;
    private TextView fbPost;
    private SharedPreferences f;
    private SharedPreferences Settings;
    private String numTry;
    private String nChart;
    private int i;
    private Intent intent = new Intent();
    private PointsGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> serie;
    private LineGraphSeries<DataPoint> serie2;
    private LineGraphSeries<DataPoint> serie3;
    private PointsGraphSeries<DataPoint> sync;
    private PointsGraphSeries<DataPoint> cont;
    private List<String> trialList;
    private String afterSession;
    private Button sendData;

    ListView mListView;
    private String[] data;
    private List<String> syncList;
    private List<String> contList;
    private List<String> syncTap;
    private List<String> contTap;
    private double beatInterval;
    private PointsGraphSeries<DataPoint> min;
    private PointsGraphSeries<DataPoint> max;
    private PointsGraphSeries<DataPoint> middle;
    private PointsGraphSeries<DataPoint> mSeries2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        afterSession = getIntent().getStringExtra("afterSession");

        fbPost = findViewById(R.id.fbPost);
        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        Settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        sendData = findViewById(R.id.sendData);
//        numTry = f.getString("numTry", "");
        nChart = f.getString("nChart", "");

        fbPost.setText(f.getString("fbPost" + nChart, ""));
        trialList = Arrays.asList(f.getString(nChart, "").split(","));
//        syncList = Arrays.asList(f.getString(nChart  + "sync", "").split(","));
        contList = Arrays.asList(f.getString(nChart  + "cont", "").split(","));
//        syncTap = Arrays.asList(f.getString(nChart  + "syncTap", "").split(","));
        contTap = Arrays.asList(f.getString(nChart  + "contTap", "").split(","));

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        beatInterval = (60/Double.parseDouble((Settings.getString("bpm", ""))))*1000;
//        sync = new PointsGraphSeries<DataPoint>(generateData5());
        cont = new PointsGraphSeries<DataPoint>(generateData6());
        min = new PointsGraphSeries<>(generateData7());
        max = new PointsGraphSeries<>(generateData8());
        middle = new PointsGraphSeries<>(generateData10());
        mSeries1 = new PointsGraphSeries<>(generateData());
//        mSeries2 = new PointsGraphSeries<DataPoint>(generateData9());
//        serie = new LineGraphSeries<>(generateData2());
//        serie2 = new LineGraphSeries<>(generateData3());
//        serie3 = new LineGraphSeries<>(generateData4());
//        serie.setColor(this.getResources().getColor(R.color.good));
//        serie2.setColor(this.getResources().getColor(R.color.good));
//        serie3.setColor(this.getResources().getColor(R.color.good));

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(Double.parseDouble((f.getString(nChart  + "beats", ""))));

        // set manual Y bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(Double.parseDouble((f.getString(nChart  + "bpm", ""))) - 30);
        graph.getViewport().setMaxX(Double.parseDouble((f.getString(nChart  + "bpm", ""))) + 30);

        mSeries1.setColor(Color.BLACK);
        mSeries1.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(3);
                canvas.drawLine(x-4, y-4, x+4, y+4, paint);
                canvas.drawLine(x+4, y-4, x-4, y+4, paint);
            }
        });

//        graph.addSeries(serie);
//        graph.addSeries(serie2);
//        graph.addSeries(serie3);
        graph.addSeries(mSeries1);


        min.setColor(Color.RED);
        min.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(5);
                canvas.drawLine(x-1, y-0, x+0, y+0, paint);
            }
        });

        middle.setColor(Color.RED);
        middle.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(3);
                canvas.drawLine(x-1, y-0, x+0, y+0, paint);
            }
        });

        max.setColor(Color.RED);
        max.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(5);
                canvas.drawLine(x-1, y-0, x+0, y+0, paint);
            }
        });
//
//        sync.setColor(Color.RED);
//        sync.setCustomShape(new PointsGraphSeries.CustomShape() {
//            @Override
//            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
//                paint.setStrokeWidth(3);
//                canvas.drawLine(x-0, y-7, x+0, y+7, paint);
//                canvas.drawLine(x+7, y-0, x-7, y+0, paint);
//            }
//        });
//
        cont.setColor(Color.BLACK);
        cont.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(2);
                canvas.drawLine(x-0, y-7, x+0, y+7, paint);
                canvas.drawLine(x+7, y-0, x-7, y+0, paint);
            }
        });

        graph.addSeries(min);
        graph.addSeries(middle);
        graph.addSeries(max);
//        graph.addSeries(sync);
        graph.addSeries(cont);

        sendData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
                intent.putExtra("subject", f.getString(nChart + "title", ""));
                intent.putExtra("body", f.getString(nChart, ""));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.send_data:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra("subject", f.getString(nChart + "title", ""));
                intent.putExtra("body", f.getString(nChart, ""));
                startActivity(intent);
                return true;
        }
        return false;}

    @Override
    public void onBackPressed() {
        if(afterSession.equals("0")){
            intent.setClass(getApplicationContext(), DataActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            intent.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private DataPoint[] generateData() {
        int count = trialList.size()-1;
        Log.d("count", Integer.toString(count));
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = (1000/Double.parseDouble(trialList.get(i)))*60;
            double y = i;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData9() {
        int count = trialList.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = (1000/Double.parseDouble(trialList.get(i)))*60;
            double y = i;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData2() {
        int count = trialList.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double y = Double.parseDouble((Settings.getString("bpm", ""))) - Double.parseDouble((Settings.getString("tolerance", "")));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData3() {
        int count = trialList.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double y = Double.parseDouble((Settings.getString("bpm", ""))) + Double.parseDouble((Settings.getString("tolerance", "")));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData4() {
        int count = trialList.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double y = Double.parseDouble((Settings.getString("bpm", "")));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData5() {
        int count = syncTap.size() - 1;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = (Double.parseDouble((syncList.get(i)))/beatInterval)* Double.parseDouble((Settings.getString("bpm", "")));
            double y = Double.parseDouble((syncTap.get(i)));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData6() {
        int count = contTap.size()-1;

        for (int c =0; c<count;c++){
            contTap.set(c, Double.toString(Double.parseDouble((contTap.get(c))) - 2));
        }
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = (1000/Double.parseDouble(contList.get(i)))*60;
            double y = Double.parseDouble((contTap.get(i)));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData7() {
        int count = trialList.size();
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = Double.parseDouble((f.getString(nChart  + "bpm", ""))) + ((Double.parseDouble((f.getString(nChart  + "tolerance", "")))*17)/beatInterval)*Double.parseDouble((f.getString(nChart  + "bpm", "")));
            double y = i;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData8() {
        int count = trialList.size();
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double y = i;
            double x = Double.parseDouble((f.getString(nChart  + "bpm", ""))) - ((Double.parseDouble((f.getString(nChart  + "tolerance", "")))*17)/beatInterval)*Double.parseDouble((f.getString(nChart  + "bpm", "")));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData10() {
        int count = trialList.size();
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double y = i;
            double x = Double.parseDouble(f.getString(nChart  + "bpm", ""));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }


}
