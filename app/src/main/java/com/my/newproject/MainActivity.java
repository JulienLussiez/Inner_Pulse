package com.my.newproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linear1;
    private LinearLayout linear5;
    private LinearLayout linear3;
    private LinearLayout linear20;
    private Button tap;
    private Button play;
    private Button settings;
    private Button send;
    private Button about;
    private TextView tempo_text;
    private TextView too_soon_red;
    private TextView too_soon_yellow;
    private TextView perfect_green;
    private TextView too_late_yellow;
    private TextView too_late_red;
    private TextView today_progress_label;
    private SeekBar today_progress;
    private SeekBar feedback_per;
    private TextView total_progress_label;
    private TextView num_try_text;
    private TextView combo_text;
    private TextView period_ms;
    private TextView period_mean;
    private SeekBar total_progress;
    private ProgressBar comboBar;
    private ProgressBar rushingBar;
    private ProgressBar draggingBar;
    private FloatingActionButton fab;
    private CustomSeekBar seekbar0;


    private int inc = 0;
    private int comboEnd = 0;
    private int tapToBegin = 0;
    private int comboMax;
    private int startMF = 0;
    private int perCounter = 1;
    private int cd = 1;
    private int firstTapInterval = 0;
    private int firstCd = 1;
    private double firstTap = 0;
    private double secondTap = 0;
    private double thirdTap = 0;
    private double periodMs = 0;
    private int endMF = 0;
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
    private String perListString = "";
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
    private double perSum = 0;
    private double asynMean = 0;
    private double perMean = 0;
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
    private double lastPer = 0;
    private String data = "";
    private double k = 0;
    private String dataContinuation = "";
    private String dataSynchronization = "";
    private double numTry = 0;
    private int combo = 0;
    private String file = "";
    private double l = 0;
    private double longestList = 0;
    private double m = 0;
    private double tooLateSyncPercent = 0;
    private double tooSoonSyncPercent = 0;
    private double perfectSyncPercent = 0;
    private double isChecked = 1;
    private double tooLateContPercent = 0;
    private double perfectContPercent = 0;
    private double tooSoonContPercent = 0;
    private String feedbackPostText = "";
    private String feedbackSyncPost = "";
    private String feedbackCont = "";
    private String accuracyPercent = "";
    private String accuracyRep = "";
    private ProgressDialog pd;
    private String fb_post;
    private boolean dialogIsShowing = false;
    private boolean countdownIsRunning = false;
    private boolean canTap = false;
    private int fab_play = 0;
    int j2 = 0;
    int k2 = 0;
    private int comboOn = 0;

    private ArrayList<String> latencySelection = new ArrayList<String>();
    private ArrayList<Double> asynList = new ArrayList<Double>();
    private ArrayList<Double> perList = new ArrayList<Double>();
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

    private Button btnSeekTo;
    private CustomSeekBar seekbar;
    private EditText txtSeekProgress;
    private ToggleButton btnToogleSeek;

    private float totalSpan = 0;
    private float redSpanLeft = 0;
    private float yellowSpanRightLeft = 0;
    private float greenSpan = 0;
    private float yellowSpanRight = 0;
    private float redSpanRight = 0;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
    private DescriptiveStatistics stats;
    private double perSD;
    private double perCV;
    private double perACF;

    double x[];
    double y[];
    private int slow;
    private int good;
    private int fast;
    private int nChart;
    private double lastlastPer;
    private double toleranceBpm;
    private TextView score_text;
    private String syncList;
    private String contList;
    private String syncTap;
    private String contTap;

    private int colorSlow = 0xFF26a69a;
    private int colorSlow2 = 0xFF26a69a;
    private int colorGood = 0xFF00897b;
    private int colorFast = 0xFF00796b;
    private double toleranceConversion;
    private String toleranceConversionString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        initializeLogic();

        seekbar = ((CustomSeekBar) findViewById(R.id.seekBar0));
        initDataToSeekbar();

    }

    private void initDataToSeekbar() {
        seekbar.setMax((int)beatInterval*2);
        seekbar.setProgress((int)beatInterval);
        totalSpan = (float)beatInterval;
        redSpanLeft = (float)(beatInterval/2);
//        yellowSpanRightLeft = (float)(beatInterval/7);
//        greenSpan = ((float)(beatInterval/20));
//        yellowSpanRight = (float)(beatInterval/7);
        redSpanRight = (float)(beatInterval/2);

        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = ((redSpanLeft / totalSpan) * 100);
        Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.very_slow;
        progressItemList.add(mProgressItem);
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (yellowSpanRightLeft / totalSpan) * 100;
        mProgressItem.color = R.color.slow;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (greenSpan / totalSpan) * 100;
        mProgressItem.color = R.color.good;
        progressItemList.add(mProgressItem);
        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (yellowSpanRight / totalSpan) * 100;
        mProgressItem.color = R.color.fast;
        progressItemList.add(mProgressItem);
        // greyspan
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (redSpanRight / totalSpan) * 100;
        mProgressItem.color = R.color.very_fast;
        progressItemList.add(mProgressItem);

        seekbar.initData(progressItemList);
        seekbar.invalidate();

        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
//            case R.id.action_back:
//                return true;

            case R.id.action_data:
                intent.setClass(getApplicationContext(), DataActivity.class);
                startActivity(intent);
                finish();
                return true;

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
//            case R.id.save:
////                _makeFile(startMF, startMF, endMF);
//				intent.setAction(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
//				intent.putExtra("subject", username + latency);
//				intent.putExtra("body", perListString);
//				startActivity(intent);
//                String filename = "data.txt";
//                String string = perListString;
//                FileOutputStream outputStream;
//
//                try {
//                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//                    outputStream.write(string.getBytes());
//                    outputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
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
        linear20 = (LinearLayout) findViewById(R.id.linear20);
        seekbar0 = (CustomSeekBar) findViewById(R.id.seekBar0);
        tap = (Button) findViewById(R.id.tap);
        play = (Button) findViewById(R.id.play);
        settings = (Button) findViewById(R.id.settings);
        send = (Button) findViewById(R.id.send);
        about = (Button) findViewById(R.id.about);
        too_soon_red = (TextView) findViewById(R.id.too_soon_red);
//        too_soon_yellow = (TextView) findViewById(R.id.too_soon_yellow);
        perfect_green = (TextView) findViewById(R.id.perfect_green);
//        too_late_yellow = (TextView) findViewById(R.id.too_late_yellow);
        too_late_red = (TextView) findViewById(R.id.too_late_red);
        today_progress_label = (TextView) findViewById(R.id.today_progress_label);
        today_progress = (SeekBar) findViewById(R.id.today_progress);
        total_progress_label = (TextView) findViewById(R.id.total_progress_label);
        total_progress = (SeekBar) findViewById(R.id.total_progress);
        num_try_text = (TextView) findViewById(R.id.num_try_text);
        combo_text = (TextView) findViewById(R.id.combo_text);
        score_text = (TextView) findViewById(R.id.score_text);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        period_ms = (TextView) findViewById(R.id.period_ms);
        period_mean = (TextView) findViewById(R.id.period_mean);
        feedback_per = (SeekBar) findViewById(R.id.feedback_per);
        tempo_text = (TextView) findViewById(R.id.tempo);
        comboBar = (ProgressBar) findViewById(R.id.comboBar);
        rushingBar = (ProgressBar) findViewById(R.id.rushingBar);
        draggingBar = (ProgressBar) findViewById(R.id.draggingBar);

        fAsyn = getSharedPreferences("fAsyn", Activity.MODE_PRIVATE);
        f = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        Settings = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);

        _setColors();
        _setProgressBar();

        feedback_per.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        tap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (tapToBegin == 0) {
                        tapToBegin = 1;
                        fab.setImageResource(R.drawable.ic_stop_black_24dp);
                        seekbar.setProgress(((int)beatInterval));
                        _setColors();
                        _setProgressBar();
                        fab_play = 1;
                        combo = 0;
                        comboOn = 0;
                        comboMax = 0;
                        String record = f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "");
                        score_text.setText("Max : " + comboMax + " | Record : " + record);
                        combo_text.setText("Combo : " + Integer.toString(combo));
                        beatCounter = ((audibleBeats + quietBeats) * multiplier)+4;
                        firstTapInterval = 0;
                        comboBar.setProgress(0);
                        j = 0;
                        firstCd = 1;
                        perCounter = 1;
                        tapCounter = 0;
                        cd = 1;
                        perListString = "";
                        syncList = "";
                        contList = "";
                        syncTap = "";
                        contTap = "";
                        asynList.clear();
                        perList.clear();
                        tapTimeList.clear();
                        tapSoundList.clear();
                        accuracyPercentList.clear();
                        _startTimer();}

                        if ((play.getText().toString().equals("Stop")&& canTap == true) || (fab_play == 1 && canTap == true)) {
                            tapCounter++;
                            calendar = Calendar.getInstance();
                            tapTime = calendar.getTimeInMillis();
                            tapTimeList.add(Double.valueOf(tapTime));
                            period = (tapTime - beatTime) - latency;

                            _makePeriod();
//                        _makeAsynList();
//                        _makeSoundTapList();
//                        _feedback();

                            if (firstTapInterval == 1){
                                _makePeriodList();
                                _feedbackPer();
//                            feedback_per.setProgress(((int)beatInterval*2) - (int)periodMs);
//                            seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
                            }
                            firstTapInterval = 1;
                        }
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

  /*      play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {

                if (play.getText().toString().equals("Play")) {
                    play.setText("Stop");
                    beatCounter = (audibleBeats + quietBeats) * multiplier;
                    j = 0;
                    asynList.clear();
                    perList.clear();
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
        });*/
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


//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View _v) {
//                pd = ProgressDialog.show(MainActivity.this, "Save file", "Saving...");
//                new Thread(new Runnable() {
//                    public void run() {
//                        _makeFile(0, 2);
////				intent.setAction(Intent.ACTION_VIEW);
////				intent.setData(Uri.parse("mailto:lussiez.julien@gmail.com"));
////				intent.putExtra("subject", username + latency);
////				intent.putExtra("body", data);
////				startActivity(intent);
////                String filename = "data.txt";
////                String string = data;
////                FileOutputStream outputStream;
////
////                try {
////                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
////                    outputStream.write(string.getBytes());
////                    outputStream.close();
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
//                        String h;
//                        TextView result;
//                        try {
//                            h = DateFormat.format(username + "MM-dd-yyyyy-h-mmssaa", System.currentTimeMillis()).toString();
//                            // this will create a new name everytime and unique
//                            File root = new File(Environment.getExternalStorageDirectory(), "Inner Pulse Data");
//                            // if external memory exists and folder with name Notes
//                            if (!root.exists()) {
//                                root.mkdirs(); // this will create folder.
//                            }
//                            File filepath = new File(root, h + ".txt");  // file path to save
//                            FileWriter writer = new FileWriter(filepath);
//                            writer.append(data.toString());
//                            writer.flush();
//                            writer.close();
//
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        handler.sendEmptyMessage(0);
//                    }
//                }).start();
//
//            }
//            Handler handler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    Context context = getApplicationContext();
//                    CharSequence text = "File Saved in 'Inner Pulse Data' Directory";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                    pd.dismiss();
//                }
//            };
//        });

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
                    _setColors();
                    _setProgressBar();
                    fab.setImageResource(R.drawable.ic_stop_black_24dp);
                    seekbar.setProgress(((int)beatInterval));
                    fab_play = 1;
                    tapToBegin = 1;
                    combo = 0;
                    comboOn = 0;
                    comboMax = 0;
                    String record = f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "");
                    score_text.setText("Max : " + comboMax + " | Record : " + record);
                    combo_text.setText("Combo : " + Integer.toString(combo));
                    beatCounter = ((audibleBeats + quietBeats) * multiplier)+4;
                    firstTapInterval = 0;
                    comboBar.setProgress(0);
                    j = 0;
                    firstCd = 1;
                    perCounter = 1;
                    tapCounter = 0;
                    cd = 1;
                    perListString = "";
                    syncList = "";
                    contList = "";
                    syncTap = "";
                    contTap = "";
                    asynList.clear();
                    perList.clear();
                    tapTimeList.clear();
                    tapSoundList.clear();
                    accuracyPercentList.clear();
                    _startTimer();

//                    countdown = new CountDownTimer((long)(4*beatInterval), (long)beatInterval - 10) {
//
//                        public void onTick(long millisUntilFinished) {
//                            tap.setText("" + Math.round((float) millisUntilFinished / beatInterval));
//                        }
//
//                        public void onFinish() {
//                            tap.setText("Tap");
//                            _startTimer();
//                        }
//                    }.start();
                }

                else{
                    if (timer != null)
                        timer.cancel();
//                    countdown.cancel();
                    canTap = false;
                    tap.setText("Tap to start");
                    _setColors();
                    _setProgressBar();
                    seekbar.setProgress(((int)beatInterval));
                    fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    fab_play = 0;
                    tapToBegin = 0;
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
        for (int i = 0; i < 4; i++){
            beatSoundList.add(Double.valueOf(0));
        }
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
//                        canTap = true;
//                        countdown.cancel();
                        calendar = Calendar.getInstance();
                        beatTime = calendar.getTimeInMillis();
                        if ((play.getText().toString().equals("Stop") && !(beatCounter == 0)) || (fab_play == 1 && !(beatCounter == 0))) {

                            if (true) {
                                if (comboOn == 0) {
                                    if (cd == 5){
                                        comboOn = 1;
                                        firstCd = 0;
                                        _setColors();
                                        _setProgressBar();
                                        seekbar.setProgress(((int)beatInterval));
                                        sp.stop((int)(soundID));
//                                        playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                        tap.setText("Tap");
                                        canTap = true;
                                    }
                                    else {
                                        if (cd == 1 || cd == 2 || cd == 3 || cd == 4){
                                            sp.stop((int)(soundID));
                                            playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                            if (firstCd == 1) {
                                                tap.setText(String.valueOf(cd));

                                            }
                                        }
                                        else {
                                            sp.stop((int)(soundID));
                                            playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                        }
                                    }
                                }
                                else {
                                    tap.setText("Tap");
                                    canTap = true;
                                    sp.stop((int)(soundID));
                                    playSound = sp.play((int)(soundID2), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                                }
                                j++;
                            }


//                            if (!(j == beatSoundList.size())) {
//                                if (beatSoundList.get((int)(j)).doubleValue() == 0) {
//                                    if (cd == 0){
//                                        sp.stop((int)(soundID));
//                                        playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
//                                        tap.setText("Tap");
//                                        canTap = true;
//                                    }
//                                    else {
//                                        if (cd == 4 || cd == 3 || cd == 2 || cd == 1){
//                                            sp.stop((int)(soundID));
//                                            playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
//                                            tap.setText(String.valueOf(cd));
//                                        }
//                                        else {
//                                            sp.stop((int)(soundID));
//                                            playSound = sp.play((int)(soundID), 1.0f, 1.0f, 1, (int)(0), 1.0f);
//                                        }
//                                    }
//                                }
//                                else {
//                                    tap.setText("Tap");
//                                    canTap = true;
//                                    sp.stop((int)(soundID));
//                                    playSound = sp.play((int)(soundID2), 1.0f, 1.0f, 1, (int)(0), 1.0f);
//                                }
//                                j++;
//                            }
                            beatCounter--;
                            cd++;

                        }
                        else {
                            canTap = false;
                            playSound = sp.play((int)(soundID3), 1.0f, 1.0f, 1, (int)(0), 1.0f);
                            play.setText("Play");
                            fab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                            fab_play = 0;
                            seekbar.setProgress(((int)beatInterval));
                            tap.setText("Tap to start");
                            timer.cancel();
                            double a = 1;
                            Date date = new Date();
                            SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
                            f.edit().putString(Integer.toString((int) numTry) + "title", (int)numTry + ". " + username + " " + Settings.getString("bpm", "") + "bpm " + "± " + toleranceConversionString + " " +  ft.format(date)).commit();
                            f.edit().putString(Integer.toString((int) numTry), perListString).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "sync", syncList).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "cont", contList).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "syncTap", syncTap).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "contTap", contTap).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "tolerance", Settings.getString("tolerance", "")).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "beats", Settings.getString("quiet_beat", "") ).apply();
                            f.edit().putString(Integer.toString((int) numTry) + "bpm", Settings.getString("bpm", "")).apply();
                            f.edit().putString(Double.toString(numTry), String.valueOf((long)(perList.size()))).commit();
                            _statistics();
                            _feedbackPostPer();
                            if (beatCounter == 0) {
                                numTry++;
//                                _storeComboMax();
                                f.edit().putString("numTry", String.valueOf((long) (numTry))).commit();
                                nChart = (int) numTry - 1;
                                f.edit().putString("fbPost" + String.format(String.valueOf(nChart)) ,fb_post).apply();
                                f.edit().putString("nChart",Integer.toString(nChart)).apply();
                            }
//                            if (tapSoundList.contains(a)){
//                                _makeSyncContList();
//                                _syncRepPercentage();
//                                _contRepPercentage();
//                                _accuracyPercentage2(asynList);
//                                _accuracyPercentage2(audibleBeatsList);
//                                _accuracyPercentage2(quietBeatsList);
////                                _meanPeriod(perList);
//                                _feedbackPost();
//                                if (beatCounter == 0) {
//                                    _storeList2(asynList, "T");
//                                    _storeList2(audibleBeatsList, "S");
//                                    _storeList2(quietBeatsList, "C");
//                                    _storeList2(accuracyPercentList, "A");
//                                    numTry++;
//                                    f.edit().putString("numTry", String.valueOf((long) (numTry))).commit();
//                                    _updateProgress();
//                                }
//                            }
//                            else if (!asynList.isEmpty()) {
//                                _accuracyPercentage2(asynList);
//                                _meanPeriod(perList);
//                                accuracyPercent = "Accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(0)).doubleValue())).concat("%"));
//                                d.setTitle("Feedback");
//                                d.setMessage(accuracyPercent);
//                                d.create().show();
//                                if (beatCounter == 0) {
//                                    _storeList2(asynList, "T");
//                                    _storeList2(accuracyPercentList, "A");
//                                    numTry++;
//                                    f.edit().putString("numTry", String.valueOf((long) (numTry))).commit();
//                                    _updateProgress();
//                                }
//                            }
                        }
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(timer, (int)(0), (int)(beatInterval));
    }

//    private void _makeAsynList () {
//        if ((period / beatInterval) > 0.5d) {
//            if (Math.abs(tapTime) < 1) {
//                asynList.add((Double.valueOf((beatTime - latency) - beatInterval))*(beatInterval/1000));
//
//            }
//            else {
//                asynList.add((Double.valueOf(period - beatInterval))*(beatInterval/1000));
//
//            }
//        }
//        else {
//            if (Math.abs(tapTime) < 1) {
//                asynList.add((Double.valueOf(beatTime))*(beatInterval/1000));
//
//            }
//            else {
//                asynList.add((Double.valueOf(period))*(beatInterval/1000));
//
//            }
//        }
//    }

    private void _makePeriodList () {
        perList.add((Double.valueOf(periodMs)));
        perListString = perListString + (int)periodMs + ",\n";

        if (cd <= 5){
            syncList = syncList + (int)periodMs + ",\n";
            syncTap = syncTap + tapCounter + ",\n";
        }
//        else {
//            contList = contList + (int)periodMs + ",\n";
//            contTap = contTap + tapCounter + ",\n";
//        }
    }

    private void _makePeriod (){
        if (perCounter == 1){
            if (tapCounter == 1){
                firstTap = tapTime;
                period_ms.setText((Math.round((1000/beatInterval)*60)) + " bpm " + "(" + Math.round(Float.parseFloat(Double.toString(beatInterval))) + "ms)");
                perCounter++;
            }
            else {
                firstTap = tapTime;
                periodMs = firstTap - thirdTap;
                period_ms.setText((Math.round((1000/periodMs)*60)) + " bpm " + "(" + Math.round(Float.parseFloat(Double.toString(periodMs))) + "ms)");
//                perList.add(periodMs);
                perCounter++;
            }
        }
        else {
            if (perCounter == 2){
                secondTap = tapTime;
                periodMs = secondTap - firstTap;
                period_ms.setText((Math.round((1000/periodMs)*60)) + " bpm " + "(" + Math.round(Float.parseFloat(Double.toString(periodMs))) + "ms)");
//                perList.add(periodMs);
                perCounter++;
            }
            else {
                thirdTap = tapTime;
                periodMs = thirdTap - secondTap;
                period_ms.setText((Math.round((1000/periodMs)*60)) + " bpm " + "(" + Math.round(Float.parseFloat(Double.toString(periodMs))) + "ms)");
//                perList.add(periodMs);
                perCounter = 1;
            }
        }
    }

//    private void _makeSyncContList () {
//        audibleBeatsList.clear();
//        quietBeatsList.clear();
//        iMakeSyncContList = 0;
//        for(int _repeat10 = 0; _repeat10 < (int)(asynList.size()); _repeat10++) {
//            if (tapSoundList.get((int)(iMakeSyncContList)).doubleValue() == 0) {
//                audibleBeatsList.add(Double.valueOf(asynList.get((int)(iMakeSyncContList)).doubleValue()));
//            }
//            else {
//                quietBeatsList.add(Double.valueOf(asynList.get((int)(iMakeSyncContList)).doubleValue()));
//            }
//            iMakeSyncContList++;
//        }
//    }
//    private void _makeSoundTapList () {
//        if (beatSoundList.get((int)(j - 1)).doubleValue() == 0) {
//            tapSoundList.add(Double.valueOf(0));
//        }
//        else {
//            tapSoundList.add(Double.valueOf(1));
//        }
//    }
//    private void _updateProgress () {
//        num_try_text.setText("Progression : " + String.valueOf((int)numTry) + "/400");
//        if (today_progress.getProgress() == 100) {
//            today_progress.setProgress((int)20);
//            today_progress_label.setText("Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%"))));
//            total_progress.setProgress((int)total_progress.getProgress() + 1);
//            total_progress_label.setText("Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%")));
//        }
//        else {
//            today_progress.setProgress((int)today_progress.getProgress() + 20);
//            today_progress_label.setText("Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%"))));
//            total_progress.setProgress((int)total_progress.getProgress() + 1);
//            total_progress_label.setText("Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%")));
//            if (today_progress.getProgress() == 100) {
//                day++;
//            }
//        }
//        f.edit().putString("today_progress", String.valueOf((long)(today_progress.getProgress()))).commit();
//        f.edit().putString("today_progress_label", "Day ".concat(String.valueOf((long)(day))).concat(" : ".concat(String.valueOf((long)(today_progress.getProgress())).concat("%")))).commit();
//        f.edit().putString("total_progress", String.valueOf((long)(total_progress.getProgress()))).commit();
//        f.edit().putString("total_progress_label", "Total : ".concat(String.valueOf((long)(total_progress.getProgress())).concat("%"))).commit();
//        f.edit().putString("day", String.valueOf((long)(day))).commit();
//    }
//    private void _feedback () {
//        _setColors();
//        if (asynList.size() == 0) {
//
//        }
//        else {
//            lastAsyn = asynList.get((int)(asynList.size() - 1)).doubleValue();
//            if (((-(beatInterval/2) < lastAsyn) && (lastAsyn < -(beatInterval/20))) || (lastAsyn == -(beatInterval/2))) {
//                too_soon_red.setBackgroundColor(0xFFF44336);
//
//            }
//            else {
//                if (((-(beatInterval/10) < lastAsyn) && (lastAsyn < -(beatInterval/20))) || (lastAsyn == -(beatInterval/10))) {
//                    too_soon_yellow.setBackgroundColor(0xFFFFEB3B);
//
//                }
//                else {
//                    if (((-(beatInterval/20) < lastAsyn) && (lastAsyn < (beatInterval/20))) || (lastAsyn == -(beatInterval/20))) {
//                        perfect_green.setBackgroundColor(0xFF4CAF50);
//
//                    }
//                    else {
//                        if (((beatInterval/20 < lastAsyn) && (beatInterval/10 < 100)) || (lastAsyn == 20)) {
//                            too_late_yellow.setBackgroundColor(0xFFFFEB3B);
//                        }
//                        else {
//                            if ((((beatInterval/10) < lastAsyn) && (lastAsyn < (beatInterval/2))) || (lastAsyn == (beatInterval/10))) {
//                                too_late_red.setBackgroundColor(0xFFF44336);
//                            }
//                            else {
//                                too_late_red.setBackgroundColor(0xFFF44336);
////								too_soon_red.setBackgroundColor(0xFFF44336);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

//    private void _feedbackPer () {
//
//        if (perList.size() == 0) {
//
//        }
//        else {
//            lastPer = periodMs;
//            if ((beatInterval/1000 < lastPer) && (lastPer < beatInterval/1.1) || (lastPer == beatInterval/1000)) {
//                if (comboOn == 0) {
//
//                }
//                else {
//                    combo = 0;
//                    comboOn = 0;
//                    cd = 1;
//                }
//
//                comboBar.setProgress(0);
//                combo_text.setText("Combo : " + Integer.toString(combo));
////                seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_rewind_black_24dp));
//
//            }
//            else {
//                if ((beatInterval/1.1 < lastPer) && (lastPer < beatInterval/1.03) || (lastPer == beatInterval/1.1)) {
//                    if (comboOn == 0) {
//
//                    }
//                    else {
//                        combo = 0;
//                        comboOn = 0;
//                        cd = 1;
//                    }
//                    comboBar.setProgress(0);
//                    combo_text.setText("Combo : " + Integer.toString(combo));
////                    seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_rewind_black_24dp));
//
//                }
//                else {
//                    if ((beatInterval/1.03 < lastPer) && (lastPer < beatInterval/0.97) || (lastPer == beatInterval/1.03)) {
//                        if (comboOn == 0){
//
//                        }
//                        else {
//                            combo++;
//                            comboBar.incrementProgressBy(1);
//                            if (combo%6 == 0){
//                                comboBar.setProgress(0);
//                            }
//                            combo_text.setText("Combo : " + Integer.toString(combo));
//                        }
//
////                        seekbar.setThumb(getResources().getDrawable( R.drawable.ic_arrow_drop_down_black_24dp));
//
//                    }
//                    else {
//                        if ((beatInterval/0.97 < lastPer) && (lastPer < beatInterval/0.85) || (lastPer == beatInterval/0.97)) {
//                            if (comboOn == 0) {
//
//                            }
//                            else {
//                                combo = 0;
//                                comboOn = 0;
//                                cd = 1;
//                            }
//                            comboBar.setProgress(0);
//                            combo_text.setText("Combo : " + Integer.toString(combo));
////                            seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_forward_black_24dp));
//
//                        }
//                        else {
//                            if ((beatInterval/0.85 < lastPer) && (lastPer < beatInterval/0.1) || (lastPer == beatInterval/0.85)) {
//                                if (comboOn == 0) {
//
//                                }
//                                else {
//                                    combo = 0;
//                                    comboOn = 0;
//                                    cd = 1;
//                                }
//                                comboBar.setProgress(0);
//                                combo_text.setText("Combo : " + Integer.toString(combo));
////                                seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_forward_black_24dp));
//                            }
//                            else {
//                                if (comboOn == 0) {
//
//                                }
//                                else {
//                                    combo = 0;
//                                    comboOn = 0;
//                                    cd = 1;
//                                }
//                                comboBar.setProgress(0);
//                                combo_text.setText("Combo : " + Integer.toString(combo));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

       private void _setColors () {
        too_soon_red.setBackgroundColor(colorSlow);
//        too_soon_yellow.setBackgroundColor(0xFFE0E0E0);
        perfect_green.setBackgroundColor(colorSlow);
//        too_late_yellow.setBackgroundColor(0xFFE0E0E0);
        too_late_red.setBackgroundColor(colorSlow);
    }

    private void _setProgressBar () {
        colorSlow2 = colorSlow;
        rushingBar.setProgress(0);
        draggingBar.setProgress(0);
        comboBar.setProgress(0);
        rushingBar.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
        draggingBar.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
        comboBar.getProgressDrawable().setColorFilter(colorSlow, android.graphics.PorterDuff.Mode.SRC_IN);
    }


    private void _feedbackPer () {
        int colorIncrement = 0;
        int colorCombo = colorSlow;

        if (perList.size() == 0) {

        }
        else {
            lastPer = periodMs;
            Log.d("per", Double.toString(lastPer));
            if (tapCounter > 1){
                lastlastPer = perList.get(perList.size()-1);
                Log.d("per", Double.toString(perList.get(perList.size()-1)));
            }
            if ((beatInterval/1000 < lastPer) && (lastPer < beatInterval - toleranceBpm) || (lastPer == beatInterval/1000)) {
                if (comboOn == 0) {

                }
                else {
                    too_soon_red.setBackgroundColor(colorFast + colorIncrement);
                    too_late_red.setBackgroundColor(colorSlow + colorIncrement);
                    perfect_green.setBackgroundColor(colorSlow + colorIncrement);
                    colorSlow2 = colorSlow;
                    comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
                    rushingBar.setProgress(1);
                    draggingBar.setProgress(0);
                    comboBar.setProgress(0);
//                    comboBar.setVisibility(View.INVISIBLE);
//                    comboBar.setVisibility(View.GONE);
                    seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
                    combo = 0;
                    comboOn = 0;
                    cd = 1;
                }

                comboBar.setProgress(0);
                combo_text.setText("Combo : " + Integer.toString(combo));
//                seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_rewind_black_24dp));

            }
                else {
                    if ((beatInterval - toleranceBpm < lastPer) && (lastPer < beatInterval + toleranceBpm) || (lastPer == beatInterval - toleranceBpm)) {
                        if (lastPer - lastlastPer < -(toleranceBpm * beatInterval) ) {
                            if (comboOn == 0) {

                            }
                            else {
                                too_soon_red.setBackgroundColor(colorFast + colorIncrement);
                                too_late_red.setBackgroundColor(colorSlow + colorIncrement);
                                perfect_green.setBackgroundColor(colorSlow + colorIncrement);
                                colorSlow2 = colorSlow;
                                comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
                                rushingBar.setProgress(1);
                                draggingBar.setProgress(0);
                                comboBar.setProgress(0);
                                seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
//                                comboBar.setVisibility(View.INVISIBLE);
//                                comboBar.setVisibility(View.GONE);
                                combo = 0;
                                comboOn = 0;
                                cd = 1;
                            }
                        }
                        else {
                            if (lastPer - lastlastPer > (toleranceBpm * beatInterval)) {
                                if (comboOn == 0) {

                                }
                                else {
                                    comboBar.setVisibility(View.INVISIBLE);
//                                    comboBar.setVisibility(View.GONE);
                                    too_soon_red.setBackgroundColor(colorSlow + colorIncrement);
                                    too_late_red.setBackgroundColor(colorFast + colorIncrement);
                                    perfect_green.setBackgroundColor(colorSlow + colorIncrement);
                                    colorSlow2 = colorSlow;
                                    comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
                                    rushingBar.setProgress(0);
                                    draggingBar.setProgress(1);
                                    comboBar.setProgress(0);
                                    seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
                                    combo = 0;
                                    comboOn = 0;
                                    cd = 1;
                                }
                            }
                            else {
                                if (comboOn == 0){

                                }
                                else {
                                    too_soon_red.setBackgroundColor(colorSlow + colorIncrement);
                                    too_late_red.setBackgroundColor(colorSlow + colorIncrement);
                                    perfect_green.setBackgroundColor(colorFast + colorIncrement);
                                    rushingBar.setProgress(0);
                                    draggingBar.setProgress(0);
//                                    perfect_green.setPadding(64, 64,64,64);
                                    comboBar.setVisibility(View.VISIBLE);
                                    comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
//                                    comboBar.setVisibility(View.GONE);
                                    combo++;
                                    contList = contList + (int)lastPer + ",\n";
                                    contTap = contTap + tapCounter + ",\n";
                                    if (combo > comboMax){
                                        comboMax = combo;
                                        score_text.setText("Max : " + comboMax + " | Record : " + f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), ""));
                                    }
                                    _storeComboMax();
                                    comboBar.incrementProgressBy(1);
                                    Log.d("combo", Integer.toString(comboBar.getProgress()));
                                    if (combo%10 == 0 && comboEnd == 0){
                                        comboEnd = 1;
                                        comboBar.setProgress(10);
                                        comboBar.getProgressDrawable().setColorFilter(colorSlow2 + 100, android.graphics.PorterDuff.Mode.SRC_IN);

                                    }
                                    else {
                                        if (comboEnd == 1){
                                            comboEnd = 0;
                                            comboBar.setProgress(1);
                                        }
                                    }
                                    combo_text.setText("Combo : " + Integer.toString(combo));
                                }
                            }
                        }

//                        seekbar.setThumb(getResources().getDrawable( R.drawable.ic_arrow_drop_down_black_24dp));

                    }

                        else {
                            if ((beatInterval + toleranceBpm < lastPer) && (lastPer < beatInterval/0.1) || (lastPer == beatInterval + toleranceBpm)) {
                                if (comboOn == 0) {

                                }
                                else {
//                                    comboBar.setVisibility(View.INVISIBLE);
//                                    comboBar.setVisibility(View.GONE);
                                    too_soon_red.setBackgroundColor(colorSlow + colorIncrement);
                                    too_late_red.setBackgroundColor(colorFast + colorIncrement);
                                    perfect_green.setBackgroundColor(colorSlow + colorIncrement);
                                    rushingBar.setProgress(0);
                                    draggingBar.setProgress(1);
                                    comboBar.setProgress(0);
                                    colorSlow2 = colorSlow;
                                    comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
                                    seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
                                    combo = 0;
                                    comboOn = 0;
                                    cd = 1;
                                }
                                comboBar.setProgress(0);
                                combo_text.setText("Combo : " + Integer.toString(combo));
//                                seekbar.setThumb(getResources().getDrawable( R.drawable.ic_fast_forward_black_24dp));
                            }
                            else {
                                if (comboOn == 0) {

                                }
                                else {
//                                    comboBar.setVisibility(View.INVISIBLE);
//                                    comboBar.setVisibility(View.GONE);
                                    too_soon_red.setBackgroundColor(colorSlow + colorIncrement);
                                    too_late_red.setBackgroundColor(colorFast + colorIncrement);
                                    perfect_green.setBackgroundColor(colorSlow + colorIncrement);
                                    rushingBar.setProgress(0);
                                    draggingBar.setProgress(1);
                                    comboBar.setProgress(0);
                                    colorSlow2 = colorSlow;
                                    comboBar.getProgressDrawable().setColorFilter(colorSlow2, android.graphics.PorterDuff.Mode.SRC_IN);
                                    seekbar.setProgress(((int)beatInterval*2) - (int)periodMs);
                                    combo = 0;
                                    comboOn = 0;
                                    cd = 1;
                                }
                                comboBar.setProgress(0);
                                combo_text.setText("Combo : " + Integer.toString(combo));
                            }
                        }
                    }
                }
            }

    private void _statistics() {
        stats = new DescriptiveStatistics();
        for( int i = 0; i < perList.size(); i++) {
            stats.addValue(perList.get(i));
        }
        perMean = Math.round(stats.getMean());
        perSD = Math.round(stats.getStandardDeviation());
        perCV = Math.round(100*(perSD/perMean));
        Log.d("mean", Double.toString(perMean));
        period_mean.setText(Double.toString(perMean));

    }

//    private void _setColors () {
//        too_soon_red.setBackgroundColor(0xFFE0E0E0);
//        too_soon_yellow.setBackgroundColor(0xFFE0E0E0);
//        perfect_green.setBackgroundColor(0xFFE0E0E0);
//        too_late_yellow.setBackgroundColor(0xFFE0E0E0);
//        too_late_red.setBackgroundColor(0xFFE0E0E0);
//    }
//    private void _makeFile (int start, int startCounter, int end) {
//        data = "";
//        k = 0;
//        l = start;
//        for(int _repeat208 = 0; _repeat208 < (int)(longestList); _repeat208++) {
//            for(int _repeat248 = startCounter; _repeat248 < (int)(end); _repeat248++) {
//                if (!f.getString("T".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
//                    data = data.concat(f.getString("T".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
//                }
//                else {
//                    data = data.concat("Null,");
//                }
//                l++;
//            }
//            l = start;
//            k++;
//            data = data.concat("\n");
//        }
//        data = data.concat("\n\n");
//        k = 0;
//        l = start;
//        for(int _repeat276 = 0; _repeat276 < (int)(longestList); _repeat276++) {
//            for(int _repeat278 = startCounter; _repeat278 < (int)(end); _repeat278++) {
//                if (!f.getString("S".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
//                    data = data.concat(f.getString("S".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
//                }
//                else {
//                    data = data.concat("Null,");
//                }
//                l++;
//            }
//            l = start;
//            k++;
//            data = data.concat("\n");
//        }
//        data = data.concat("\n\n");
//        k = 0;
//        l = start;
//        for(int _repeat391 = 0; _repeat391 < (int)(longestList); _repeat391++) {
//            for(int _repeat393 = startCounter; _repeat393 < (int)(end); _repeat393++) {
//                if (!f.getString("C".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
//                    data = data.concat(f.getString("C".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
//                }
//                else {
//                    data = data.concat("Null,");
//                }
//                l++;
//            }
//            l = start;
//            k++;
//            data = data.concat("\n");
//        }
//        data = data.concat("\n\n");
//        k = 0;
//        l = start;
//        for(int _repeat429 = 0; _repeat429 < (int)(4); _repeat429++) {
//            for(int _repeat431 = startCounter; _repeat431 < (int)(end); _repeat431++) {
//                if (!f.getString("A".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), "").equals("")) {
//                    data = data.concat(f.getString("A".concat(String.valueOf((long)(l)).concat(String.valueOf((long)(k)))), ""));
//                }
//                else {
//                    data = data.concat("Null,");
//                }
//                l++;
//            }
//            l = start;
//            k++;
//            data = data.concat("\n");
//        }
//    }
//    private void _syncRepPercentage () {
//        m = 0;
//        tooLateSyncPercent = 0;
//        tooSoonSyncPercent = 0;
//        perfectSyncPercent = 0;
//        while(true) {
//            if (m == audibleBeatsList.size()) {
//                tooSoonSyncPercent = (tooSoonSyncPercent / audibleBeatsList.size()) * 100;
//                perfectSyncPercent = (perfectSyncPercent / audibleBeatsList.size()) * 100;
//                tooLateSyncPercent = (tooLateSyncPercent / audibleBeatsList.size()) * 100;
//
//
//
//                break;
//            }
//            else {
//                if (audibleBeatsList.get((int)(m)).doubleValue() > 50) {
//                    tooLateSyncPercent++;
//                }
//                else {
//                    if (audibleBeatsList.get((int)(m)).doubleValue() < -50) {
//                        tooSoonSyncPercent++;
//                    }
//                    else {
//                        perfectSyncPercent++;
//                    }
//                }
//                m++;
//            }
//        }
//    }
//    private void _contRepPercentage () {
//        m = 0;
//        tooLateContPercent = 0;
//        tooSoonContPercent = 0;
//        perfectContPercent = 0;
//        while(true) {
//            if (m == quietBeatsList.size()) {
//                tooSoonContPercent = (tooSoonContPercent / quietBeatsList.size()) * 100;
//                perfectContPercent = (perfectContPercent / quietBeatsList.size()) * 100;
//                tooLateContPercent = (tooLateContPercent / quietBeatsList.size()) * 100;
//
//
//
//                break;
//            }
//            else {
//                if (quietBeatsList.get((int)(m)).doubleValue() > 50) {
//                    tooLateContPercent++;
//                }
//                else {
//                    if (quietBeatsList.get((int)(m)).doubleValue() < -50) {
//                        tooSoonContPercent++;
//                    }
//                    else {
//                        perfectContPercent++;
//                    }
//                }
//                m++;
//            }
//        }
//    }

    private void _storeComboMax() {

        if (f.getString((Settings.getString("bpm", "")) + Settings.getString("tolerance", ""), "").equals("")){
            f.edit().putString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), Integer.toString(combo)).apply();
            score_text.setText("Max : " + comboMax + " | Record : " + f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "0"));

        }
        else {
            if (combo > Integer.parseInt(f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), ""))) {
                f.edit().putString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), Integer.toString(combo)).apply();
                score_text.setText("Max : " + comboMax + " | Record : " + f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "") );
            }
            else {
            }
        }
    }

    private void _getSettings2 () {
        if (f.getString((Settings.getString("bpm", "") + Settings.getString("tolerance", "")), "").equals("")){
            score_text.setText("Max : " + comboMax + " | Record : 0" );
            f.edit().putString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "0").apply();
        }
        else {
            score_text.setText("Max : " + comboMax + " | Record : "  + f.getString(Settings.getString("bpm", "") + Settings.getString("tolerance", ""), "") );
        }

        if (Settings.getString("latency", "").equals("")) {

        }
        else {
            latency = Double.parseDouble(Settings.getString("latency", ""));

        }
        if (Settings.getString("bpm", "").equals("")) {
            beatInterval = 1000;
            feedback_per.setMax(1000);
            Settings.edit().putString("bpm", "60").apply();

        }
        else {
            tempo_text.setText(Settings.getString("bpm", "") + " BPM ± " + toleranceConversionString + " " + Settings.getString(Settings.getString("bpm", ""), "") );
            beatInterval = (60/(Double.parseDouble(Settings.getString("bpm", "")))*1000);
            feedback_per.setMax((int)beatInterval*2);

        }
//        if (Settings.getString("tolerance", "").equals("")) {
//            toleranceBpm = 33;
//        }
//        else {
//            toleranceBpm = (Double.parseDouble(Settings.getString("tolerance", "")));
//            toleranceBpm = (toleranceBpm/Double.parseDouble(Settings.getString("bpm", ""))*beatInterval);
//            Log.d("test", Double.toString(toleranceBpm));
//
//        }
        if (Settings.getString("tolerance", "").equals("")) {
            toleranceBpm = 34;
            toleranceConversion = Math.round((toleranceBpm/beatInterval) * Double.parseDouble((Settings.getString("bpm", ""))));
            int value = (int) toleranceConversion;
            toleranceConversionString = Integer.toString(value);
            tempo_text.setText(Settings.getString("bpm", "") + " BPM ± " + toleranceConversionString + " " + Settings.getString(Settings.getString("bpm", ""), "") );
        }
        else {
//            toleranceBpm = (Double.parseDouble(Settings.getString("tolerance", ""))) * 17;
            toleranceBpm = (Double.parseDouble(Settings.getString("tolerance", "")));
            Log.d("tolerance", Double.toString(toleranceBpm));
//            toleranceBpm = (toleranceBpm/Double.parseDouble(Settings.getString("bpm", ""))*beatInterval);
            toleranceConversion = Math.round((toleranceBpm/beatInterval) * Double.parseDouble((Settings.getString("bpm", ""))));
            int value = (int) toleranceConversion;
            toleranceConversionString = Integer.toString(value);
            tempo_text.setText(Settings.getString("bpm", "") + " BPM ± " + toleranceConversionString + " " + Settings.getString(Settings.getString("bpm", ""), "") );
            Log.d("test", Double.toString(toleranceBpm));
            Log.d("test", Double.toString(toleranceConversion));

        }

        if (Settings.getString("audible_beat", "").equals("")) {
            audibleBeats = 0;
        }
        else {
            audibleBeats = Double.parseDouble(Settings.getString("audible_beat", ""));
        }
        if (Settings.getString("quiet_beat", "").equals("")) {
            quietBeats = 120;
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
            linear20.setVisibility(View.GONE);
            seekbar0.setVisibility(View.GONE);
        }
        else {
            linear3.setVisibility(View.VISIBLE);
            linear20.setVisibility(View.GONE);
            seekbar0.setVisibility(View.GONE);
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
            num_try_text.setText("Progression : " + f.getString("numTry", "") + "/400");
        }
        if (Settings.getString("send_start", "").equals("")) {
            startMF = 0;
        }
        else {
            startMF = (int) Double.parseDouble(Settings.getString("send_start", ""));

        }

        if (Settings.getString("send_end", "").equals("")) {
            endMF = (int) numTry;
        }
        else {
            if (Settings.getString("send_end", "").equals("0")){
                endMF = (int) numTry;
            }
            else {
                endMF = (int) Double.parseDouble(Settings.getString("send_end", ""));
            }
        }
    }

    private void _feedbackPostPer (){
        slow = 0;
        good = 0;
        fast = 0;

        String tendency_fb;

        for(i = 0; i < perList.size(); i++){
            if ((beatInterval/1000 < perList.get((int)i)) && (perList.get((int)i) < beatInterval/1.1) || (perList.get((int)i) == beatInterval/1000)) {
                fast += 3;
            }
            else {
                if ((beatInterval/1.1 < perList.get((int)i)) && (perList.get((int)i) < beatInterval/1.05) || (perList.get((int)i) == beatInterval/1.1)) {
                    fast += 3;
                }
                else {
                    if ((beatInterval/1.05 < perList.get((int)i)) && (perList.get((int)i) < beatInterval/0.95) || (perList.get((int)i) == beatInterval/1.05)) {
                        good++;
                    }
                    else {
                        if ((beatInterval/0.95 < perList.get((int)i)) && (perList.get((int)i) < beatInterval/0.85) || (perList.get((int)i) == beatInterval/0.95)) {
                            slow += 3;
                        }
                        else {
                            if ((beatInterval/0.85 < perList.get((int)i)) && (perList.get((int)i) < beatInterval/0.1) || (perList.get((int)i) == beatInterval/0.85)) {
                                slow += 3;
                            }
                            else {
                            }
                        }
                    }
                }
            }
        }



        if(fast > slow && fast > good || fast == good){
            tendency_fb = "Tendency to speed up : slow down";
        }
        else if (slow > fast && slow > good || slow == good) {
            tendency_fb = "Tendency to slow down : speed up";
        }
        else if (slow == fast) {
            tendency_fb = "Similar tendency to slow down/speed up : find your pace and stay focus to stabilize it";
        }
        else {
            tendency_fb = "Stable timing : keep it up";
        }
        String mean_fb = "Mean Velocity : " + String.format("%.0f", (1000/perMean) * 60) + " bpm";
        String sd_fb = "Variability :  " + "± " + String.format("%.0f", (perSD/perMean) * 60) + " bpm";
        String comboMaxString = "Combo : " + Integer.toString(comboMax);
        fb_post = mean_fb + "\n" + sd_fb + "\n" + comboMaxString +  "\n\n" + tendency_fb;

        d.setTitle("Feedback");
        d.setMessage(fb_post);
        d.setCancelable(false);
        d.setPositiveButton("See Chart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                intent.putExtra("afterSession", "1");
                intent.setClass(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        d.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                tapToBegin = 0;
            }
        });

        d.create().show();
    }

//    private void _feedbackPost () {
//        if (perfectSyncPercent > 75) {
//            feedbackSyncPost = "In time ".concat(String.valueOf((long)(perfectSyncPercent)).concat("% of time during synchronization phase : Well Done. \n"));
//        }
//        else {
//            if (tooSoonSyncPercent > tooLateSyncPercent) {
//                feedbackSyncPost = "Too fast ".concat(String.valueOf((long)(tooSoonSyncPercent)).concat("% of time during synchronization phase : Slow Down. \n"));
//            }
//            else {
//                if (tooSoonSyncPercent < tooLateSyncPercent) {
//                    feedbackSyncPost = "Too slow ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% of time during synchronization phase : Speed Up. \n"));
//                }
//                else {
//                    feedbackSyncPost = "Too slow and too fast ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% of time during synchronization phase : Adjust your timing. \n"));
//                }
//            }
//        }
//        if (perfectContPercent > 75) {
//            feedbackCont = "In time ".concat(String.valueOf((long)(perfectContPercent)).concat("% of time during continuation phase : Well Done. "));
//        }
//        else {
//            if (tooSoonContPercent > tooLateContPercent) {
//                feedbackCont = "Too fast ".concat(String.valueOf((long)(tooSoonContPercent)).concat("% of time during continuation phase : Slow Down. "));
//            }
//            else {
//                if (tooSoonContPercent < tooLateContPercent) {
//                    feedbackCont = "Too slow ".concat(String.valueOf((long)(tooLateContPercent)).concat("% of time during continuation phase : Speed Up. "));
//                }
//                else {
//                    feedbackCont = "Too slow and too fast ".concat(String.valueOf((long)(tooLateContPercent)).concat("% of time during continuation phase : Adjust your timing. "));
//                }
//            }
//        }
//        accuracyPercent = "Total Accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(0)).doubleValue())).concat("%\n")).concat("Synchronization accuracy : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(1)).doubleValue())).concat("%\n")).concat("Continuation accuracy  : ".concat(String.valueOf((long)(accuracyPercentList.get((int)(2)).doubleValue())).concat("%\n\n"))));
//        accuracyRep = "Perfect : Sync. ".concat(String.valueOf((long)(perfectSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(perfectContPercent)).concat("%\n"))).concat("Too Fast : Sync. ".concat(String.valueOf((long)(tooSoonSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(tooSoonContPercent)).concat("%\n")))).concat("Too Slow : Sync. ".concat(String.valueOf((long)(tooLateSyncPercent)).concat("% / Cont. ").concat(String.valueOf((long)(tooLateContPercent)).concat("%"))));
//        d.setTitle("Feedback");
//        d.setMessage(accuracyPercent.concat(feedbackSyncPost).concat(feedbackCont.concat("\n\n".concat(accuracyRep))));
//        d.create().show();
//    }
//    private void _accuracyPercentage2 (final ArrayList<Double> _list) {
//        if (_list.size() == 0) {
//
//        }
//        else {
//            asynSum = 0;
//            asynMean = 0;
//            iAsynPercentage = 0;
//            accuracyP = 0;
//            for(int _repeat18 = 0; _repeat18 < (int)(_list.size()); _repeat18++) {
//                asynSum = asynSum + Math.abs(_list.get((int)(iAsynPercentage)).doubleValue());
//                iAsynPercentage++;
//            }
//            asynMean = (asynSum * 2) / _list.size();
//            accuracyP = (beatInterval - asynMean) / (beatInterval / 100);
//            accuracyPercentList.add(Double.valueOf(accuracyP));
//        }
//    }
//
//    private void _meanPeriod (final ArrayList<Double> _list) {
//        if (_list.size() == 0) {
//
//        }
//        else {
//            perSum = 0;
//            perMean = 0;
//            iAsynPercentage = 0;
//            accuracyP = 0;
//            for(int _repeat18 = 0; _repeat18 < (int)(_list.size()); _repeat18++) {
//                perSum = perSum + Math.abs(_list.get((int)(iAsynPercentage)).doubleValue());
//                iAsynPercentage++;
//            }
//            perMean = perSum / _list.size();
////            period_mean.setText(Double.toString(perMean));
//        }
//    }
//
//    private void _storePerList (final ArrayList<Double> _list, final String _string){
//        f.edit().putString(Double.toString(numTry), String.valueOf((long)(perList.size()))).commit();
//    }
//
//    private void _storeList2 (final ArrayList<Double> _list, final String _string) {
//        if (asynList.size() > longestList) {
//            longestList = asynList.size();
//            f.edit().putString("longestList", String.valueOf((long)(asynList.size()))).commit();
//        }
//        k = 0;
//        f.edit().putString(_string.concat(String.valueOf((long)(numTry)).concat(String.valueOf((long)(k)))), "Try ".concat(String.valueOf((long)(numTry))).concat(",")).commit();
//        while(true) {
//            if (k == _list.size()) {
//                break;
//            }
//            else {
//                f.edit().putString(_string.concat(String.valueOf((long)(numTry)).concat(String.valueOf((long)(k + 1)))), String.valueOf((long)(_list.get((int)(k)).doubleValue())).concat(",")).commit();
//                k++;
//            }
//        }
//    }

}
