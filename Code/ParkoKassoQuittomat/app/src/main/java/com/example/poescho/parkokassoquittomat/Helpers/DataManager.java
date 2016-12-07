package com.example.poescho.parkokassoquittomat.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luca on 06.12.2016.
 */

public abstract class DataManager {
    Date now = new Date();
    public String currentDateToString = currentDate();
    public int[] money = new int[12];

    public String currentDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss, E, dd.MM.yyyy");
        String date = dateFormatter.format(this.now);
        return date;
    }

    public int[] getMoneyData() {
        return this.money;
    }

    public void saveDate() {

    }
}
