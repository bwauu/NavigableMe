package com.portofos.navigableme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);

        // TODO: Implement google maps sdk api
        // 1. Click on "app" folder and press F4 || Right-click>app>Open Module Settings
        // 2. In settings go>Dependencies
        // 3. In Dependencies, click on '+' || (windows) alt+insert
        // 4. Click on  Library Dependency
        // 5. Search for "play-services-maps"
        // 6. Select artefact play-services-maps and click ok
        // 7. Click on 'apply'
        // 8. Click then on Ok.
        // Added Library Completed
    }
}