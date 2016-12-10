package com.example.michael.kassenautomat_dhbw.fragments.two;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.michael.kassenautomat_dhbw.R;
import com.example.michael.kassenautomat_dhbw.dialogs.MyFragment;
import com.example.michael.kassenautomat_dhbw.list_adapter.CoinsListAdapter;



/**
 * Created by administrator on 18.06.16.
 */
public class FragmentCoinList extends MyFragment {

    View view;
    ListView list;
    CoinsListAdapter coinsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_two_coins_list, container, false);
        coinsListAdapter = new CoinsListAdapter(mCallback.getKassenautomatContext().getContext(), mCallback.getKassenautomatContext().getCurrentUser().getMoney().asList(), mCallback);
        list = (ListView) view.findViewById(R.id.fragment_two_coins_list_list);
        list.setAdapter(coinsListAdapter);
        return view;
    }

    public void updateCoinList() {
        coinsListAdapter.notifyDataSetChanged();
    }
}
