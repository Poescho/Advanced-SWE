package com.example.michael.kassenautomat_dhbw.list_adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.michael.kassenautomat_dhbw.OnButtonClickedCallback;
import com.example.michael.kassenautomat_dhbw.util.KassenautomatContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 26.06.2016.
 */
public class MyAdapter<T> extends ArrayAdapter<T> {

    Context context;
    OnButtonClickedCallback mCallback;


    /**
     * @param context Used in the super constructor for ArrayAdapter (internal Android system)
     * @param resource Used in the super constructor. List items will be represented as in the resource.
     * @param objects Stored in the variable 'data'. For each object in data a list row will be presented
     * @param mCallback Stored in te variable 'mCallback'. Callback functions for other fragments.
     */
    public MyAdapter(Context context, int resource, List<T> objects, OnButtonClickedCallback mCallback) {
        super(context, resource, objects);
        this.context = context;
        this.mCallback = mCallback;
    }
}
