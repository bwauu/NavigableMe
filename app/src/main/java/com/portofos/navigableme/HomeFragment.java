package com.portofos.navigableme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    private ImageView ad1;
    private ImageView ad2;
    private ImageView ad3;
    // Save onCreateView. This will be needed later in project
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ad1 = view.findViewById(R.id.clickable_mcdonalds_img);
        ad2 = view.findViewById(R.id.clickable_levis_img);
        ad3 = view.findViewById(R.id.clickable_filmstaden_img);

        return view;
    }


}