package com.portofos.navigableme;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;


public class SettingsFragment extends Fragment {

    private TextView mShowCount;
    private TextView mPrompt;
    private TextView mallCap;
    private int customersInStoreCounter;
    private int mCap;
    private int availableEntries;
    private Button enterBtn;
    private Button leaveBtn;

    private String[] hexColors = {"#00FF00","#F6BE00","#FF0000"};

    // TODO: Implement onSaveState. Save availbable enteries.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initializeAllFragmentVariables(view);

        mPrompt.setText("Det finns " + Integer.toString(mCap- customersInStoreCounter) + " platser tillgängliga");

        mallCap.setText("Total people allowed in store " + String.valueOf(mCap));
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                incrementClickLogia();

            }
        });


        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                decrementClickLogia();

            }
        });

        if(savedInstanceState != null) {
            customersInStoreCounter = savedInstanceState.getInt("count");
            mShowCount.setText(customersInStoreCounter);

        }

        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", customersInStoreCounter);
    }

    private int getRandomInteger(int min, int max) {

        Random rand;
        rand = new Random();

        int result  = rand.nextInt(max - min + 1) + min;

        return result;
    }

    private void incrementClickLogia(){

        customersInStoreCounter++;

        availableEntries = mCap - customersInStoreCounter;
        if (customersInStoreCounter >= mCap ) {
            if(customersInStoreCounter == mCap) {
                mShowCount.setText(Integer.toString(customersInStoreCounter));
            }

            mPrompt.setText("Gallerian är full. Vänligen vänta.");
            // set color to red
            mPrompt.setTextColor(Color.parseColor(hexColors[2]));
            customersInStoreCounter = mCap;
        }
        else  {

            mPrompt.setTextColor(Color.parseColor(hexColors[0]));
            mShowCount.setText(Integer.toString(customersInStoreCounter));
            mPrompt.setText("There are " + Integer.toString(availableEntries) + " entries available.");

            // If customers in store is more than  to 50% of total capacity then
            // display prompt text as Dark Yellow (medium traffic)
            if (customersInStoreCounter > mCap * 0.5) {
                double percentage = customersInStoreCounter / mCap;
                mPrompt.setTextColor(Color.parseColor(hexColors[1]));
                mPrompt.setText("Hurry! Store is almost full!");
            }
        }

    }

    private void decrementClickLogia() {
        --customersInStoreCounter;
        availableEntries = mCap - customersInStoreCounter;

        if (customersInStoreCounter < 0) {
            customersInStoreCounter = 0;
            mPrompt.setText("There are " + Integer.toString(availableEntries-1) + " entries available.");
        }

        else {
            mPrompt.setTextColor(Color.parseColor("#00FF00"));
            mShowCount.setText(Integer.toString(customersInStoreCounter));
            mPrompt.setText("There are " + Integer.toString(availableEntries) + " entries available.");

            if (customersInStoreCounter > mCap * 0.5) {
                mPrompt.setTextColor(Color.parseColor("#F6BE00"));
                mPrompt.setText("Hurry! Store is almost full!");
            }
        }
    }


    private void initializeAllFragmentVariables(View view) {
        enterBtn = (Button) view.findViewById(R.id.button_enter);
        leaveBtn = (Button) view.findViewById(R.id.button_leave);



        mallCap = (TextView) view.findViewById(R.id.textview_cap);
        mShowCount = (TextView) view.findViewById(R.id.show_count);
        mPrompt = (TextView) view.findViewById(R.id.textview_prompter);

        mCap = getRandomInteger(1, 100);
        customersInStoreCounter = getRandomInteger(getRandomInteger(1,mCap), mCap);
        mShowCount.setText(String.valueOf(customersInStoreCounter));
    }

}
