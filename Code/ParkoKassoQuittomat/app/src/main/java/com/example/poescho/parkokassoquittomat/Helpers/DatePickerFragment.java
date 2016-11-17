package com.example.poescho.parkokassoquittomat.Helpers;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.poescho.parkokassoquittomat.Main;
import com.example.poescho.parkokassoquittomat.R;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
         int year = Main.parkschein.getYear();
        int month = Main.parkschein.getMonth();
         int day = Main.parkschein.getDay();

        // Create a new instance of DatePickerDialog and return it
       return new DatePickerDialog(getActivity(),
               R.style.picker,this,year,month,day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Main.parkschein.setDay(day);
        Main.parkschein.setMonth(month);
        Main.parkschein.setYear(year);
        Main.parkschein.notifyObservers(Main.parkschein);
 }
}
