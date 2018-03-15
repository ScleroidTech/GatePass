package com.scleroidtech.gatepass.utils;

import android.content.Context;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

public class ToastUtils {
    @Inject
    public ToastUtils() {
    }

    /**
     * Prints error toast
     *
     * @param context context of the current view
     * @param msg     the error message to be printed
     */
    public void errorToast(Context context, String msg) {
        Toasty.error(context, msg).show();
    }
}