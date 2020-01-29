package com.example.equipo2_crudapp_android.tool_bar_ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.ui.adapters.HomeRecyclerViewAdapter;
import com.example.equipo2_crudapp_android.ui.adapters.ShopsRecyclerViewAdapter;

import java.util.ArrayList;

import equipo2_crudapp_classes.classes.User;

/**
 * Fragment for the home view.
 * @author Diego Corral
 */
public class HomeFragment extends Fragment {

    /**
     * The name of each offer.
     */
    private ArrayList<String> offerNames = new ArrayList<>();

    /**
     * The name discounted price of each offer.
     */
    private ArrayList<String> offerDiscountedPrices = new ArrayList<>();

    /**
     * The image of each offer
     */
    private ArrayList<byte[]> offerImages = new ArrayList<>();

    /**
     * The shops of each offer.
     */
    private ArrayList<String> offerShops = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        loadData();
        initRecyclerView(root);
        return root;
    }

    /**
     * Loads the offers saved in the application.
     */
    private void loadData() {
        offerNames.add("Ejemplo 1");
        offerDiscountedPrices.add("50");
        offerShops.add("Steam");

        offerNames.add("Ejemplo 2");
        offerDiscountedPrices.add("30");
        offerShops.add("Epic Games");

        offerNames.add("Ejemplo 3");
        offerDiscountedPrices.add("10");
        offerShops.add("Origin");

        offerNames.add("Ejemplo 4");
        offerDiscountedPrices.add("20");
        offerShops.add("Steam");

        offerNames.add("Ejemplo 5");
        offerDiscountedPrices.add("80");
        offerShops.add("Origin");

        offerNames.add("Ejemplo 6");
        offerDiscountedPrices.add("12");
        offerShops.add("Steam");

        offerNames.add("Ejemplo 7");
        offerDiscountedPrices.add("23");
        offerShops.add("Origin");

        offerNames.add("Ejemplo 8");
        offerDiscountedPrices.add("34");
        offerShops.add("Steam");

        offerNames.add("Ejemplo 9");
        offerDiscountedPrices.add("40");
        offerShops.add("Steam");

        offerNames.add("Ejemplo 10");
        offerDiscountedPrices.add("20");
        offerShops.add("Uplay");
    }

    /**
     * Inits the recycler view with its adapter and loads in it the loaded shops.
     * @param root The view.
     */
    private void initRecyclerView(View root){
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewFeaturedOffers);
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(root.getContext(),
                offerNames, offerDiscountedPrices, offerImages, offerShops);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}