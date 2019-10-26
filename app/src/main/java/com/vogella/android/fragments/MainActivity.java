package com.vogella.android.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFrag.ItemSelected {

    // refers to tvDescription from DetailFragment
    TextView tvDescription;
    String [] descriptions; // array of strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescription = findViewById(R.id.tvDescription);

        // getting descriptions array from resources array file
        descriptions = getResources().getStringArray(R.array.descriptions);

        // the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {

            FragmentManager manager = this.getSupportFragmentManager();

            // if phone is in portrait mode, I want to show the listFragment
            // & hide the detailFragment
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();
        }

        // The phone is in landscape mode
        if (findViewById(R.id.layout_land) != null) {

            FragmentManager manager = this.getSupportFragmentManager();

            // if phone is in landscape mode, I want to show both fragments
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();
        }

    }

    @Override
    public void onItemSelected(int index) {

        tvDescription.setText(descriptions[index]);

        // phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {

            FragmentManager manager = this.getSupportFragmentManager();

            // if phone is in portrait mode, I want to show the listFragment
            // & hide the detailFragment
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .hide(manager.findFragmentById(R.id.listFrag))
                    .addToBackStack(null) // allows for back navigation without destroying activity
                    .commit();
        }
    }
}
