package com.my.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PreferredTempoActivity extends AppCompatActivity {

    private Button tap;
    private ProgressBar tapProgressBar;
    private ProgressBar trial1;
    private ProgressBar trial2;
    private ProgressBar trial3;
    private TextView trial1_text;
    private TextView trial2_text;
    private TextView trial3_text;

    private int blue = 0xFF03a9f4;
    private int colorSlow = 0xFF26a69a;
    private int trial = 1;

    private Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_tempo);
        _init();
        _tap();
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

    }

    private void _tap() {
        tap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if(tapProgressBar.getProgress() < 30){
                        tapProgressBar.incrementProgressBy(1);
                    }
                    else{
                        tapProgressBar.setProgress(0);
                        if(trial == 1){
                            trial1.setProgress(1);
                            trial++;
                        }
                        else {
                            if(trial == 2){
                                trial2.setProgress(1);
                                trial++;
                            }
                            else {
                                if(trial == 3){
                                    trial3.setProgress(1);
                                }
                            }
                        }
                    }
                }
                return false;
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

    @Override
    public void onBackPressed() {
        intent.setClass(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
        finish();
    }
}
