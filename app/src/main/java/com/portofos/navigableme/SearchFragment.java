package com.portofos.navigableme;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Button findBtn;
    private TextView textUpdate;
    private SearchView searchView;
    private ListView listViewStores;
    private ArrayAdapter<String> adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        textUpdate = view.findViewById(R.id.selectedTextView);
        searchView = view.findViewById(R.id.search_bar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] storeItems = {"R.O.O.M.", "Lexington", "Spral", "ESPRIT", "Himla", "Tesla", "JoyShop", "Jackie", "Scorett", "Cubus", "Systembolaget", "Tavex", "Subway", "Smarteyes", "Picard", "Le Creuset"};

        listViewStores = (ListView) view.findViewById(R.id.store_list_items);
        adapter = new
                ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,storeItems);

        listViewStores.setAdapter(adapter);

        // Search functionality implementation
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchFragment.this.adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                SearchFragment.this.adapter.getFilter().filter(s);
                return false;
            }
        });

        listViewStores.setOnItemClickListener(SearchFragment.this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // get user's clicked item from ui list view
        String selectedItem = (String) adapterView.getItemAtPosition(i);
        // set text to clicked item
        textUpdate.setText(selectedItem);
        // Create new fragment and transaction
        MapFragment mapFragment = new MapFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in Fragment "container" view with new fragment
        transaction.replace(R.id.container, mapFragment);
        // Add transaction to the back stack
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

}
