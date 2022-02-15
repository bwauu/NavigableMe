package com.portofos.navigableme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.URL;

public class HomeFragment extends Fragment {

    private ImageView ad1;
    private ImageView ad2;
    private ImageView ad3;

    public String ad1Url = "https://www.mcdonalds.com/se/sv-se/just-nu/donkenmeals.html";
    public String ad2Url = "https://www.levi.com/SE/sv_SE/klader/herr/jeans/501-levis-original-jeans/p/005013190";
    public String ad3Url = "https://www.filmstaden.se/";
    // Save onCreateView. This will be needed later in project
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ad1 = view.findViewById(R.id.clickable_mcdonalds_img);
        ad2 = view.findViewById(R.id.clickable_levis_img);
        ad3 = view.findViewById(R.id.clickable_filmstaden_img);
        // TODO: Implement clickable ImageViews
        // ad1.setClickable(true);
        /*
        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */

        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUniformResourceLocator(ad1Url);
            }
        });

        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUniformResourceLocator(ad2Url);
            }
        });

        ad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUniformResourceLocator(ad3Url);
            }
        });
        return view;
    }

    private void goToUniformResourceLocator(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}