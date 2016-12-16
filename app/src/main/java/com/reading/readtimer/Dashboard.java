package com.reading.readtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reading.readtimer.countdown.CountdownTimer;
import com.reading.readtimer.countdown.CountdownTimer.CountDownListener;
import com.reading.readtimer.utils.Toasts;

import java.text.Format;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {

    private CountdownTimer countdownTimer;
    private CountDownListener countDownListener;

    private Button startStopButton;
    private EditText durationEditText;

    private Toasts toasts;

    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toasts = new Toasts(this.getApplicationContext());
        initLayout();
        initListeners();
    }

    private void initListeners() {
        countDownListener = new CountDownListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                durationEditText.setText(
                        String.format(Locale.ENGLISH, "%d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                started = false;
                resetDurationEditText();
                startStopButton.setText("Start");
            }
        };
    }

    private void initLayout() {
        startStopButton = (Button) findViewById(R.id.startStopButton);
        durationEditText = (EditText) findViewById(R.id.timertText);
    }

    public void startStopOnClick(View view) {
        if (!started) {
            startStopButton.setText("Stop");
            started = true;
            String text = durationEditText.getText().toString();
            durationEditText.setEnabled(false);
            try {
                double time = Double.parseDouble(text);
                long duration = (long) time * 1000;
                countdownTimer = CountdownTimer.getNewInstance(duration);
                countdownTimer.addListener(countDownListener);
                countdownTimer.start();
            } catch (NumberFormatException nfe) {
                toasts.shorter("Please enter a valid duration");
            }
        } else {
            countdownTimer.cancel();
            startStopButton.setText("Start");
            resetDurationEditText();
            started = false;
        }
    }

    public void resetDurationEditText() {
        durationEditText.setText("0.00");
        durationEditText.setEnabled(true);
    }
}
