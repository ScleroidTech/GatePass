package com.scleroidtech.gatepass.fragments.dialogs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.scleroidtech.gatepass.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ganesh Kaple on 16-06-2016 at 03:12 PM for CriminalIntent
 */


public class TimePickerDialogFragment extends DialogFragment {
    public static final String EXTRA_TIME = "com.example.ganesh.criminalintent.time";
    private static final String ARG_TIME = "crime_time";
    private TimePicker mTimePicker;

    public static TimePickerDialogFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TIME, date);

        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        fragment.setArguments(bundle);
        return fragment;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final int hour = calendar.get(Calendar.HOUR);
        final int min = calendar.get(Calendar.MINUTE);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        mTimePicker = v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(min);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(min);
        }


        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_title).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    int hour = mTimePicker.getHour();
                    int min = mTimePicker.getMinute();
                } else {

                    int hour = mTimePicker.getCurrentHour();
                    int min = mTimePicker.getCurrentMinute();
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR, hour);
                cal.set(Calendar.MINUTE, min);
                Date date = cal.getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        }).create();
    }

    private void sendResult(int ResultCode, Date date) {
        if (getTargetFragment() == null) return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), ResultCode, intent);
    }


}
