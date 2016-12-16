package com.reading.readtimer.countdown;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachit on 12/16/16.
 */

public class CountdownTimer extends CountDownTimer {

    private static final long DEFAULT_COUNTDOWN_INTERVAL = 1000;

    private List<CountDownListener> listeners;

    private static CountdownTimer timer;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    private CountdownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void addListener(CountDownListener countDownListener) {
        if (null == listeners) {
            listeners = new ArrayList<>();
        }
        listeners.add(countDownListener);
    }

    public static CountdownTimer getNewInstance(long duration) {
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
        timer = new CountdownTimer(duration, DEFAULT_COUNTDOWN_INTERVAL);
        return timer;
    }

    public static CountdownTimer getInstance() {
        return timer;
    }


    @Override
    public void onTick(long millisUntilFinished) {
        if (null != listeners) {
            for (CountDownListener listener : listeners) {
                listener.onTick(millisUntilFinished);
            }
        }
    }

    @Override
    public void onFinish() {
        if (null != listeners) {
            for (CountDownListener listener : listeners) {
                listener.onFinish();
            }
        }
        listeners = null;
    }

    public interface CountDownListener {
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}
