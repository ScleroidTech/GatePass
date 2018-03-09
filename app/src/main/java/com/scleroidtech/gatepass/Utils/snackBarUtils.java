package com.scleroidtech.gatepass.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class snackBarUtils {
    public snackBarUtils() {
    }

    public void showSnackbar(View view, int msg) {

        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}