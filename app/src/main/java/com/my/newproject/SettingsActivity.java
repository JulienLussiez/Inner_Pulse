package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.appyvet.materialrangebar.RangeBar;

public class SettingsActivity extends AppCompatActivity {

	private LinearLayout linear1;
    private LinearLayout linear2;
	private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private LinearLayout linearTolerance;
	private TextView username_text;
	private TextView username;
	private EditText username_edit;
	private TextView latency_text;
	private TextView latency;
	private EditText latency_edit;
	private TextView bpm_text;
	private TextView random_text;
	private TextView bpm;
	private EditText bpm_edit;
    private TextView audible_beat_text;
    private TextView audible_beat;
    private EditText audible_beat_edit;
    private TextView quiet_beat_text;
    private TextView quiet_beat;
    private EditText quiet_beat_edit;
    private TextView repetition_text;
    private TextView repetition;
    private EditText repetition_edit;
	private Switch im_switch;
	private TextView send_start;
	private EditText send_start_edit;
	private TextView send_start_text;
	private TextView send_end;
	private EditText send_end_edit;
	private TextView send_end_text;
	private SeekBar seekBarBpm;
    private SeekBar seekBarTrials;
    private TextView trialsBeforeTempoChange;
    private ImageButton random;

	private String text = "";


	private Intent intent = new Intent();
	private SharedPreferences f;
	private TextView tolerance_text;
	private TextView tolerance;
	private EditText tolerance_edit;

	private int step = 1;
	private int max = 160;
	private int min = 40;

	private SeekBar seekBarTolerance;
	private int stepTolerance = 15;
	private int maxTolerance = 65;
	private int minTolerance = 20;

	private SeekBar seekBarBeats;
	private int stepBeats = 1;
	private int maxBeats = 200;
	private int minBeats = 30;
	private Switch reaction_time_switch;
	private Switch distractor_switch;
	private SeekBar seekBarFrequency;
	private TextView frequency_text;

	private int stepFrequency = 1;
	private int maxFrequency = 9;
	private int minFrequency = 3;
    private ImageButton sptButton;

    private int stepTrials = 1;
    private int maxTrials = 3;
    private int minTrials = 1;

    private RangeBar rangeBar;
	private String preferredTempo;
	private TextView spt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		username_text = (TextView) findViewById(R.id.username_text);
		username = (TextView) findViewById(R.id.username);
		username_edit = (EditText) findViewById(R.id.username_edit);
		latency_text = (TextView) findViewById(R.id.latency_text);
		latency = (TextView) findViewById(R.id.latency);
		latency_edit = (EditText) findViewById(R.id.latency_edit);
		bpm_text = (TextView) findViewById(R.id.bpm_text);
		bpm = (TextView) findViewById(R.id.bpm);
		bpm_edit = (EditText) findViewById(R.id.bpm_edit);
		tolerance_text = (TextView) findViewById(R.id.tolerance_text);
		tolerance = (TextView) findViewById(R.id.tolerance);
		tolerance_edit = (EditText) findViewById(R.id.tolerance_edit);
        audible_beat_text = (TextView) findViewById(R.id.audible_beat_text);
        audible_beat = (TextView) findViewById(R.id.audible_beat);
        audible_beat_edit = (EditText) findViewById(R.id.audible_beat_edit);
        quiet_beat_text = (TextView) findViewById(R.id.quiet_beat_text);
        quiet_beat = (TextView) findViewById(R.id.quiet_beat);
        quiet_beat_edit = (EditText) findViewById(R.id.quiet_beat_edit);
        repetition_text = (TextView) findViewById(R.id.repetition_text);
        repetition = (TextView) findViewById(R.id.repetition);
        repetition_edit = (EditText) findViewById(R.id.repetition_edit);
		im_switch = (Switch) findViewById(R.id.im_switch);
		reaction_time_switch = (Switch) findViewById(R.id.reaction_switch);
//		distractor_switch = (Switch) findViewById(R.id.distractor_switch);
		send_start_text = (TextView) findViewById(R.id.send_start_text);
		send_start = (TextView) findViewById(R.id.send_start);
		send_start_edit = (EditText) findViewById(R.id.send_start_edit);
		send_end_text = (TextView) findViewById(R.id.send_end_text);
		send_end = (TextView) findViewById(R.id.send_end);
		send_end_edit = (EditText) findViewById(R.id.send_end_edit);
		seekBarBpm = (SeekBar) findViewById(R.id.seekBarBpm);
		seekBarTolerance = (SeekBar) findViewById(R.id.seekbarTolerance);
		seekBarBeats = (SeekBar) findViewById(R.id.seekbarBeats);
		seekBarFrequency = (SeekBar) findViewById(R.id.seekBarFrequency);
		frequency_text = (TextView) findViewById(R.id.frequency_text);
        random = (ImageButton) findViewById(R.id.random);
        random_text = (TextView) findViewById(R.id.random_text);
        sptButton = (ImageButton) findViewById(R.id.sptButton);
        seekBarTrials = (SeekBar) findViewById(R.id.seekBarTrials);
        trialsBeforeTempoChange = (TextView) findViewById(R.id.trialsBeforeTempoChange);
        rangeBar = (RangeBar) findViewById(R.id.rangeSeekbar);
        spt = (TextView) findViewById(R.id.spt);

        f = getSharedPreferences("Settings", Activity.MODE_PRIVATE);


		latency_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				latency.setText(_charSeq.toString());
				f.edit().putString("latency", _charSeq.toString()).commit();
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});
		username_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				username.setText(_charSeq.toString());
				f.edit().putString("username", _charSeq.toString()).commit();
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});
        bpm_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
            }
            @Override
            public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
                bpm.setText(_charSeq.toString());
                f.edit().putString("bpm", _charSeq.toString()).commit();
            }
            @Override
            public void afterTextChanged(Editable _text) {
            }
        });
		tolerance_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				tolerance.setText(_charSeq.toString());
				f.edit().putString("tolerance", _charSeq.toString()).commit();
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});
        audible_beat_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
            }
            @Override
            public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
                audible_beat.setText(_charSeq.toString());
                f.edit().putString("audible_beat", _charSeq.toString()).commit();
            }
            @Override
            public void afterTextChanged(Editable _text) {
            }
        });
        quiet_beat_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
            }
            @Override
            public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
                quiet_beat.setText(_charSeq.toString());
                f.edit().putString("quiet_beat", _charSeq.toString()).commit();
            }
            @Override
            public void afterTextChanged(Editable _text) {
            }
        });
        repetition_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
            }
            @Override
            public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
                repetition.setText(_charSeq.toString());
                f.edit().putString("repetition", _charSeq.toString()).commit();
            }
            @Override
            public void afterTextChanged(Editable _text) {
            }
        });
		im_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _buttonView, final boolean _isChecked)  { 
				if (_isChecked) {
					f.edit().putString("isChecked", "1").commit();
				}
				else {
					f.edit().putString("isChecked", "0").commit();
				}
			}
		});
		reaction_time_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _buttonView, final boolean _isChecked)  {
				if (_isChecked) {
					f.edit().putString("reaction_switch_is_checked", "1").commit();
				}
				else {
					f.edit().putString("reaction_switch_is_checked", "0").commit();
				}
			}
		});
//		distractor_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton _buttonView, final boolean _isChecked)  {
//				if (_isChecked) {
//					f.edit().putString("distractor_switch_is_checked", "1").commit();
//				}
//				else {
//					f.edit().putString("distractor_switch_is_checked", "0").commit();
//				}
//			}
//		});
		send_start_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				send_start.setText(_charSeq.toString());
				f.edit().putString("send_start", _charSeq.toString()).commit();
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});
		send_end_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
			}
			@Override
			public void onTextChanged(final CharSequence _charSeq, int _start, int _before, int _count) {
				send_end.setText(_charSeq.toString());
				f.edit().putString("send_end", _charSeq.toString()).commit();
			}
			@Override
			public void afterTextChanged(Editable _text) {
			}
		});

		bpm.setText(seekBarBpm.getProgress() + "/" + seekBarBpm.getMax() + " BPM");

		seekBarBpm.setMax( (max - min) / step );

		seekBarBpm.setOnSeekBarChangeListener(
				new SeekBar.OnSeekBarChangeListener()
				{
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
												  boolean fromUser)
					{
						// Ex :
						// And finally when you want to retrieve the value in the range you
						// wanted in the first place -> [3-5]
						//
						// if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
						int value = min + (progress * step);
						bpm.setText(Integer.toString(value) + " BPM");
						f.edit().putString("bpm", Integer.toString(value).toString()).apply();
						spt.setText(String.format("%.0f%% of Preferred Tempo", (Double.parseDouble(f.getString("bpm", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100));
					}
				}
		);

		frequency_text.setText(seekBarFrequency.getProgress() + "/" + seekBarFrequency.getMax());

		seekBarFrequency.setMax( (maxFrequency - minFrequency) / stepFrequency );

		seekBarFrequency.setOnSeekBarChangeListener(
				new SeekBar.OnSeekBarChangeListener()
				{
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
												  boolean fromUser)
					{
						// Ex :
						// And finally when you want to retrieve the value in the range you
						// wanted in the first place -> [3-5]
						//
						// if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
						int value = minFrequency + (progress * stepFrequency);
						frequency_text.setText(("After " + value + " taps combo"));
						f.edit().putString("frequency", String.valueOf(value)).apply();

					}
				}
		);

        trialsBeforeTempoChange.setText(seekBarTrials.getProgress() + "/" + seekBarTrials.getMax());

        seekBarTrials.setMax( (maxTrials - minTrials) / stepTrials );

        seekBarTrials.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser)
                    {
                        // Ex :
                        // And finally when you want to retrieve the value in the range you
                        // wanted in the first place -> [3-5]
                        //
                        // if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
                        int value = minTrials + (progress * stepTrials);
                        if (value == 1 ){
                            trialsBeforeTempoChange.setText((value + " trial before tempo change"));
                        }
                        else {
                            trialsBeforeTempoChange.setText((value + " trials before tempo change"));
                        }
                        f.edit().putString("trials", String.valueOf(value)).apply();
                    }
                }
        );


		tolerance.setText(seekBarTolerance.getProgress() + "/" + seekBarTolerance.getMax() + " ms");

		seekBarTolerance.setMax( (maxTolerance - minTolerance) / stepTolerance );

		seekBarTolerance.setOnSeekBarChangeListener(
				new SeekBar.OnSeekBarChangeListener()
				{
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
												  boolean fromUser)
					{
						// Ex :
						// And finally when you want to retrieve the value in the range you
						// wanted in the first place -> [3-5]
						//
						// if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
						int value = minTolerance + (progress * stepTolerance);
						tolerance.setText(Integer.toString(value) + " ms");
						f.edit().putString("tolerance", Integer.toString(value).toString()).apply();
						Log.d("tolerance", f.getString("tolerance", ""));


					}
				}
		);

		quiet_beat.setText(seekBarBeats.getProgress() + "/" + seekBarBeats.getMax());

		seekBarBeats.setMax( (maxBeats - minBeats) / stepBeats );

		seekBarBeats.setOnSeekBarChangeListener(
				new SeekBar.OnSeekBarChangeListener()
				{
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {}

					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
												  boolean fromUser)
					{
						// Ex :
						// And finally when you want to retrieve the value in the range you
						// wanted in the first place -> [3-5]
						//
						// if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
						int value = minBeats + (progress * stepBeats);
						quiet_beat.setText(Integer.toString(value));
						f.edit().putString("quiet_beat", quiet_beat.getText().toString()).apply();

					}
				}
		);

        random.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (random.getBackgroundTintList() == ColorStateList.valueOf(getResources().getColor(R.color.grey))){
                        random.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.very_fast)));
						bpm.setText((f.getString("randomMin", "") + " - " + (f.getString("randomMax", "") + " BPM")));
						String min = String.format("%.0f", (Double.parseDouble(f.getString("randomMin", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100);
						String max = String.format("%.0f", (Double.parseDouble(f.getString("randomMax", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100);
						spt.setText(String.format("%s%% - %s%% of Preferred Tempo", min, max));
						seekBarBpm.setVisibility(View.INVISIBLE);
                        rangeBar.setVisibility(View.VISIBLE);
                        random_text.setVisibility(View.VISIBLE);
                        trialsBeforeTempoChange.setVisibility(View.VISIBLE);
                        seekBarTrials.setVisibility(View.VISIBLE);

                        f.edit().putString("random", "1").apply();
                    }
                    else {
                        random.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                        bpm.setText(f.getString("bpm", "") + " BPM");
						spt.setText(String.format("%.0f%% of Preferred Tempo", (Double.parseDouble(f.getString("bpm", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100));
						seekBarBpm.setVisibility(View.VISIBLE);
                        rangeBar.setVisibility(View.GONE);
                        random_text.setVisibility(View.INVISIBLE);
                        trialsBeforeTempoChange.setVisibility(View.GONE);
                        seekBarTrials.setVisibility(View.GONE);
                        f.edit().putString("random", "0").apply();
                    }
                }
                return false;
            }
        });

        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                bpm.setText(leftPinValue + " - " + rightPinValue + " BPM");
				f.edit().putString("randomMin", leftPinValue).apply();
				f.edit().putString("randomMax", rightPinValue).apply();
				String min = String.format("%.0f", (Double.parseDouble(f.getString("randomMin", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100);
				String max = String.format("%.0f", (Double.parseDouble(f.getString("randomMax", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100);
				spt.setText(String.format("%s%% - %s%% of Preferred Tempo", min, max));

			}

        });

	}

	private void  initializeLogic() {

		if((f.getString("bpm", "")).equals("")){
			f.edit().putString("bpm", "60").apply();
			seekBarBpm.setProgress(60);
			bpm.setText("60 BPM");
		}
		else {
			bpm.setText(f.getString("bpm", "") + "BPM");
			seekBarBpm.setProgress(Integer.parseInt(f.getString("bpm", "")) - 40);
		}

		if((f.getString("preferredTempo", "")).equals("")){
			f.edit().putString("preferredTempo", "120").apply();
		}
		else {
			preferredTempo = (f.getString("preferredTempo", "") + "BPM");
			spt.setText(String.format("%.0f%% of Preferred Tempo", (Double.parseDouble(f.getString("bpm", "")))/(Double.parseDouble(f.getString("preferredTempo", "")))*100));
		}

		if((f.getString("randomMin", "")).equals("")){
			f.edit().putString("randomMin", "60").apply();
			f.edit().putString("randomMax", "100").apply();
			rangeBar.setRangePinsByValue(60, 100);
			bpm.setText(("60" + " - " + "100" + " BPM"));
		}
		else {
			rangeBar.setRangePinsByValue(Float.parseFloat(f.getString("randomMin", "")), Float.parseFloat(f.getString("randomMax", "")));
			bpm.setText((f.getString("randomMin", "") + " - " + (f.getString("randomMax", "") + " BPM")));
		}

		if((f.getString("randomMax", "")).equals("")){
			f.edit().putString("randomMin", "60").apply();
			f.edit().putString("randomMax", "100").apply();
			rangeBar.setRangePinsByValue(60, 100);
			bpm.setText(("60" + " - " + "100" + " BPM"));
		}
		else {
			rangeBar.setRangePinsByValue(Float.parseFloat(f.getString("randomMin", "")), Float.parseFloat(f.getString("randomMax", "")));
			bpm.setText((f.getString("randomMin", "") + " - " + (f.getString("randomMax", "") + " BPM")));
		}

		if((f.getString("frequency", "")).equals("")){
			f.edit().putString("frequency", "3").apply();
			seekBarFrequency.setProgress(3);
			frequency_text.setText("After 3 taps combo");
		}
		else {
			frequency_text.setText("After " + (f.getString("frequency", "")) + " taps combo");
			seekBarFrequency.setProgress(Integer.parseInt(f.getString("frequency", "")) - 3);
		}

        if((f.getString("trials", "")).equals("")){
            f.edit().putString("trials", "3").apply();
            seekBarTrials.setProgress(3);
            trialsBeforeTempoChange.setText("3 trials before tempo change");
        }
        else {
		    if ((f.getString("trials", "")).equals("1")){
                trialsBeforeTempoChange.setText((f.getString("trials", "")) + " trial before tempo change");
            }
            else {
                trialsBeforeTempoChange.setText((f.getString("trials", "")) + " trials before tempo change");
            }
            seekBarTrials.setProgress(Integer.parseInt(f.getString("trials", "")) - 1);
        }

		if((f.getString("tolerance", "")).equals("")){
			f.edit().putString("tolerance", "35").apply();
			seekBarTolerance.setProgress(0);
			tolerance.setText("35 ms");
		}
		else {
			tolerance.setText(f.getString("tolerance", "") + " ms");
			seekBarTolerance.setProgress((Integer.parseInt(f.getString("tolerance", ""))/15) - 1);
			Log.d("tolerance", f.getString("tolerance", ""));
		}

		if((f.getString("audible_beat", "")).equals("")){
			f.edit().putString("audible_beat", "0").apply();
			audible_beat.setText("0");
		}
		else {
			audible_beat.setText(f.getString("audible_beat", ""));
		}

		if((f.getString("quiet_beat", "")).equals("")){
			f.edit().putString("quiet_beat", "30").apply();
			seekBarBeats.setProgress(0);
			quiet_beat.setText("30");
		}
		else {
			quiet_beat.setText(f.getString("quiet_beat", ""));
			seekBarBeats.setProgress(Integer.parseInt(f.getString("quiet_beat", "")) - 30);
		}

		if((f.getString("repetition", "")).equals("")){
			repetition.setText("1");
			f.edit().putString("repetition", "1").apply();
		}
		else {
			repetition.setText(f.getString("repetition", ""));
		}

		latency.setText(f.getString("latency", ""));
		username.setText(f.getString("username", ""));
		send_start.setText(f.getString("send_start", ""));
		send_end.setText(f.getString("send_end", ""));

		if(f.getString("isChecked", "").equals("")){
			im_switch.setChecked(true);
		}
		else {
			if (Double.parseDouble(f.getString("isChecked", "")) == 1) {
				im_switch.setChecked(true);
			}
			else {
				im_switch.setChecked(false);
			}
		}
		if(f.getString("reaction_switch_is_checked", "").equals("")){
			reaction_time_switch.setChecked(false);
		}
		else {
			if (Double.parseDouble(f.getString("reaction_switch_is_checked", "")) == 1) {
				reaction_time_switch.setChecked(true);
			}
			else {
				reaction_time_switch.setChecked(false);
			}
		}

        if((f.getString("random", "")).equals("")){
            f.edit().putString("random", "0").apply();
            bpm.setText(f.getString("bpm", ""));
            seekBarBpm.setVisibility(View.VISIBLE);
            rangeBar.setVisibility(View.GONE);
            random_text.setVisibility(View.INVISIBLE);
            trialsBeforeTempoChange.setVisibility(View.GONE);
            seekBarTrials.setVisibility(View.GONE);
        }
        else {
            if((f.getString("random", "")).equals("1")){
                random.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.very_fast)));
				bpm.setText((f.getString("randomMin", "") + " - " + (f.getString("randomMax", "") + " BPM")));
				seekBarBpm.setVisibility(View.INVISIBLE);
                rangeBar.setVisibility(View.VISIBLE);
                random_text.setVisibility(View.VISIBLE);
                trialsBeforeTempoChange.setVisibility(View.VISIBLE);
                seekBarTrials.setVisibility(View.VISIBLE);

            }
            else {
                random.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                bpm.setText(f.getString("bpm", "") + " BPM");
                seekBarBpm.setVisibility(View.VISIBLE);
                random_text.setVisibility(View.INVISIBLE);
            }

        }

        _sptButton();


//		if(f.getString("distractor_switch_is_checked", "").equals("")){
//			distractor_switch.setChecked(false);
//		}
//		else {
//			if (Double.parseDouble(f.getString("distractor_switch_is_checked", "")) == 1) {
//				distractor_switch.setChecked(true);
//			}
//			else {
//				distractor_switch.setChecked(false);
//			}
//		}
	}

    private void _sptButton() {
        sptButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    intent.setClass(getApplicationContext(), PreferredTempoActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }


	@Override
	public void onBackPressed() {
				intent.setClass(getApplicationContext(), MainActivity.class);
				intent.putExtra("latency", latency.getText().toString());
				intent.putExtra("username", username.getText().toString());
		        intent.putExtra("bpm",bpm.getText().toString());
				intent.putExtra("tolerance",tolerance.getText().toString());
                intent.putExtra("audible_beat", audible_beat.getText().toString());
                intent.putExtra("quiet_beat", quiet_beat.getText().toString());
                intent.putExtra("repetition", repetition.getText().toString());
				intent.putExtra("send_start", send_start.getText().toString());
				intent.putExtra("send_end", send_end.getText().toString());
				startActivity(intent);
				finish();
	}

}
