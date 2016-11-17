package com.example.poescho.parkokassoquittomat.Helpers;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import com.example.poescho.parkokassoquittomat.Main;

import com.example.poescho.parkokassoquittomat.Main;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        int hour = Main.parkschein.getHour();
        int minute = Main.parkschein.getMinute();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        // Do something with the time chosen by the user
        Main.parkschein.setHour(hourOfDay);
        Main.parkschein.setMinute(minute);


    }
}
