package com.example.poescho.parkokassoquittomat.Parkscheinausgabe;

import android.content.Context;

import com.example.poescho.parkokassoquittomat.Helpers.Subject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Poescho on 07.11.2016.
 */

public class Parkschein extends Subject implements Serializable{

    private Calendar date;
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;

    public Parkschein() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        this.date =  calendar;
        this.minute =  this.date.get(this.date.MINUTE);
        this.hour =  this.date.get(this.date.HOUR_OF_DAY);
        this.day =  this.date.get(this.date.DAY_OF_MONTH);
        this.month =  this.date.get(this.date.MONTH);
        this.year =  this.date.get(this.date.YEAR);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Calendar getDate() {
        return date;
    }




    public void saveParkschein(Context context)
    {
        try {
        FileOutputStream fos = context.openFileOutput("Parkschein", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
         os.writeObject(this);
         os.close();
         fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Parkschein loadParkschein(Context context) {

        Parkschein parkschein = null;

        try {
            FileInputStream fis = context.openFileInput("Parkschein");
            ObjectInputStream is = new ObjectInputStream(fis);
            parkschein = (Parkschein) is.readObject();
            is.close();
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return parkschein;
    }
}
