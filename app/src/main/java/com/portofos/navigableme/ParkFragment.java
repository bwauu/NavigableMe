package com.portofos.navigableme;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;


public class ParkFragment extends Fragment {

    // instance variable

    private TextView textView;
    private EditText editText;
    private Button applyTextButton, saveButton;
    private Switch switch1;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TEXT = "text";
    private static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    // time instance variables

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    // TODO: Implement Spinner object to register parking spots as string array about 10 strings A1 - A10.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_park, container, false);

        textView = view.findViewById(R.id.textview);
        editText = view.findViewById(R.id.editTextTextEmailAddress);
        applyTextButton = view.findViewById(R.id.apply_text_button);
        saveButton = view.findViewById(R.id.save_button);
        switch1 = view.findViewById(R.id.switch1);

        // Park Time Vars
        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        mButtonStartPause = view.findViewById(R.id.button_start_pause);
        mButtonReset = view.findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
        // We want to set our context from 00:00 to our actual time that is left.
        updateCountDownText();

        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
                saveData();
            }
        });

        loadData();
        updateViews();

        return view;
    }

    private void startTimer() {
        // Initialize MediaPlayer Object.
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.clap_reverb_wav);
        // Whenever startTimer is invoked, we will create a CountDownTimer object
        // and then immediately start the CountDownTimer by start()
        // first arg = length in milliseconds. second arg = how many milliseconds shall pass when
        // our onTick method should be called. In our case every 1000 millisecond =
        // 1 millisecond = 0.001 seconds. Therefore the onTick Method will be invoked every 1 Second.
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // the parameter is amount of millis until this new CountDownTimer is finished.
                // we save this in our member variable so we if it get cancelled we can continue
                // on where we have stopped.
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                // play sound each tic
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.start();

            }

            @Override
            public void onFinish() {
            }
        }.start();
        // After we have start our timer we need a reference to refer if we have started or not.

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void saveData() {
        // MODE_PRIVATE = No other app can change our SharedPreferences.
        // For Activities: SharedPreferences sharedPreferences = getSharedPreferences("String", MODE_PRIVATE);
        // For Fragments code down below:
        SharedPreferences sharedPreferencesInterface = ParkFragment
                .this
                .getActivity()
                .getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferencesInterface.edit();

        sharedPreferencesEditor.putString(TEXT, textView.getText().toString());
        sharedPreferencesEditor.putBoolean(SWITCH1, switch1.isChecked());

        // apply save
        sharedPreferencesEditor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferencesInterface = ParkFragment
                .this
                .getActivity()
                .getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // Empty string by default
        text = sharedPreferencesInterface.getString(TEXT,"");
        // Off by default (false)
        switchOnOff = sharedPreferencesInterface.getBoolean(SWITCH1, true);
    }

    private void updateViews() {
        textView.setText(text);
        switch1.setChecked(switchOnOff);
    }


}