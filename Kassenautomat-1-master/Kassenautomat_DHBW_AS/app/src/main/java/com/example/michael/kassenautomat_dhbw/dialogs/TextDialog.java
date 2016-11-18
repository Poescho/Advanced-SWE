package com.example.michael.kassenautomat_dhbw.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by Michael on 30.04.2016.
 */
public class TextDialog extends DialogFragment {
    public static final String DIALOG_BUNDLE_TEXT = "DIALOG_BUNDLE_TEXT";

    OnButtonClickedCallback mCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle dialogBundle = getArguments();

        String text = "";
        if(dialogBundle != null) {
            text = dialogBundle.getString(DIALOG_BUNDLE_TEXT);
        } else {
            text = "Bundle failed to deliver.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(text);


        return builder.create();
    }

    public static Bundle getBundle(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_BUNDLE_TEXT, text);
        return bundle;
    }

    public void setmCallback(OnButtonClickedCallback mCallback) {
        this.mCallback = mCallback;
    }
}