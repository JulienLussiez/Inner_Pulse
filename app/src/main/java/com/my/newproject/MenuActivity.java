package com.my.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button startFreeMode;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initialize();
    }

    private void initialize(){
        startFreeMode = (Button) findViewById(R.id.startFreeMode);

        startFreeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
