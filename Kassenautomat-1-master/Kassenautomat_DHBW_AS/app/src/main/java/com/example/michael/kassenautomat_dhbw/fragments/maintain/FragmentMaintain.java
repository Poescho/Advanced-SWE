package com.example.michael.kassenautomat_dhbw.fragments.maintain;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.dialogs.TextDialog;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by Michael on 20.05.2016.
 */
public class FragmentMaintain extends MyFragment {

    public KassenautomatContext kassenautomatContext;
    private static SeekBar sbTwoEuro;
    private static SeekBar sbOneEuro;
    private static SeekBar sbFiftyCent;
    private static SeekBar sbTwentyCent;
    private static SeekBar sbTenCent;
    private static SeekBar sbFiveCent;
    private static SeekBar sbParkCoins;



    public static FragmentMaintain newInstance(KassenautomatContext kassenautomatContext)
    {
        FragmentMaintain fragmentMaintain = new FragmentMaintain();
        fragmentMaintain.kassenautomatContext = kassenautomatContext;
        return fragmentMaintain;
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView:FragmentMaintain");
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_maintain, null);

        Button btnSetDefault = (Button)view.findViewById(R.id.dialog_maintain_set_default);
        Button btnLoadDefault = (Button)view.findViewById(R.id.dialog_maintain_load_default);
        Button btnOk = (Button)view.findViewById(R.id.dialog_maintain_ok);

        sbTwoEuro = (SeekBar)view.findViewById(R.id.dialog_maintain_two_euro_seek_bar);
        final TextView etTwoEuro = (TextView)view.findViewById(R.id.dialog_maintain_two_euro_edit_text);
        sbOneEuro = (SeekBar)view.findViewById(R.id.dialog_maintain_one_euro_seek_bar);
        final TextView etOneEuro = (TextView)view.findViewById(R.id.dialog_maintain_one_euro_edit_text);
        sbFiftyCent = (SeekBar)view.findViewById(R.id.dialog_maintain_fifty_cent_seek_bar);
        final TextView etFiftyCent = (TextView)view.findViewById(R.id.dialog_maintain_fifty_cent_edit_text);
        sbTwentyCent = (SeekBar)view.findViewById(R.id.dialog_maintain_twenty_cent_seek_bar);
        final TextView etTwentyCent = (TextView)view.findViewById(R.id.dialog_maintain_twenty_cent_edit_text);
        sbTenCent = (SeekBar)view.findViewById(R.id.dialog_maintain_ten_cent_seek_bar);
        final TextView etTenCent = (TextView)view.findViewById(R.id.dialog_maintain_ten_cent_edit_text);
        sbFiveCent = (SeekBar)view.findViewById(R.id.dialog_maintain_five_cent_seek_bar);
        final TextView etFiveCent = (TextView)view.findViewById(R.id.dialog_maintain_five_cent_edit_text);
        sbParkCoins = (SeekBar)view.findViewById(R.id.dialog_maintain_park_coins_seek_bar);
        final TextView etParkCoins = (TextView)view.findViewById(R.id.dialog_maintain_park_coins_edit_text);



        bindSeekBarValueAndEditTextInput(sbTwoEuro, etTwoEuro);
        bindSeekBarValueAndEditTextInput(sbOneEuro, etOneEuro);
        bindSeekBarValueAndEditTextInput(sbFiftyCent, etFiftyCent);
        bindSeekBarValueAndEditTextInput(sbTwentyCent, etTwentyCent);
        bindSeekBarValueAndEditTextInput(sbTenCent, etTenCent);
        bindSeekBarValueAndEditTextInput(sbFiveCent, etFiveCent);
        bindSeekBarValueAndEditTextInput(sbParkCoins, etParkCoins);




       update();


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money tempMoney = new Money(
                        sbTwoEuro.getProgress(),
                        sbOneEuro.getProgress(),
                        sbFiftyCent.getProgress(),
                        sbTwentyCent.getProgress(),
                        sbTenCent.getProgress(),
                        sbFiveCent.getProgress()
                );

                Automata automata = mCallback.getKassenautomatContext().getAutomata();
                automata.setMoney(tempMoney);
                automata.setParkCoins(sbParkCoins.getProgress());
                try {
                    mCallback.getKassenautomatContext().updateAutomata(automata);
                } catch (DbException e) {
                    e.printStackTrace();
                }

                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Ihre Einstellungen wurden übernommen!"));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Show confirmation maintain");
               // dismiss();
            }
        });

        btnSetDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Money tempMoney = new Money(
                        sbTwoEuro.getProgress(),
                        sbOneEuro.getProgress(),
                        sbFiftyCent.getProgress(),
                        sbTwentyCent.getProgress(),
                        sbTenCent.getProgress(),
                        sbFiveCent.getProgress()
                );

                DefaultValuesHandler.setDefaultMoney(mCallback.getKassenautomatContext(), tempMoney);
                DefaultValuesHandler.setDefaultParkCoins(mCallback.getKassenautomatContext(), sbParkCoins.getProgress());

                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Ihre Einstellungen wurden als Standard übernommen!"));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Set default settings");



            }
        });


        btnLoadDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money defaultMoney = DefaultValuesHandler.getDefaultAutomataMoney(mCallback.getKassenautomatContext());

                sbTwoEuro.setProgress(defaultMoney.getTwoEuro());
                sbOneEuro.setProgress(defaultMoney.getOneEuro());
                sbFiftyCent.setProgress(defaultMoney.getFiftyCent());
                sbTwentyCent.setProgress(defaultMoney.getTwentyCent());
                sbTenCent.setProgress(defaultMoney.getTenCent());
                sbFiveCent.setProgress(defaultMoney.getFiveCent());
                sbParkCoins.setProgress(DefaultValuesHandler.getDefaultParkCoins(mCallback.getKassenautomatContext()));

                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Ihre Einstellungen wurden zurückgesetzt!"));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Show default settings");
            }
        });

      /*  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);*/

        return view;
    }

    @Override
    public void onResume() {
        System.out.println("OnResume");
        super.onResume();



    }

    @Override
    public void onStart() {
        System.out.println("OnStart");
        super.onStart();
}

    public static void update()
    {
        Automata automata = mCallback.getKassenautomatContext().getAutomata();
        sbTwoEuro.setProgress(automata.getMoney().getTwoEuro());
        sbOneEuro.setProgress(automata.getMoney().getOneEuro());
        sbFiftyCent.setProgress(automata.getMoney().getFiftyCent());
        sbTwentyCent.setProgress(automata.getMoney().getTwentyCent());
        sbTenCent.setProgress(automata.getMoney().getTenCent());
        sbFiveCent.setProgress(automata.getMoney().getFiveCent());
        sbParkCoins.setProgress((int) automata.getParkCoins());
        automata = null;
    }


    private void bindSeekBarValueAndEditTextInput(final SeekBar sb, final TextView et) {
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                et.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sb.setProgress(20);


        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sb.setProgress(Integer.parseInt(v.getText().toString()));
                return false;
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!et.getText().toString().equals("")) {
                    sb.setProgress(Integer.parseInt(et.getText().toString()));
                } else {
                    sb.setProgress(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!et.getText().toString().equals("")) {
                    sb.setProgress(Integer.parseInt(et.getText().toString()));
                } else {
                    sb.setProgress(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!et.getText().toString().equals("")) {
                    sb.setProgress(Integer.parseInt(et.getText().toString()));
                } else {
                    sb.setProgress(0);
                }
            }
        });
    }
}
