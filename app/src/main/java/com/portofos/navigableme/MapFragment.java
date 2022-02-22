package com.portofos.navigableme;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment  {

    private Bundle args;
    private String usersSearchFragmentItem;

    private String sSource, sDestination;

    private String currentMall = "Taby Centrum";

    EditText etSource, etDestination;
    Button btTrack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        etSource = view.findViewById(R.id.et_source);
        etDestination = view.findViewById(R.id.et_destination);
        btTrack = view.findViewById(R.id.bt_track);

        args = getArguments();
        if (args != null) {
            try {
                usersSearchFragmentItem  = (String) args.get("selectedItemKey");
                Log.e("MapFragmentonCreateView", usersSearchFragmentItem);
                // searchView.setQuery(usersSearchFragmentItem,false);
                if(!usersSearchFragmentItem.isEmpty()) {
                    sDestination = usersSearchFragmentItem;
                    etDestination.setText(sDestination + ", "+currentMall);
                    Log.e("a", sDestination);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                Log.e("MapFragmentonCreateView", "Caught" + e);
            }
        }


        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get value from edit text
                String sSource= etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                // check condition

                if(sSource.equals("") && sDestination.equals("")) {
                    // wHEN BOTH VALUE BLANK
                    Toast.makeText(getActivity(), "Enter both location", Toast.LENGTH_SHORT).show();
                }
                else {
                    // when both values filled
                    // display tack
                    DisplayTrack(sSource,sDestination);
                }
            }
        });


        // Return view
        return view;
    }

    private void DisplayTrack(String sSource, String sDestination) {
        // if the device does not have a map installed, then redirect  it to play store

        try {
            // when google map is installed
            // intitialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"
                    + sSource + "/"
                    + sDestination);
            // init intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // When google map is not installed
            // Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            // init intent with action
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}