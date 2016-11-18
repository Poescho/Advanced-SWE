package dead_code_for_reference.fragment_draft.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.kassenautomat_dhbw.R;

/**
 * Created by Michael on 17.04.2016.
 */
public class FragmentContentMainCustomer extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main_customer, container, false);

        return view;
    }
}