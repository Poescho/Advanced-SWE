package com.example.michael.kassenautomat_dhbw.fragments.three;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.dialogs.TextDialog;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;

/**
 * Created by administrator on 21.04.16.
 */
public class FragmentThreeUserInformation extends MyFragment {
    Button showTickets;
    Button showMoney;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        System.out.println("onCreateView");
        view = inflater.inflate(R.layout.fragment_three, container, false);
        showTickets = (Button) view.findViewById(R.id.fragment_three_show_park_coins);
        showMoney = (Button) view.findViewById(R.id.fragment_three_show_money);

        showTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Sie haben " + mCallback.getKassenautomatContext().getCurrentUser().getParkCoins() + " Parkmünzen."));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Show parkconis tag");
            }
        });

        showMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Sie haben: \n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getTwoEuro() + " x 2€\n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getOneEuro() + " x 1€\n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getFiftyCent() + " x 50ct\n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getTwentyCent() + " x 20ct\n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getTenCent() + " x 10ct\n" +
                        mCallback.getKassenautomatContext().getCurrentUser().getMoney().getFiveCent() + " x 5ct"
                ));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Show money");
            }
        });

        /*
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(showTickets, "alpha", 0.f, 0.125f, 0.25f, 0.375f, 0.5f, 0.625f, 0.75f, 0.875f, 0.5f, 0f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
        */


        return view;
    }

}
