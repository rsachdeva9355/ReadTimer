package com.reading.readtimer.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rachit on 12/16/16.
 */

public class Toasts {

    private Context context;

    public Toasts(Context context) {
        this.context = context;
    }

    public void shorter(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void longer(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}
