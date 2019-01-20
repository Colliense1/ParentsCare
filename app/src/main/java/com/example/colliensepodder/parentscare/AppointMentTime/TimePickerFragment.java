package com.example.colliensepodder.parentscare.AppointMentTime;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.text.format.DateFormat;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener =
                (TimePickerDialog.OnTimeSetListener)getActivity();
        return new TimePickerDialog(getActivity(),listener,hour,minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
