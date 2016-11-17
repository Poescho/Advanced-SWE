package com.example.poescho.parkokassoquittomat.Helpers;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import com.example.poescho.parkokassoquittomat.Main;

import com.example.poescho.parkokassoquittomat.Main;
import com.example.poescho.parkokassoquittomat.R;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int hour = Main.parkschein.getHour();
        int minute = Main.parkschein.getMinute();

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),  R.style.picker, this, hour, minute,
                true);

        // Create a new instance of TimePickerDialog and return it
        return timePickerDialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        // Do something with the time chosen by the user
        Main.parkschein.setHour(hourOfDay);
        Main.parkschein.setMinute(minute);
        Main.parkschein.notifyObservers(Main.parkschein);


    }
}
