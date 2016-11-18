package com.example.michael.kassenautomat_dhbw.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.View;

import com.example.michael.kassenautomat_dhbw.R;

/**
 * Created by Michael on 27.06.2016.
 */
public class CoinDragShadow extends View.DragShadowBuilder {

    private static Drawable shadow;

    public CoinDragShadow(View v, Context context) {
        super(v);

        // Use deprecated method, because new one is not present in API level 17
        shadow = context.getResources().getDrawable(R.drawable.two_euro);
    }



    // Defines a callback that sends the drag shadow dimensions and touch point back to the
    // system.
    @Override
    public void onProvideShadowMetrics (Point size, Point touch) {
        int width, height;

        width = getView().getWidth();
        height = getView().getHeight();

        shadow.setBounds(0, 0, width, height);
        size.set(width, height);
        touch.set(width, height);
    }

    // Defines a callback that draws the drag shadow in a Canvas that the system constructs
    // from the dimensions passed in onProvideShadowMetrics().
    @Override
    public void onDrawShadow(Canvas canvas) {

        // Draws the ColorDrawable in the Canvas passed in from the system.
        shadow.draw(canvas);
    }
}

