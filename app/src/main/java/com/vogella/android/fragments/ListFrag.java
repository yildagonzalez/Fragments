package com.vogella.android.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends ListFragment {

    // activity that I will make a link to
    ItemSelected activity;

    // Interface needed to communicate between fragments
    public interface  ItemSelected {

        // method that sends the data through
        void onItemSelected(int index); // just want to pass the index selected in the list
    }


    public ListFrag() {
        // Required empty public constructor
    }

    // called when the fragment has been associated with the activity
    // context is the activity passed in, link to the activity that is attaching the fragment
    // the CONTEXT is the activity that attached the specific fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // cast to specific interface
        // context refers to the main activity
        // casting the main activity to an interface called ItemSelected
        activity = (ItemSelected) context;
    }

    // called when activity's onCreate method is basically finished
    // helps us set all the stuff after we made a connection to the activity
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // string array with data to place in the listView
        String [] data = getResources().getStringArray(R.array.pieces);


        // in order to put this data in the listView inside the fragment:
        // 1(parameter): getActivity() will get us the activity that attached this fragment
        // 2: predefined layout
        // 3. data that you want to display in the list
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data));
        // to start the activity with detail of item 1

        // gets activity that's hosting our fragment (main activity)
        // phone is in landscape mode
        if (this.getActivity().findViewById(R.id.layout_land) != null) {
            activity.onItemSelected(0);
        }

    }

    // when an item in the list gets clicked, we want to send through the data to the main activity
    // if an item in the list gets clicked, we will know exactly at which position it was clicked
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        // activity is a connection to the main activity
        // refer to the main activity (activity)
        // then call the method of the interface (.onItemSelected)
        // pass through the position
        activity.onItemSelected(position);
    }
}
