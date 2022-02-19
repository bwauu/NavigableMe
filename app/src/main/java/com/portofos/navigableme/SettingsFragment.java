package com.portofos.navigableme;

import android.os.Bundle;

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
    private int mCount;
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

        mCap = getRandomInteger(1,100);
        mCount = getRandomInteger(getRandomInteger(1,mCap), mCap);
        mShowCount.setText(String.valueOf(mCount));

        mPrompt.setText("Det finns " + Integer.toString(mCap-mCount) + " platser tillgängliga");

        mallCap.setText("Total people allowed in store " + String.valueOf(mCap));
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;

                availableEntries = mCap - mCount;
                if (mCount >= mCap ) {
                    if(mCount == mCap) {
                        mShowCount.setText(Integer.toString(mCount));
                    }
                    mPrompt.setText("Gallerian är full. Vänligen vänta");
                    mCount = mCap;
                }
                else  {

                    mShowCount.setText(Integer.toString(mCount));
                    mPrompt.setText("Det finns " + Integer.toString(availableEntries) + " platser tillgängliga");
                }

            }
        });

        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --mCount;
                availableEntries = mCap - mCount;

                if (mCount < 0) {
                    mCount = 0;
                    mPrompt.setText("Det finns " + Integer.toString(availableEntries-1) + " platser tillgängliga");
                }

                else {
                    mShowCount.setText(Integer.toString(mCount));
                    mPrompt.setText("Det finns " + Integer.toString(availableEntries) + " platser tillgängliga");
                }



            }
        });

        return view;
    }

    public int getRandomInteger(int min, int max) {

        Random rand;
        rand = new Random();

        int result  = rand.nextInt(max - min + 1) + min;

        return result;
    }

}
