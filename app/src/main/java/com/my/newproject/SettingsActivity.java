package com.my.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

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
	private TextView username_text;
	private TextView username;
	private EditText username_edit;
	private TextView latency_text;
	private TextView latency;
	private EditText latency_edit;
	private TextView bpm_text;
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

	private String text = "";


	private Intent intent = new Intent();
	private SharedPreferences f;


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
		send_start_text = (TextView) findViewById(R.id.send_start_text);
		send_start = (TextView) findViewById(R.id.send_start);
		send_start_edit = (EditText) findViewById(R.id.send_start_edit);
		send_end_text = (TextView) findViewById(R.id.send_end_text);
		send_end = (TextView) findViewById(R.id.send_end);
		send_end_edit = (EditText) findViewById(R.id.send_end_edit);


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

	}

	private void  initializeLogic() {
		latency.setText(f.getString("latency", ""));
		username.setText(f.getString("username", ""));
		bpm.setText(f.getString("bpm", ""));
        audible_beat.setText(f.getString("audible_beat", ""));
        quiet_beat.setText(f.getString("quiet_beat", ""));
        repetition.setText(f.getString("repetition", ""));
		send_start.setText(f.getString("send_start", ""));
		send_end.setText(f.getString("send_end", ""));
		if (!f.getString("isChecked", "").equals("")) {
			if (Double.parseDouble(f.getString("isChecked", "")) == 1) {
				im_switch.setChecked(true);
			}
			else {
				im_switch.setChecked(false);
			}
		}
	}

	@Override
	public void onBackPressed() {
				intent.setClass(getApplicationContext(), MainActivity.class);
				intent.putExtra("latency", latency.getText().toString());
				intent.putExtra("username", username.getText().toString());
		        intent.putExtra("bpm",bpm.getText().toString());
                intent.putExtra("audible_beat", audible_beat.getText().toString());
                intent.putExtra("quiet_beat", quiet_beat.getText().toString());
                intent.putExtra("repetition", repetition.getText().toString());
				intent.putExtra("send_start", send_start.getText().toString());
				intent.putExtra("send_end", send_end.getText().toString());
				startActivity(intent);
				finish();
	}

}
