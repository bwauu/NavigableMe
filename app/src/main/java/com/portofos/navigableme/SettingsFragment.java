package com.portofos.navigableme;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    // TODO: Import Random class


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        enterBtn = (Button) view.findViewById(R.id.button_enter);
        leaveBtn = (Button) view.findViewById(R.id.button_leave);



        mallCap = (TextView) view.findViewById(R.id.textview_cap);
        mShowCount = (TextView) view.findViewById(R.id.show_count);
        mPrompt = (TextView) view.findViewById(R.id.textview_prompter);

        mCap = 10;
        customersInStoreCounter = getRandomInteger(getRandomInteger(1,mCap), mCap);
        mShowCount.setText(String.valueOf(customersInStoreCounter));

        mPrompt.setText("Det finns " + Integer.toString(mCap- customersInStoreCounter) + " platser tillgängliga");

        mallCap.setText("Total people allowed in store " + String.valueOf(mCap));
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // DESIRED FUNCTIONALITY. For each enter (incrementation of mCount) change
                // to more yellow green mid green than yellow. when mCount reach mCap
                // color of mPrompt should be red.
                customersInStoreCounter++;

                availableEntries = mCap - customersInStoreCounter;
                if (customersInStoreCounter >= mCap ) {
                    if(customersInStoreCounter == mCap) {
                        mShowCount.setText(Integer.toString(customersInStoreCounter));
                    }

                    mPrompt.setText("Gallerian är full. Vänligen vänta.");
                    mPrompt.setTextColor(Color.parseColor("#FF0000"));
                    customersInStoreCounter = mCap;
                }
                else  {

                    mPrompt.setTextColor(Color.parseColor("#00FF00"));
                    mShowCount.setText(Integer.toString(customersInStoreCounter));
                    mPrompt.setText("There are " + Integer.toString(availableEntries) + " entries available.");

                    // If customers in store is more than  to 50% of total capacity then
                    // display prompt text as Dark Yellow (medium traffic)
                    if (customersInStoreCounter > mCap * 0.5) {
                        double percentage = customersInStoreCounter / mCap;
                        mPrompt.setTextColor(Color.parseColor("#F6BE00"));
                        mPrompt.setText("Hurry! Store is almost full!");
                    }
                }

            }
        });

        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    if (customersInStoreCounter >= mCap * 0.5) {
                        mPrompt.setTextColor(Color.parseColor("#F6BE00"));
                        mPrompt.setText("Hurry! Store is almost full!");
                    }
                }


            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public int getRandomInteger(int min, int max) {

        Random rand;
        rand = new Random();

        int result  = rand.nextInt(max - min + 1) + min;

        return result;
    }

}
