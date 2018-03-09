package com.scleroidtech.gatepass;

import android.support.design.widget.Snackbar;
import android.view.View;

public class snackBarUtils {
    public snackBarUtils() {
    }

    void showSnackbar(int msg) {
        View parentLayout = null.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT).show();
    }
}