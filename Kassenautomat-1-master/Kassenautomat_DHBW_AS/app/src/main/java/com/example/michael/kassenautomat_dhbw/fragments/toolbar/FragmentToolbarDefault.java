package com.example.michael.kassenautomat_dhbw.fragments.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.fragments.maintain.MaintainFragment;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.fragments.settings.SettingsDialog;

/**
 * Created by administrator on 03.05.16.
 */
/*public class FragmentToolbarDefault extends MyFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar_default, container, false);

        Button btnMaintain = (Button)view.findViewById(R.id.fragment_toolbar_default_maintain);
        Button btnSettings = (Button) view.findViewById(R.id.fragment_toolbar_default_settings);

        btnMaintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaintainFragment dialog = new MaintainFragment();
                dialog.setmCallback(mCallback);
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Maintain dialog");
            }
        });


        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsDialog dialog = new SettingsDialog();
                dialog.setmCallback(mCallback);
                dialog.show(mCallback.getKassenautomatContext().getFragmentManager(), "Settings dialog");
            }
        });


        return view;
    }

}*/
