package com.my.newproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linear1;
    private LinearLayout linear5;
    private LinearLayout linear3;
    private Button tap;
    private Button play;
    private Button settings;
    private Button send;
    private Button about;
    private TextView too_soon_red;
    private TextView too_soon_yellow;
    private TextView perfect_green;
    private TextView too_late_yellow;
    private TextView too_late_red;
    private TextView today_progress_label;
    private SeekBar today_progress;
    private TextView total_progress_label;
    private SeekBar total_progress;
    private FloatingActionButton fab;

    private double time = 0;
    private double elapsed_time = 0;
    private double tapTime = 0;
    private double beatTime = 0;
    private double period = 0;
    private double soundID = 0;
    private double playSound = 0;
    private double latency = 0;
    private double i = 0;
    private String latencyString = "";
    private double tapCounter = 0;
    private double beatInterval = 0;
    private double beatCounter = 0;
    private double audibleBeats = 0;
    private double quietBeats = 0;
    private double multiplier = 0;
    private double j = 0;
    private double soundID2 = 0;
    private double soundID3 = 0;
    private double asynSum = 0;
    private double asynMean = 0;
    private double iAsynPercentage = 0;
    private double accuracyP = 0;
    private double startTimeline = 0;
    private double iMakeTimeline = 0;
    private double iMakeSyncContList = 0;
    private double iMakeSyncContList2 = 0;
    private double iMap = 0;
    private double day = 0;
    private double iMap2 = 0;
    private String username = "";
    private double lastAsyn = 0;
    private String data = "";
    private double k = 0;
    private String dataContinuation = "";
    private String dataSynchronization = "";
    private double numTry = 0;
    private String file = "";
    private double l = 0;
    private double longestList = 0;
    private double m = 0;
    private double tooLateSyncPercent = 0;
    private double tooSoonSyncPercent = 0;
    private double perfectSyncPercent = 0;
    private double isChecked = 0;
    private double tooLateContPercent = 0;
    private double perfectContPercent = 0;
    private double tooSoonContPercent = 0;
    private String feedbackPostText = "";
    private String feedbackSyncPost = "";
    private String feedbackCont = "";
    private String accuracyPercent = "";
    private String accuracyRep = "";
    private ProgressDialog pd;
    private boolean dialogIsShowing = false;
    private boolean countdownIsRunning = false;
    private boolean canTap = false;
    private int fab_play = 0;

    private ArrayList<String> latencySelection = new ArrayList<String>();
    private ArrayList<Double> asynList = new ArrayList<Double>();
    private ArrayList<Double> beatSoundList = new ArrayList<Double>();
    private ArrayList<Double> timeline = new ArrayList<Double>();
    private ArrayList<Double> tapTimeList = new ArrayList<Double>();
    private ArrayList<Double> audibleBeatsList = new ArrayList<Double>();
    private ArrayList<Double> quietBeatsList = new ArrayList<Double>();
    private ArrayList<Double> tapSoundList = new ArrayList<Double>();
    private ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> syncMapList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> contMapList = new ArrayList<>();
    private ArrayList<Double> accuracyPercentList = new ArrayList<Double>();

    private Timer _timer = new Timer();
    private Calendar calendar = Calendar.getInstance();
    private TimerTask timer;
    private SoundPool sp;
    private Intent intent = new Intent();
    private SharedPreferences fAsyn;
    private SharedPreferences f;
    private SharedPreferences Settings;
    private AlertDialog.Builder d;
    private CountDownTimer countdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        initializeLogic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
//            case R.id.action_back:
//                return true;
            case R.id.action_settings:
                intent.setClass(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("latency", getIntent().getStringExtra("latency"));
                intent.putExtra("username", getIntent().getStringExtra("username"));
                intent.putExtra("bpm", getIntent().getStringExtra("bpm"));
                intent.putExtra("audible_beat", getIntent().getStringExtra("audible_beat"));
                intent.putExtra("quiet_beat", getIntent().getStringExtra("quiet_beat"));
                intent.putExtra("repetition", getIntent().getStringExtra("repetition"));
                intent.putExtra("isCheckedIm", getIntent().getStringExtra("isCheckedIm"));
                startActivity(intent);
                if (play.getText().toString().equals("Stop"))
                    timer.cancel();
                finish();
                return true;
            case R.id.save:
                _makeFile();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
				intent.putExtra("subject", username + latency);
				intent.putExtra("body", data);
				startActivity(intent);
                String filename = "data.txt";
                String string = data;
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.about:
                d.setTitle("About");
                d.setMessage("This application is made to practice internal timing. \n\nMade by Julien Lussiez");
                d.create().show();
                return true;
        }
        return false;}

    private void  initialize() {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear5 = (LinearLayout) findViewById(R.id.linear5);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        tap = (Button) findViewById(R.id.tap);
        play = (Button) findViewById(R.id.play);
        settings = (Button) findViewById(R.id.settings);
        send = (Button) findViewById(R.id.send);
        about = (Button) findViewById(R.id.about);
        too_soon_red = (TextView) findViewById(R.id.too_soon_red);
        too_soon_yellow = (TextView) findViewById(R.id.too_soon_yellow);
        perfect_green = (TextView) findViewById(R.id.perfect_green);
        too_late_yellow = (TextView) findViewById(R.id.too_late_yellow);
        too_late_red = (TextView) findViewById(R.id.too_late_red);
        today_progress_label = (TextView) findViewById(R.id.today_progress_label);
        today_progress = (SeekBar) findViewById(R.id.today_progress);
        total_progress_label = (TextView) findViewById(R.id.total_progress_label);
        total_progress = (SeekBar) findViewById(R.id.total_progress);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fAsyn = getSharedPreferences("fAsyn", Activity.MODE_PRIVATE);
        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        Settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);

        tap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if ((play.getText().toString().equals("Stop")&& canTap == true) || (fab_play == 1 && canTap == true)) {
                        tapCounter++;
                        calendar = Calendar.getInstance();
                        tapTime = calendar.getTimeInMillis();
                        tapTimeList.add(Double.valueOf(tapTime));
                        period = (tapTime - beatTime) - latency;

                        _makeAsynList();
                        _makeSoundTapList();
                        _feedback();}
                }
                return false;
            }

        });

        today_progress.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        total_progress.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {

                if (play.getText().toString().equals("Play")) {
                    play.setText("Stop");
                    beatCounter = (audibleBeats + quietBeats) * multiplier;
                    j = 0;
                    asynList.clear();
                    tapTimeList.clear();
                    tapSoundList.clear();
                    accuracyPercentList.clear();
                    _setColors();

                    countdown = new CountDownTimer((long)(4*beatInterval), (long)beatInterval - 10) {

                        public void onTick(long millisUntilFinished) {
                            tap.setText("" + Math.round((float) millisUntilFinished / beatInterval));
                        }

                        public void onFinish() {
                            tap.setText("Tap");
                            _startTimer();
                        }
                    }.start();
                }
                else {
                    if (timer != null)
                        timer.cancel();
                    countdown.cancel();
                    canTap = false;
                    play.setText("Play");
                    tap.setText("Press Play");
                }
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intent.setClass(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("latency", getIntent().getStringExtra("latency"));
                intent.putExtra("username", getIntent().getStringExtra("username"));
                intent.putExtra("bpm", getIntent().getStringExtra("bpm"));
                intent.putExtra("audible_beat", getIntent().getStringExtra("audible_beat"));
                intent.putExtra("quiet_beat", getIntent().getStringExtra("quiet_beat"));
                intent.putExtra("repetition", getIntent().getStringExtra("repetition"));
                intent.putExtra("isCheckedIm", getIntent().getStringExtra("isCheckedIm"));
                startActivity(intent);
                if (play.getText().toString().equals("Stop"))
                    timer.cancel();
                finish();
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                pd = ProgressDialog.show(MainActivity.this, "Save file", "Saving...");
                new Thread(new Runnable() {
                    public void run() {
                        _makeFile();
//				intent.setAction(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
//				intent.putExtra("subject", username + latency);
//				intent.putExtra("body", data);
//				startActivity(intent);
//                String filename = "data.txt";
//                String string = data;
//                FileOutputStream outputStream;
//
//                try {
//                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//                    outputStream.write(string.getBytes());
//                    outputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                        String h;
                        TextView result;
                        try {
                            h = DateFormat.format(username + "MM-dd-yyyyy-h-mmssaa", System.currentTimeMillis()).toString();
                            // this will create a new name everytime and unique
                            File root = new File(Environment.getExternalStorageDirectory(), "Inner Pulse Data");
                            // if external memory exists and folder with name Notes
                            if (!root.exists()) {
                                root.mkdirs(); // this will create folder.
                            }
                            File filepath = new File(root, h + ".txt");  // file path to save
                            FileWriter writer = new FileWriter(filepath);
                            writer.append(data.toString());
                            writer.flush();
                            writer.close();



                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }).start();

            }
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Context context = getApplicationContext();
                    CharSequence text = "File Saved in 'Inner Pulse Data' Directory";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    pd.dismiss();
                }
            };
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                d.setTitle("About");
                d.setMessage("This application is made to practice internal timing. \n\nMade by Julien Lussiez");
                d.create().show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                if (fab_play == 0) {
                    fab.setImageResource(R.drawable.ic_stop_black_24dp);
                    fab_play = 1;
                    beatCounter = (audibleBeats + quietBeats) * multiplier;
                    j = 0;
                    asynList.clear();
                    tapTimeList.clear();
                    tapSoundList.clear();
                    accuracyPercentList.clear();
                    _setColors();

                    countdown = new CountDownTimer((long)(4*beatInterval), (long)beatInterval - 10) {

                        public void onTick(long millisUntilFinished) {
                            tap.setText("" + Math.round((float) millisUntilFinished / beatInterval));
                        }

                        public void onFinish() {
                            tap.setText("Tap");
                            _startTimer();
                        }
                    }.start();
                }

                else{
                    if (timer != null)
                        timer.cancel();
                    countdown.cancel();
                    canTap = false;
                    tap.setText("Press Play");
                    fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    fab_play = 0;
                }
            }
        });

    }
    private void  initializeLogic() {
        _getSettings2();
//		audibleBeats = 16;
//		quietBeats = 16;
//		multiplier = 1;
//		beatInterval = 1000;
        sp = new SoundPool((int)(1), AudioManager.STREAM_MUSIC, 0);
        soundID = sp.load(getApplicationContext(), R.raw.audible_beat, 1);
        soundID2 = sp.load(getApplicationContext(), R.raw.silent_beat, 1);
        soundID3 = sp.load(getApplicationContext(), R.raw.ding, 1);
        i = 100;
        for(int _repeat27 = 0; _repeat27 < (int)(multiplier); _repeat27++) {
            for(int _repeat29 = 0; _repeat29 < (int)(audibleBeats); _repeat29++) {
                beatSoundList.add(Double.valueOf(0));
            }
            for(int _repeat30 = 0; _repeat30 < (int)(quietBeats); _repeat30++) {
                beatSoundList.add(Double.valueOf(1));
            }
        }
        if (f.getString("longestList", "").equals("")) {

        }
        else {
            longestList = Double.parseDouble(f.getString("longestList", ""));
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if (play.getText().toString().equals("Stop") || fab_play == 1) {
            timer.cancel();
            play.setText("Play");
            fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            fab_play = 0;
        }
    }
    private void _startTimer () {
        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        canTap = true;
                        countdown.cancel();
                        if ((play.getText().toString().equals("Stop") && !(beatCounter == 0)) || (fab_play == 1 && !(beatCounter == 0))) {
                            if (!(j == beatSoundList.size())) {
                                if (beatSoundList.get((int)(j)).doubleValue() == 0) {
                                    sp.stop((int)(soundID));
                                    playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                }
                                else {
                                    sp.stop((int)(soundID));
                                    playSound = sp.play((int)(soundID2), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                }
                                j++;
                            }
                            calendar = Calendar.getInstance();
                            beatTime = calendar.getTimeInMillis();
                            beatCounter--;

                        }
                        else {
                            canTap = false;
                            playSound = sp.play((int)(soundID3), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                            play.setText("Play");
                            fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                            fab_play = 0;
                            tap.setText("Press Play");
                            timer.cancel();
                            double a = 1;
                            if (tapSoundList.contains(a)){
                                _makeSyncContList();
                                _syncRepPercentage();
                                _contRepPercentage();
                                _accuracyPercentage2(asynList);
                                _accuracyPercentage2(audibleBeatsList);
                                _accuracyPercentage2(quietBeatsList);
                                _feedbackPost();
                                if (beatCounter == 0) {
                                    _storeList2(asynList, "T");
                                    _storeList2(audibleBeatsList, "S");
                                    _storeList2(quietBeatsList, "C");
                                    _storeList2(accuracyPercentList, "A");
                                    numTry++;
                                    f.edit().putString("numTry", String.valueOf((long) (numTry))).commit();
                                    _updateProgress();
                                }
                            }
                            else if (!asynList.isEmpty()) {
                                _accuracyPercentage2(asynList);
                                accuracyPercent = "Accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(0)).doubleValue())).concat("%"));
                                d.setTitle("Feedback");
                                d.setMessage(accuracyPercent);
                                d.create().show();
                                if (beatCounter == 0) {
                                    _storeList2(asynList, "T");
                                    _storeList2(accuracyPercentList, "A");
                                    numTry++;
                                    f.edit().putString("numTry", String.valueOf((long) (numTry))).commit();
                                    _updateProgress();
                                }
                            }
                        }
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(timer, (int)(0), (int)(beatInterval));
    }
    private void _makeAsynList () {
        if ((period / beatInterval) > 0.5d) {
            if (Math.abs(tapTime) < 1) {
                asynList.add((Double.valueOf((beatTime - latency) - beatInterval))*(beatInterval/1000));

            }
            else {
                asynList.add((Double.valueOf(period - beatInterval))*(beatInterval/1000));

            }
        }
        else {
            if (Math.abs(tapTime) < 1) {
                asynList.add((Double.valueOf(beatTime))*(beatInterval/1000));

            }
            else {
                asynList.add((Double.valueOf(period))*(beatInterval/1000));

            }
        }
    }
    private void _makeSyncContList () {
        audibleBeatsList.clear();
        quietBeatsList.clear();
        iMakeSyncContList = 0;
        for(int _repeat10 = 0; _repeat10 < (int)(asynList.size()); _repeat10++) {
            if (tapSoundList.get((int)(iMakeSyncContList)).doubleValue() == 0) {
                audibleBeatsList.add(Double.valueOf(asynList.get((int)(iMakeSyncContList)).doubleValue()));
            }
            else {
                quietBeatsList.add(Double.valueOf(asynList.get((int)(iMakeSyncContList)).doubleValue()));
            }
            iMakeSyncContList++;
        }
    }
    private void _makeSoundTapList () {
        if (beatSoundList.get((int)(j - 1)).doubleValue() == 0) {
            tapSoundList.add(Double.valueOf(0));
        }
        else {
            tapSoundList.add(Double.valueOf(1));
        }
    }
    private void _updateProgress () {
        if (today_progress.getProgress() == 100) {
            today_progress.setProgress((int)20);
            today_progress_label.setText("Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%"))));
            total_progress.setProgress((int)total_progress.getProgress() + 1);
            total_progress_label.setText("Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%")));
        }
        else {
            today_progress.setProgress((int)today_progress.getProgress() + 20);
            today_progress_label.setText("Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%"))));
            total_progress.setProgress((int)total_progress.getProgress() + 1);
            total_progress_label.setText("Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%")));
            if (today_progress.getProgress() == 100) {
                day++;
            }
        }
        f.edit().putString("today_progress", String.valueOf((long)(today_progress.getProgress()))).commit();
        f.edit().putString("today_progress_label", "Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%")))).commit();
        f.edit().putString("total_progress", String.valueOf((long)(total_progress.getProgress()))).commit();
        f.edit().putString("total_progress_label", "Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%"))).commit();
        f.edit().putString("day", String.valueOf((long)(day))).commit();
    }
    private void _feedback () {
        _setColors();
        if (asynList.size() == 0) {

        }
        else {
            lastAsyn = asynList.get((int)(asynList.size() - 1)).doubleValue();
            if (((-(beatInterval/2) < lastAsyn) && (lastAsyn < -(beatInterval/20))) || (lastAsyn == -(beatInterval/2))) {
                too_soon_red.setBackgroundColor(0xFFF44336);

            }
            else {
                if (((-(beatInterval/10) < lastAsyn) && (lastAsyn < -(beatInterval/20))) || (lastAsyn == -(beatInterval/10))) {
                    too_soon_yellow.setBackgroundColor(0xFFFFEB3B);

                }
                else {
                    if (((-(beatInterval/20) < lastAsyn) && (lastAsyn < (beatInterval/20))) || (lastAsyn == -(beatInterval/20))) {
                        perfect_green.setBackgroundColor(0xFF4CAF50);

                    }
                    else {
                        if (((beatInterval/20 < lastAsyn) && (beatInterval/10 < 100)) || (lastAsyn == 20)) {
                            too_late_yellow.setBackgroundColor(0xFFFFEB3B);

                        }
                        else {
                            if ((((beatInterval/10) < lastAsyn) && (lastAsyn < (beatInterval/2))) || (lastAsyn == (beatInterval/10))) {
                                too_late_red.setBackgroundColor(0xFFF44336);

                            }
                            else {
                                too_late_red.setBackgroundColor(0xFFF44336);
//								too_soon_red.setBackgroundColor(0xFFF44336);
                            }
                        }
                    }
                }
            }
        }
    }
    private void _setColors () {
        too_soon_red.setBackgroundColor(0xFFE0E0E0);
        too_soon_yellow.setBackgroundColor(0xFFE0E0E0);
        perfect_green.setBackgroundColor(0xFFE0E0E0);
        too_late_yellow.setBackgroundColor(0xFFE0E0E0);
        too_late_red.setBackgroundColor(0xFFE0E0E0);
    }
    private void _makeFile () {
        data = "";
        k = 0;
        l = 0;
        for(int _repeat208 = 0; _repeat208 < (int)(longestList); _repeat208++) {
            for(int _repeat248 = 0; _repeat248 < (int)(numTry); _repeat248++) {
                if (!f.getString("T".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
                    data = data.concat(f.getString("T".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
                }
                else {
                    data = data.concat("Null,");
                }
                l++;
            }
            l = 0;
            k++;
            data = data.concat("\n");
        }
        data = data.concat("\n\n");
        k = 0;
        l = 0;
        for(int _repeat276 = 0; _repeat276 < (int)(longestList); _repeat276++) {
            for(int _repeat278 = 0; _repeat278 < (int)(numTry); _repeat278++) {
                if (!f.getString("S".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
                    data = data.concat(f.getString("S".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
                }
                else {
                    data = data.concat("Null,");
                }
                l++;
            }
            l = 0;
            k++;
            data = data.concat("\n");
        }
        data = data.concat("\n\n");
        k = 0;
        l = 0;
        for(int _repeat391 = 0; _repeat391 < (int)(longestList); _repeat391++) {
            for(int _repeat393 = 0; _repeat393 < (int)(numTry); _repeat393++) {
                if (!f.getString("C".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
                    data = data.concat(f.getString("C".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
                }
                else {
                    data = data.concat("Null,");
                }
                l++;
            }
            l = 0;
            k++;
            data = data.concat("\n");
        }
        data = data.concat("\n\n");
        k = 0;
        l = 0;
        for(int _repeat429 = 0; _repeat429 < (int)(4); _repeat429++) {
            for(int _repeat431 = 0; _repeat431 < (int)(numTry); _repeat431++) {
                if (!f.getString("A".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
                    data = data.concat(f.getString("A".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
                }
                else {
                    data = data.concat("Null,");
                }
                l++;
            }
            l = 0;
            k++;
            data = data.concat("\n");
        }
    }
    private void _syncRepPercentage () {
        m = 0;
        tooLateSyncPercent = 0;
        tooSoonSyncPercent = 0;
        perfectSyncPercent = 0;
        while(true) {
            if (m == audibleBeatsList.size()) {
                tooSoonSyncPercent = (tooSoonSyncPercent / audibleBeatsList.size()) * 100;
                perfectSyncPercent = (perfectSyncPercent / audibleBeatsList.size()) * 100;
                tooLateSyncPercent = (tooLateSyncPercent / audibleBeatsList.size()) * 100;



                break;
            }
            else {
                if (audibleBeatsList.get((int)(m)).doubleValue() > 50) {
                    tooLateSyncPercent++;
                }
                else {
                    if (audibleBeatsList.get((int)(m)).doubleValue() < -50) {
                        tooSoonSyncPercent++;
                    }
                    else {
                        perfectSyncPercent++;
                    }
                }
                m++;
            }
        }
    }
    private void _contRepPercentage () {
        m = 0;
        tooLateContPercent = 0;
        tooSoonContPercent = 0;
        perfectContPercent = 0;
        while(true) {
            if (m == quietBeatsList.size()) {
                tooSoonContPercent = (tooSoonContPercent / quietBeatsList.size()) * 100;
                perfectContPercent = (perfectContPercent / quietBeatsList.size()) * 100;
                tooLateContPercent = (tooLateContPercent / quietBeatsList.size()) * 100;



                break;
            }
            else {
                if (quietBeatsList.get((int)(m)).doubleValue() > 50) {
                    tooLateContPercent++;
                }
                else {
                    if (quietBeatsList.get((int)(m)).doubleValue() < -50) {
                        tooSoonContPercent++;
                    }
                    else {
                        perfectContPercent++;
                    }
                }
                m++;
            }
        }
    }
    private void _getSettings2 () {
        if (Settings.getString("latency", "").equals("")) {

        }
        else {
            latency = Double.parseDouble(Settings.getString("latency", ""));

        }
        if (Settings.getString("bpm", "").equals("")) {
            beatInterval = 1000;
        }
        else {
            beatInterval = (60/(Double.parseDouble(Settings.getString("bpm", "")))*1000);

        }
        if (Settings.getString("audible_beat", "").equals("")) {
            audibleBeats = 4;
        }
        else {
            audibleBeats = Double.parseDouble(Settings.getString("audible_beat", ""));
        }
        if (Settings.getString("quiet_beat", "").equals("")) {
            quietBeats = 4;
        }
        else {
            quietBeats = Double.parseDouble(Settings.getString("quiet_beat", ""));
        }
        if (Settings.getString("repetition", "").equals("")) {
            multiplier = 1;
        }
        else {
            multiplier = Double.parseDouble(Settings.getString("repetition", ""));
        }
        if (Settings.getString("username", "").equals("")) {

        }
        else {
            username = Settings.getString("username", "");
        }
        if (Settings.getString("isChecked", "").equals("")) {

        }
        else {
            isChecked = Double.parseDouble(Settings.getString("isChecked", ""));
        }
        if (isChecked == 1) {
            linear3.setVisibility(View.VISIBLE);
        }
        else {
            linear3.setVisibility(View.GONE);
        }
        if (f.getString("numTry", "").equals("")) {

        }
        else {
            numTry = Double.parseDouble(f.getString("numTry", ""));

        }
        if (f.getString("today_progress", "").equals("")) {

        }
        else {
            day = Double.parseDouble(f.getString("day", ""));
            today_progress_label.setText(f.getString("today_progress_label", ""));
            total_progress_label.setText(f.getString("total_progress_label", ""));
            today_progress.setProgress((int)Double.parseDouble(f.getString("today_progress", "")));
            total_progress.setProgress((int)Double.parseDouble(f.getString("total_progress", "")));
        }
    }
    private void _feedbackPost () {
        if (perfectSyncPercent > 75) {
            feedbackSyncPost = "In time ".concat(String.valueOf((long)(perfectSyncPercent)).concat("% of time during synchronization phase : Well Done. \n"));
        }
        else {
            if (tooSoonSyncPercent > tooLateSyncPercent) {
                feedbackSyncPost = "Too fast ".concat(String.valueOf((long)(tooSoonSyncPercent)).concat("% of time during synchronization phase : Slow Down. \n"));
            }
            else {
                if (tooSoonSyncPercent < tooLateSyncPercent) {
                    feedbackSyncPost = "Too slow ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% of time during synchronization phase : Speed Up. \n"));
                }
                else {
                    feedbackSyncPost = "Too slow and too fast ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% of time during synchronization phase : Adjust your timing. \n"));
                }
            }
        }
        if (perfectContPercent > 75) {
            feedbackCont = "In time ".concat(String.valueOf((long)(perfectContPercent)).concat("% of time during continuation phase : Well Done. "));
        }
        else {
            if (tooSoonContPercent > tooLateContPercent) {
                feedbackCont = "Too fast ".concat(String.valueOf((long)(tooSoonContPercent)).concat("% of time during continuation phase : Slow Down. "));
            }
            else {
                if (tooSoonContPercent < tooLateContPercent) {
                    feedbackCont = "Too slow ".concat(String.valueOf((long)(tooLateContPercent)).concat("% of time during continuation phase : Speed Up. "));
                }
                else {
                    feedbackCont = "Too slow and too fast ".concat(String.valueOf((long)(tooLateContPercent)).concat("% of time during continuation phase : Adjust your timing. "));
                }
            }
        }
        accuracyPercent = "Total Accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(0)).doubleValue())).concat("%\n")).concat("Synchronization accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(1)).doubleValue())).concat("%\n")).concat("Continuation accuracy  : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(2)).doubleValue())).concat("%\n\n"))));
        accuracyRep = "Perfect : Sync. ".concat(String.valueOf((long)(perfectSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(perfectContPercent)).concat("%\n"))).concat("Too Fast : Sync. ".concat(String.valueOf((long)(tooSoonSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(tooSoonContPercent)).concat("%\n")))).concat("Too Slow : Sync. ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(tooLateContPercent)).concat("%"))));
        d.setTitle("Feedback");
        d.setMessage(accuracyPercent.concat(feedbackSyncPost).concat(feedbackCont.concat("\n\n".concat(accuracyRep))));
        d.create().show();
    }
    private void _accuracyPercentage2 (final ArrayList<Double> _list) {
        if (_list.size() == 0) {

        }
        else {
            asynSum = 0;
            asynMean = 0;
            iAsynPercentage = 0;
            accuracyP = 0;
            for(int _repeat18 = 0; _repeat18 < (int)(_list.size()); _repeat18++) {
                asynSum = asynSum + Math.abs(_list.get((int)(iAsynPercentage)).doubleValue());
                iAsynPercentage++;
            }
            asynMean = (asynSum * 2) / _list.size();
            accuracyP = (beatInterval - asynMean) / (beatInterval / 100);
            accuracyPercentList.add(Double.valueOf(accuracyP));
        }
    }
    private void _storeList2 (final ArrayList<Double> _list, final String _string) {
        if (asynList.size() > longestList) {
            longestList = asynList.size();
            f.edit().putString("longestList", String.valueOf((long)(asynList.size()))).commit();
        }
        k = 0;
        f.edit().putString(_string.concat(String.valueOf((long)(numTry)).concat(String.valueOf((long)(k)))), "Try ".concat(String.valueOf((long)(numTry))).concat(",")).commit();
        while(true) {
            if (k == _list.size()) {
                break;
            }
            else {
                f.edit().putString(_string.concat(String.valueOf((long)(numTry)).concat(String.valueOf((long)(k + 1)))), String.valueOf((long)(_list.get((int)(k)).doubleValue())).concat(",")).commit();
                k++;
            }
        }
    }

}
