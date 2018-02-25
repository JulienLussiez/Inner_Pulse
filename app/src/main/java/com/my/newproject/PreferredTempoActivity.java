package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.Calendar;

public class PreferredTempoActivity extends AppCompatActivity {

    private Button tap;
    private ProgressBar tapProgressBar;
    private ProgressBar trial1;
    private ProgressBar trial2;
    private ProgressBar trial3;
    private TextView trial1_text;
    private TextView trial2_text;
    private TextView trial3_text;
    private FloatingActionButton fab;

    private int blue = 0xFF03a9f4;
    private int colorSlow = 0xFF26a69a;
    private int trial = 1;
    private double periodMs = 0;
    private String perListString = "";

    private Intent intent = new Intent();
    private int perCounter;
    private int tapCounter;
    private int firstTapInterval = 0;

    private double firstTap;
    private double secondTap;
    private double thirdTap;
    private double tapTime = 0;

    private Calendar calendar = Calendar.getInstance();



    private ArrayList<Double> tapTimeList = new ArrayList<Double>();
    private ArrayList<Double> perList = new ArrayList<Double>();
    private int fab_play;
    private boolean canTap;
    private DescriptiveStatistics stats;
    private double perMean;
    private double perSD;
    private double bpmMean;
    private DescriptiveStatistics meanTrials;
    private TextView preferredTempo;
    private SharedPreferences Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_tempo);
        _init();
        _tap();
        _play();
        _setProgressBar();
    }

    private void _init() {
        tap = (Button) findViewById(R.id.tap);
        tapProgressBar = (ProgressBar) findViewById(R.id.tapProgressBar);
        trial1 = (ProgressBar) findViewById(R.id.trial1);
        trial2 = (ProgressBar) findViewById(R.id.trial2);
        trial3 = (ProgressBar) findViewById(R.id.trial3);
        trial1_text = (TextView) findViewById(R.id.trial1_text);
        trial2_text = (TextView) findViewById(R.id.trial2_text);
        trial3_text = (TextView) findViewById(R.id.trial3_text);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        preferredTempo = (TextView) findViewById(R.id.preferred_tempo);

        Settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);

        if((Settings.getString("preferredTempo", "")).equals("")){
            preferredTempo.setText("120 BPM");
        }
        else{
            preferredTempo.setText(Settings.getString("preferredTempo", "") + " BPM");
        }
    }

    private void _tap() {
        tap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if ((fab_play == 1 && canTap == true)) {
                        tapCounter++;
//                        tapProgressBar.incrementProgressBy(1);
                        calendar = Calendar.getInstance();
                        tapTime = calendar.getTimeInMillis();
                        tapTimeList.add(Double.valueOf(tapTime));

                        _makePeriod();
                        Log.d("per", String.valueOf(periodMs));

                        if (firstTapInterval == 1){
                            _makePeriodList();
                        }
                        if(tapProgressBar.getProgress() < 30){
                            tapProgressBar.incrementProgressBy(1);
                        }
                        else{
                            tapProgressBar.setProgress(0);
                            if(trial == 1){
                                trial1.setProgress(1);
                                _statistics();
                                trial++;
                                _stop();
                            }
                            else {
                                if(trial == 2){
                                    trial2.setProgress(1);
                                    _statistics();
                                    trial++;
                                    _stop();
                                }
                                else {
                                    if(trial == 3){
                                        trial3.setProgress(1);
                                        _statistics();
                                        trial = 1;
                                        _stop();
                                    }
                                }
                            }
                        }
                        firstTapInterval = 1;
                    }

                }
                return false;
            }
        });
    }

    private void _stop() {
        canTap = false;
        tap.setText("Tap");
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        fab_play = 0;
    }

    private void _play (){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                if (fab_play == 0) {
                    if (trial == 1){
                        _setProgressBar();
                        trial1_text.setText("TRIAL 1");
                        trial2_text.setText("TRIAL 2");
                        trial3_text.setText("TRIAL 3");
                        meanTrials = new DescriptiveStatistics();
                    }
                    fab.setImageResource(R.drawable.ic_stop_black_24dp);
                    fab_play = 1;
                    firstTapInterval = 0;
                    perCounter = 1;
                    tapCounter = 0;
                    perListString = "";
                    perList.clear();
                    tapTimeList.clear();
                    canTap = true;
                }

                else{
                    canTap = false;
                    tap.setText("Tap");
                    _setProgressBar();
                    fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    fab_play = 0;
                }
            }
        });

    }

    private void _setProgressBar () {
        trial1.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
        trial2.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
        trial3.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
        tapProgressBar.getProgressDrawable().setColorFilter(blue, android.graphics.PorterDuff.Mode.SRC_IN);

        trial1_text.setBackgroundColor(colorSlow);
        trial2_text.setBackgroundColor(colorSlow);
        trial3_text.setBackgroundColor(colorSlow);

        trial1.setProgress(0);
        trial2.setProgress(0);
        trial3.setProgress(0);
        tapProgressBar.setProgress(0);

    }


    private void _makePeriod (){
        if (perCounter == 1){
            if (tapCounter == 1){
                firstTap = tapTime;
                perCounter++;
            }
            else {
                firstTap = tapTime;
                periodMs = firstTap - thirdTap;
                perCounter++;
            }
        }
        else {
            if (perCounter == 2){
                secondTap = tapTime;
                periodMs = secondTap - firstTap;
                perCounter++;
            }
            else {
                thirdTap = tapTime;
                periodMs = thirdTap - secondTap;
                perCounter = 1;
            }
        }
    }

    private void _makePeriodList () {
        perList.add((Double.valueOf(periodMs)));
        perListString = perListString + (int)periodMs + ",\n";
    }

    private void _statistics () {
        stats = new DescriptiveStatistics();
        for( int i = 0; i < perList.size(); i++) {
            stats.addValue(perList.get(i));
        }

        perMean = Math.round(stats.getMean());
        bpmMean = (1000/perMean)*60;
        perSD = Math.round(stats.getStandardDeviation());
//        perCV = Math.round(100*(perSD/perMean));
        Log.d("mean", Double.toString(perMean));
        if (trial == 1){
            trial1_text.setText(Integer.toString((int)bpmMean) + " BPM");
            meanTrials.addValue((int)bpmMean);
        }
        if (trial == 2){
            trial2_text.setText(Integer.toString((int)bpmMean) + " BPM");
            meanTrials.addValue((int)bpmMean);
        }
        if (trial == 3){
            trial3_text.setText(Integer.toString((int)bpmMean) + " BPM");
            meanTrials.addValue((int)bpmMean);
            preferredTempo.setText(Integer.toString((int) meanTrials.getMean()));
            Settings.edit().putString("preferredTempo", Integer.toString((int) meanTrials.getMean())).commit();
        }
    }

    @Override
    public void onBackPressed() {
        intent.setClass(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
        finish();
    }
}
