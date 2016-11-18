package com.example.michael.kassenautomat_dhbw.dialogs;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

/**
 * Created by Michael on 19.04.2016.
 */
public abstract class MyFragment extends Fragment{

    public OnButtonClickedCallback mCallback;

    public void setmCallback(OnButtonClickedCallback mCallback) {
        this.mCallback = mCallback;
    }

    //An instance of mCallback is created to enable communication between fragments
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                mCallback = (OnButtonClickedCallback) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnButtonClickedCallback");
            }
        }
    }


}
