package com.example.michael.kassenautomat_dhbw.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.michael.kassenautomat_dhbw.datatypes.Money;

/**
 * Created by Michael on 21.05.2016.
 */
public class DefaultValuesHandler {


    public static final String SHARED_PREFS_DEFAULTS          = "SHARED_PREFS_DEFAULTS";


    public static final String SHARED_PREFS_DEFAULT_TWO_EURO = "SHARED_PREFS_DEFAULT_TWO_EURO";
    public static final String SHARED_PREFS_DEFAULT_ONE_EURO = "SHARED_PREFS_DEFAULT_ONE_EURO";
    public static final String SHARED_PREFS_DEFAULT_FIFTY_CENT = "SHARED_PREFS_DEFAULT_FIFTY_CENT";
    public static final String SHARED_PREFS_DEFAULT_TWENTY_CENT = "SHARED_PREFS_DEFAULT_TWENTY_CENT";
    public static final String SHARED_PREFS_DEFAULT_TEN_CENT = "SHARED_PREFS_DEFAULT_TEN_CENT";
    public static final String SHARED_PREFS_DEFAULT_FIVE_CENT = "SHARED_PREFS_DEFAULT_FIVE_CENT";
    public static final String SHARED_PREFS_DEFAULT_PARK_COINS = "SHARED_PREFS_DEFAULT_PARK_COINS";
    public static final int DEFAULT_TWO_EURO = 100;
    public static final int DEFAULT_ONE_EURO = 100;
    public static final int DEFAULT_FIFTY_CENT = 100;
    public static final int DEFAULT_TWENTY_CENT = 100;
    public static final int DEFAULT_TEN_CENT = 100;
    public static final int DEFAULT_FIVE_CENT = 100;
    public static final int DEFAULT_PARK_COINS = 100;


    public static final int DEFAULT_TWO_EURO_USER = 100;
    public static final int DEFAULT_ONE_EURO_USER = 100;
    public static final int DEFAULT_FIFTY_CENT_USER = 100;
    public static final int DEFAULT_TWENTY_CENT_USER = 100;
    public static final int DEFAULT_TEN_CENT_USER = 100;
    public static final int DEFAULT_FIVE_CENT_USER = 100;
    public static final int DEFAULT_PARK_COINS_USER = 0;


    public static final String SHARED_PREFS_DEFAULT_BASE_PRICE          = "SHARED_PREFS_DEFAULT_BASE_PRICE";
    public static final String SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE    = "SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE";
    public static final String SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR     = "SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR";
    public static final String SHARED_PREFS_DEFAULT_PRICE_PER_HOUR      = "SHARED_PREFS_DEFAULT_PRICE_PER_HOUR";
    public static final String SHARED_PREFS_DEFAULT_BASE_PRICE_DAY      = "SHARED_PREFS_DEFAULT_BASE_PRICE_DAY";
    public static final String SHARED_PREFS_DEFAULT_PRICE_PER_DAY       = "SHARED_PREFS_DEFAULT_PRICE_PER_DAY";
    public static final int DEFAULT_BASE_PRICE = 0;
    public static final int DEFAULT_PRICE_PER_MINUTE = 10;
    public static final int DEFAULT_BASE_PRICE_HOUR = 150;
    public static final int DEFAULT_PRICE_PER_HOUR = 100;
    public static final int DEFAULT_BASE_PRICE_DAY = 3000;
    public static final int DEFAULT_PRICE_PER_DAY = 5000;



    @SuppressLint("CommitPrefEdits")
    public static void initializeDefaults(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //COINS
        if (!preferences.contains(SHARED_PREFS_DEFAULT_TWO_EURO)) {
            editor.putInt(SHARED_PREFS_DEFAULT_TWO_EURO, DEFAULT_TWO_EURO);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_ONE_EURO)) {
            editor.putInt(SHARED_PREFS_DEFAULT_ONE_EURO, DEFAULT_ONE_EURO);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_FIFTY_CENT)) {
            editor.putInt(SHARED_PREFS_DEFAULT_FIFTY_CENT, DEFAULT_FIFTY_CENT);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_TWENTY_CENT)) {
            editor.putInt(SHARED_PREFS_DEFAULT_TWENTY_CENT, DEFAULT_TWENTY_CENT);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_TEN_CENT)) {
            editor.putInt(SHARED_PREFS_DEFAULT_TEN_CENT, DEFAULT_TEN_CENT);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_FIVE_CENT)) {
            editor.putInt(SHARED_PREFS_DEFAULT_FIVE_CENT, DEFAULT_FIVE_CENT);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_PARK_COINS)) {
            editor.putInt(SHARED_PREFS_DEFAULT_PARK_COINS, DEFAULT_PARK_COINS);
        }



        //PRICE (saved in cents)
        if (!preferences.contains(SHARED_PREFS_DEFAULT_BASE_PRICE)) {
            editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE, DEFAULT_BASE_PRICE);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE)) {
            editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE, DEFAULT_PRICE_PER_MINUTE);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR)) {
            editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR, DEFAULT_BASE_PRICE_HOUR);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_PRICE_PER_HOUR)) {
            editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_HOUR, DEFAULT_PRICE_PER_HOUR);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_BASE_PRICE_DAY)) {
            editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE_DAY, DEFAULT_BASE_PRICE_DAY);
        }
        if (!preferences.contains(SHARED_PREFS_DEFAULT_PRICE_PER_DAY)) {
            editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_DAY, DEFAULT_PRICE_PER_DAY);
        }

        //Use commit not apply. The delay is acceptable
        editor.commit();
    }

    public static Money getDefaultAutomataMoney(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);

        return new Money(
                preferences.getInt(SHARED_PREFS_DEFAULT_TWO_EURO, DEFAULT_TWO_EURO),
                preferences.getInt(SHARED_PREFS_DEFAULT_ONE_EURO, DEFAULT_ONE_EURO),
                preferences.getInt(SHARED_PREFS_DEFAULT_FIFTY_CENT, DEFAULT_FIFTY_CENT),
                preferences.getInt(SHARED_PREFS_DEFAULT_TWENTY_CENT, DEFAULT_TWENTY_CENT),
                preferences.getInt(SHARED_PREFS_DEFAULT_TEN_CENT, DEFAULT_TEN_CENT),
                preferences.getInt(SHARED_PREFS_DEFAULT_FIVE_CENT, DEFAULT_FIVE_CENT)
        );
    }

    public static Money getDefaultUserMoney() {
        return new Money(
                DEFAULT_TWO_EURO_USER,
                DEFAULT_ONE_EURO_USER,
                DEFAULT_FIFTY_CENT_USER,
                DEFAULT_TWENTY_CENT_USER,
                DEFAULT_TEN_CENT_USER,
                DEFAULT_FIVE_CENT_USER
        );
    }

    public static int getDefaultParkCoins(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);

        return preferences.getInt(SHARED_PREFS_DEFAULT_PARK_COINS, DEFAULT_PARK_COINS);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setDefaultMoney(KassenautomatContext kassenautomatContext, Money money) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_TWO_EURO, money.getTwoEuro());
        editor.putInt(SHARED_PREFS_DEFAULT_ONE_EURO, money.getOneEuro());
        editor.putInt(SHARED_PREFS_DEFAULT_FIFTY_CENT, money.getFiftyCent());
        editor.putInt(SHARED_PREFS_DEFAULT_TWENTY_CENT, money.getTwentyCent());
        editor.putInt(SHARED_PREFS_DEFAULT_TEN_CENT, money.getTenCent());
        editor.putInt(SHARED_PREFS_DEFAULT_FIVE_CENT, money.getFiveCent());

        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public static void setDefaultParkCoins(KassenautomatContext kassenautomatContext, int parkCoins) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_PARK_COINS, parkCoins);

        editor.commit();
    }


    public static int getBasePrice(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_BASE_PRICE, DEFAULT_BASE_PRICE);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setBasePrice(KassenautomatContext kassenautomatContext, int basePrice) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE, basePrice);

        editor.commit();
    }

    public static int getPricePerMinute(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE, DEFAULT_PRICE_PER_MINUTE);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setPricePerMinute(KassenautomatContext kassenautomatContext, int pricePerMinute) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_MINUTE, pricePerMinute);

        editor.commit();
    }

    public static int getBasePriceHour(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR, DEFAULT_BASE_PRICE_HOUR);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setBasePriceHour(KassenautomatContext kassenautomatContext, int basePriceHour) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE_HOUR, basePriceHour);

        editor.commit();
    }

    public static int getPricePerHour(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_PRICE_PER_HOUR, DEFAULT_PRICE_PER_HOUR);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setPricePerHour(KassenautomatContext kassenautomatContext, int pricePerHour) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_HOUR, pricePerHour);

        editor.commit();
    }

    public static int getBasePriceDay(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_BASE_PRICE_DAY, DEFAULT_BASE_PRICE_DAY);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setBasePriceDay(KassenautomatContext kassenautomatContext, int basePriceDay) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_BASE_PRICE_DAY, basePriceDay);

        editor.commit();
    }

    public static int getPricePerDay(KassenautomatContext kassenautomatContext) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getInt(SHARED_PREFS_DEFAULT_PRICE_PER_DAY, DEFAULT_PRICE_PER_DAY);
    }

    @SuppressLint("CommitPrefEdits")
    public static void setPricePerDay(KassenautomatContext kassenautomatContext, int pricePerDay) {
        final SharedPreferences preferences = kassenautomatContext.getContext().getSharedPreferences(SHARED_PREFS_DEFAULTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(SHARED_PREFS_DEFAULT_PRICE_PER_DAY, pricePerDay);

        editor.commit();
    }
}
