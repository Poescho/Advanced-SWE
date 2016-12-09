package com.example.michael.kassenautomat_dhbw.fragments.maintain;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class MaintainFragment extends MyFragment {

    public KassenautomatContext kassenautomatContext;
    public static MaintainFragment newInstance(KassenautomatContext kassenautomatContext)
    {
        MaintainFragment maintainFragment = new MaintainFragment();
        maintainFragment.kassenautomatContext = kassenautomatContext;
        return maintainFragment;
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_maintain, null);

        Button btnCancel = (Button)view.findViewById(R.id.dialog_maintain_cancel);
        Button btnSetDefault = (Button)view.findViewById(R.id.dialog_maintain_set_default);
        Button btnLoadDefault = (Button)view.findViewById(R.id.dialog_maintain_load_default);
        Button btnOk = (Button)view.findViewById(R.id.dialog_maintain_ok);

        final SeekBar sbTwoEuro = (SeekBar)view.findViewById(R.id.dialog_maintain_two_euro_seek_bar);
        final EditText etTwoEuro = (EditText)view.findViewById(R.id.dialog_maintain_two_euro_edit_text);
        final SeekBar sbOneEuro = (SeekBar)view.findViewById(R.id.dialog_maintain_one_euro_seek_bar);
        final EditText etOneEuro = (EditText)view.findViewById(R.id.dialog_maintain_one_euro_edit_text);
        final SeekBar sbFiftyCent = (SeekBar)view.findViewById(R.id.dialog_maintain_fifty_cent_seek_bar);
        final EditText etFiftyCent = (EditText)view.findViewById(R.id.dialog_maintain_fifty_cent_edit_text);
        final SeekBar sbTwentyCent = (SeekBar)view.findViewById(R.id.dialog_maintain_twenty_cent_seek_bar);
        final EditText etTwentyCent = (EditText)view.findViewById(R.id.dialog_maintain_twenty_cent_edit_text);
        final SeekBar sbTenCent = (SeekBar)view.findViewById(R.id.dialog_maintain_ten_cent_seek_bar);
        final EditText etTenCent = (EditText)view.findViewById(R.id.dialog_maintain_ten_cent_edit_text);
        final SeekBar sbFiveCent = (SeekBar)view.findViewById(R.id.dialog_maintain_five_cent_seek_bar);
        final EditText etFiveCent = (EditText)view.findViewById(R.id.dialog_maintain_five_cent_edit_text);
        final SeekBar sbParkCoins = (SeekBar)view.findViewById(R.id.dialog_maintain_park_coins_seek_bar);
        final EditText etParkCoins = (EditText)view.findViewById(R.id.dialog_maintain_park_coins_edit_text);



        bindSeekBarValueAndEditTextInput(sbTwoEuro, etTwoEuro);
        bindSeekBarValueAndEditTextInput(sbOneEuro, etOneEuro);
        bindSeekBarValueAndEditTextInput(sbFiftyCent, etFiftyCent);
        bindSeekBarValueAndEditTextInput(sbTwentyCent, etTwentyCent);
        bindSeekBarValueAndEditTextInput(sbTenCent, etTenCent);
        bindSeekBarValueAndEditTextInput(sbFiveCent, etFiveCent);
        bindSeekBarValueAndEditTextInput(sbParkCoins, etParkCoins);




        Automata automata = mCallback.getKassenautomatContext().getAutomata();
        sbTwoEuro.setProgress(automata.getMoney().getTwoEuro());
        sbOneEuro.setProgress(automata.getMoney().getOneEuro());
        sbFiftyCent.setProgress(automata.getMoney().getFiftyCent());
        sbTwentyCent.setProgress(automata.getMoney().getTwentyCent());
        sbTenCent.setProgress(automata.getMoney().getTenCent());
        sbFiveCent.setProgress(automata.getMoney().getFiveCent());
        sbParkCoins.setProgress((int) automata.getParkCoins());
        automata = null;


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
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  dismiss();
            }
        });

      /*  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);*/

        return view;
    }


    private void bindSeekBarValueAndEditTextInput(final SeekBar sb, final EditText et) {
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
