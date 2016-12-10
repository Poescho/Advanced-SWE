package com.example.michael.kassenautomat_dhbw.fragments.settings;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.dialogs.TextDialog;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;
import com.example.michael.kassenautomat_dhbw.util.DefaultValuesHandler;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by Michael on 20.05.2016.
 */
public class FragmentSettings extends MyFragment {

    public KassenautomatContext kassenautomatContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_settings, null);

        Button btnOk = (Button)view.findViewById(R.id.dialog_settings_ok);
        Button btnResetUser = (Button)view.findViewById(R.id.dialog_settings_reset_user);

        final EditText etBasePrice = (EditText)view.findViewById(R.id.dialog_settings_base_price_edit_text);
        final EditText etPricePerMinute = (EditText)view.findViewById(R.id.dialog_settings_price_per_five_minutes_edit_text);
        final EditText etBasePriceHour = (EditText)view.findViewById(R.id.dialog_settings_base_price_hour_edit_text);
        final EditText etPricePerHour = (EditText)view.findViewById(R.id.dialog_settings_price_per_hour_edit_text);
        final EditText etBasePriceDay = (EditText)view.findViewById(R.id.dialog_settings_base_price_day_edit_text);
        final EditText etPricePerDay = (EditText)view.findViewById(R.id.dialog_settings_price_per_day_edit_text);



        etBasePrice.setText(DefaultValuesHandler.getBasePrice(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getBasePrice(kassenautomatContext) % 100);
        etPricePerMinute.setText(DefaultValuesHandler.getPricePerMinute(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getPricePerMinute(kassenautomatContext) % 100);
        etBasePriceHour.setText(DefaultValuesHandler.getBasePriceHour(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getBasePriceHour(kassenautomatContext) % 100);
        etPricePerHour.setText(DefaultValuesHandler.getPricePerHour(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getPricePerHour(kassenautomatContext) % 100);
        etBasePriceDay.setText(DefaultValuesHandler.getBasePriceDay(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getBasePriceDay(kassenautomatContext) % 100);
        etPricePerDay.setText(DefaultValuesHandler.getPricePerDay(kassenautomatContext) / 100 + "." + DefaultValuesHandler.getPricePerDay(kassenautomatContext) % 100);


        btnResetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    kassenautomatContext.resetUser();
                    TextDialog dialog = new TextDialog();
                    dialog.setArguments(TextDialog.getBundle("Ihr Benutzer wurde zurückgesetzt!"));
                    dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Reset User");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice;
                int pricePerMinute;
                int basePriceHour;
                int pricePerHour;
                int basePriceDay;
                int pricePerDay;

                try {
                    basePrice = (int)(Double.parseDouble(etBasePrice.getText().toString()) * 100);
                } catch (Exception e) {
                    basePrice = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }
                try {
                    pricePerMinute = (int)(Double.parseDouble(etPricePerMinute.getText().toString()) * 100);
                } catch (Exception e) {
                    pricePerMinute = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }
                try {
                    basePriceHour = (int)(Double.parseDouble(etBasePriceHour.getText().toString()) * 100);
                } catch (Exception e) {
                    basePriceHour = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }
                try {
                    pricePerHour = (int)(Double.parseDouble(etPricePerHour.getText().toString()) * 100);
                } catch (Exception e) {
                    pricePerHour = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }
                try {
                    basePriceDay = (int)(Double.parseDouble(etBasePriceDay.getText().toString()) * 100);
                } catch (Exception e) {
                    basePriceDay = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }
                try {
                    pricePerDay = (int)(Double.parseDouble(etPricePerDay.getText().toString()) * 100);
                } catch (Exception e) {
                    pricePerDay = DefaultValuesHandler.DEFAULT_BASE_PRICE;
                }

                DefaultValuesHandler.setBasePrice(kassenautomatContext, basePrice);
                DefaultValuesHandler.setPricePerMinute(kassenautomatContext, pricePerMinute);
                DefaultValuesHandler.setBasePriceHour(kassenautomatContext, basePriceHour);
                DefaultValuesHandler.setPricePerHour(kassenautomatContext, pricePerHour);
                DefaultValuesHandler.setBasePriceDay(kassenautomatContext, basePriceDay);
                DefaultValuesHandler.setPricePerDay(kassenautomatContext, pricePerDay);

                TextDialog dialog = new TextDialog();
                dialog.setArguments(TextDialog.getBundle("Ihre Einstellungen wurden übernommen!"));
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Show confirmation settings");
                //dismiss();
            }
        });

        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);*/

        return view;


    }


    public void setmCallback(OnButtonClickedCallback mCallback) {
        this.mCallback = mCallback;
    }

  /*  @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mCallback.endPayment();
        mCallback.updateTicketList();
    }*/
}
