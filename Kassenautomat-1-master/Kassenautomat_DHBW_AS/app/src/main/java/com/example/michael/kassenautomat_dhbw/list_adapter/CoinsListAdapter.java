package com.example.michael.kassenautomat_dhbw.list_adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.fragments.one.FragmentOneShowAutomata;
import com.example.michael.kassenautomat_dhbw.util.CoinDragShadow;

import java.util.List;

/**
 * Created by administrator on 18.06.16.
 */
public class CoinsListAdapter extends MyAdapter<Integer> {

    static int[] coinPicture = {R.drawable.two_euro, R.drawable.one_euro, R.drawable.fifty_cent, R.drawable.twenty_cent, R.drawable.ten_cent, R.drawable.five_cent};

    public CoinsListAdapter(Context context, List<Integer> money, OnButtonClickedCallback mCallback) {
        super(context, R.layout.list_coins_row, money, mCallback);

        this.context=context;
    }

    public View getView(final int position,View view,ViewGroup parent) {

        final View rowView = ((Activity) context).getLayoutInflater().inflate(R.layout.list_coins_row, null, true);

        final ImageButton iBtnCoins = (ImageButton) rowView.findViewById(R.id.list_coins_row_imgBtn_coin);
        final TextView txtAmount = (TextView) rowView.findViewById(R.id.amount);
        final LinearLayout linLayBackground = (LinearLayout)rowView.findViewById(R.id.list_coins_row_background);

        final String text = "Amount: " + mCallback.getKassenautomatContext().getCurrentUser().getMoney().asList().get(position);
        txtAmount.setText(text);

        View.OnClickListener coinClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPayForTicket(Money.coinValues[position]);
                final String text = "Amount: " + mCallback.getKassenautomatContext().getCurrentUser().getMoney().asList().get(position);
                txtAmount.setText(text);
            }
        };

        iBtnCoins.setBackgroundResource(coinPicture[position]);
        iBtnCoins.setOnClickListener(coinClickedListener);

        linLayBackground.setOnClickListener(coinClickedListener);
        linLayBackground.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData dragData = ClipData.newPlainText(FragmentOneShowAutomata.COIN_DRAG_DESC.getLabel(), Money.coinValues[position] + "");

                CoinDragShadow coinDragShadow = new CoinDragShadow(iBtnCoins, mCallback.getKassenautomatContext().getContext());


                v.startDrag(dragData, coinDragShadow, null, 0);

                return true;

            }
        });



        return rowView;
    }


}
