package com.example.poescho.parkokassoquittomat.Parkscheinausgabe;

import com.example.poescho.parkokassoquittomat.Helpers.Subject;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Poescho on 07.11.2016.
 */

public class Parkschein extends Subject{

    private Calendar date;
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;


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

        public Parkschein() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
         this.date =  calendar;
            this.minute =  this.date.get(this.date.MINUTE);
            this.hour =  this.date.get(this.date.HOUR_OF_DAY);
            this.day =  this.date.get(this.date.DAY_OF_MONTH);
            this.month =  this.date.get(this.date.MONTH);
            this.year =  this.date.get(this.date.YEAR);
    }
}
