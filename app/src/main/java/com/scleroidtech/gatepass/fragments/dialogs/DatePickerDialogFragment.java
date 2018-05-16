package com.scleroidtech.gatepass.fragments.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.scleroidtech.gatepass.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//import android.support.v7.app.AlertDialog;

/**
 * Copyright (C)
 *
 * @author Ganesh Kaple
 * @since 09-01-2016
 */
public class DatePickerDialogFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.example.ganesh.criminalintent.date";
    private static final String ARG_DATE = "crime_date";
    private DatePicker mDatePicker;


    public static DatePickerDialogFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DATE, date);

        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        fragment.setArguments(bundle);
        return fragment;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_title).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        }).create();
    }

    private void sendResult(int ResultCode, Date date) {
        if (getTargetFragment() == null) return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), ResultCode, intent);
    }
}
