package com.example.michael.kassenautomat_dhbw.datatypes;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.michael.kassenautomat_dhbw.database.tables.TableMoneybank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 14.04.2016.
 *
 * Plain old data structure
 * Contains every coin used in the Kassenautomat.
 * Will store the number of coins, not the value. The value is represented by the variable name.
 */
public class Money {

    private int twoEuro;
    private int oneEuro;
    private int fiftyCent;
    private int twentyCent;
    private int tenCent;
    private int fiveCent;


    public static final int[] coinValues = {200, 100, 50, 20, 10, 5};


    public Money(int twoEuro, int oneEuro, int fiftyCent, int twentyCent, int tenCent, int fiveCent) {
        this.twoEuro = twoEuro;
        this.oneEuro = oneEuro;
        this.fiftyCent = fiftyCent;
        this.twentyCent = twentyCent;
        this.tenCent = tenCent;
        this.fiveCent = fiveCent;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(TableMoneybank.DB_COLUMN_TWO_EURO, twoEuro);
        cv.put(TableMoneybank.DB_COLUMN_ONE_EURO, oneEuro);
        cv.put(TableMoneybank.DB_COLUMN_FIFTY_CENT, fiftyCent);
        cv.put(TableMoneybank.DB_COLUMN_TWENTY_CENT, twentyCent);
        cv.put(TableMoneybank.DB_COLUMN_TEN_CENT, tenCent);
        cv.put(TableMoneybank.DB_COLUMN_FIVE_CENT, fiveCent);
        return cv;
    }

    public static ContentValues getContentValues(Money m) {
        ContentValues cv = new ContentValues();
        cv.put(TableMoneybank.DB_COLUMN_TWO_EURO, m.getTwoEuro());
        cv.put(TableMoneybank.DB_COLUMN_ONE_EURO, m.getOneEuro());
        cv.put(TableMoneybank.DB_COLUMN_FIFTY_CENT, m.getFiftyCent());
        cv.put(TableMoneybank.DB_COLUMN_TWENTY_CENT, m.getTwentyCent());
        cv.put(TableMoneybank.DB_COLUMN_TEN_CENT, m.getTenCent());
        cv.put(TableMoneybank.DB_COLUMN_FIVE_CENT, m.getFiveCent());
        return cv;
    }

    public List<Integer> asList() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(twoEuro);
        integerList.add(oneEuro);
        integerList.add(fiftyCent);
        integerList.add(twentyCent);
        integerList.add(tenCent);
        integerList.add(fiveCent);
        return integerList;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Money) {
            Money m = (Money)o;
            if(m.getTwoEuro() == this.twoEuro
                    && m.getOneEuro() == this.oneEuro
                    && m.getFiftyCent() == this.fiftyCent
                    && m.getTwentyCent() == this.twentyCent
                    && m.getTenCent() == this.tenCent
                    && m.getFiveCent() == this.fiveCent) {
                return true;
            }
        }
        return false;
    }

    public int sum() {
        return twoEuro + oneEuro + fiftyCent + twentyCent + tenCent + fiveCent;
    }

    public void add(int centsToAdd) {
        switch (centsToAdd) {
            case 200:
                this.increaseTwoEuro();
                break;

            case 100:
                this.increaseOneEuro();
                break;

            case 50:
                this.increaseFiftyCent();
                break;

            case 20:
                this.increaseTwentyCent();
                break;

            case 10:
                this.increaseTenCent();
                break;

            case 5:
                this.increaseFiveCent();
                break;
        }
    }

    public void remove(int centToAdd) {
        switch (centToAdd) {
            case 200:
                this.decreaseTwoEuro();
                break;

            case 100:
                this.decreaseOneEuro();
                break;

            case 50:
                this.decreaseFiftyCent();
                break;

            case 20:
                this.decreaseTwentyCent();
                break;

            case 10:
                this.decreaseTenCent();
                break;
            case 5:
                this.decreaseFiveCent();
                break;
        }
    }


    public int getAmountOfCoinValue(int cents) {
        switch (cents) {
            case 200:
                return this.twoEuro;

            case 100:
                return this.oneEuro;

            case 50:
                return this.fiftyCent;

            case 20:
                return this.twentyCent;

            case 10:
                return this.tenCent;

            case 5:
                return this.fiveCent;
        }
        return 0;
    }

    public void print() {
        System.out.println("200: " + this.twoEuro);
        System.out.println("100: " + this.oneEuro);
        System.out.println(" 50: " + this.fiftyCent);
        System.out.println(" 20: " + this.twentyCent);
        System.out.println(" 10: " + this.tenCent);
        System.out.println("  5: " + this.fiveCent);
    }


    public int getTwoEuro() {
        return twoEuro;
    }

    public void setTwoEuro(int twoEuro) {
        this.twoEuro = twoEuro;
    }

    public int increaseTwoEuro() {
        return twoEuro++;
    }

    public void decreaseTwoEuro() {
        this.twoEuro--;
    }

    public int getOneEuro() {
        return oneEuro;
    }

    public void setOneEuro(int oneEuro) {
        this.oneEuro = oneEuro;
    }

    public int increaseOneEuro() {
        return oneEuro++;
    }

    public void decreaseOneEuro() {
        this.oneEuro--;
    }

    public int getFiftyCent() {
        return fiftyCent;
    }

    public void setFiftyCent(int fiftyCent) {
        this.fiftyCent = fiftyCent;
    }

    public int increaseFiftyCent() {
        return fiftyCent++;
    }

    public void decreaseFiftyCent() {
        this.fiftyCent--;
    }

    public int getTwentyCent() {
        return twentyCent;
    }

    public void setTwentyCent(int twentyCent) {
        this.twentyCent = twentyCent;
    }

    public int increaseTwentyCent() {
        return twentyCent++;
    }

    public void decreaseTwentyCent() {
        this.twentyCent--;
    }

    public int getTenCent() {
        return tenCent;
    }

    public void setTenCent(int tenCent) {
        this.tenCent = tenCent;
    }

    public int increaseTenCent() {
        return tenCent++;
    }

    public void decreaseTenCent() {
        this.tenCent--;
    }

    public int getFiveCent() {
        return fiveCent;
    }

    public void setFiveCent(int fiveCent) {
        this.fiveCent = fiveCent;
    }

    public int increaseFiveCent() {
        return fiveCent++;
    }

    public void decreaseFiveCent() {
        this.fiveCent--;
    }


}
