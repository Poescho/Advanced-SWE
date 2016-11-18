package dead_code_for_reference.fragment_draft.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.michael.kassenautomat_dhbw.FragmentChanger;
import com.example.michael.kassenautomat_dhbw.R;

/**
 * Created by Michael on 17.04.2016.
 */
public class FragmentToolbarCustomer extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_toolbar, container, false);

        Button btnToWorker = (Button)view.findViewById(R.id.btnToWorker);
        btnToWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.toolbar_content, new FragmentToolbarWorker());
                ft.commit();

                FragmentChanger fc = new FragmentChanger(getFragmentManager());
                fc.changeToWorker();
            }
        });

        return view;
    }
}
