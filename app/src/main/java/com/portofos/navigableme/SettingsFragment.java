package com.portofos.navigableme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SettingsFragment extends Fragment {

    private TextView mShowCount;
    private TextView mPrompt;
    private int mCount = 0;
    private int mCap = 10;
    private int availableEntries;
    private Button enterBtn;
    private Button leaveBtn;

    // TODO: Import Random class


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        enterBtn = (Button) view.findViewById(R.id.button_enter);
        leaveBtn = (Button) view.findViewById(R.id.button_leave);


        mShowCount = (TextView) view.findViewById(R.id.show_count);
        mPrompt = (TextView) view.findViewById(R.id.textview_prompter);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;

                availableEntries = mCap - mCount;
                if (mCount >= mCap ) {
                    if(mCount == 10) {
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

}
