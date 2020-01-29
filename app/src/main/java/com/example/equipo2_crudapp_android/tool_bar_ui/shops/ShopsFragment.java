package com.example.equipo2_crudapp_android.tool_bar_ui.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.client.interfaces.ShopInterface;
import com.example.equipo2_crudapp_android.client.retrofit.ShopAPIClient;
import com.example.equipo2_crudapp_android.ui.adapters.ShopsRecyclerViewAdapter;

import java.util.ArrayList;

import equipo2_crudapp_classes.classes.Shop;
import equipo2_crudapp_classes.classes.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Fragment for the shops view.
 * @author Diego Corral
 */
public class ShopsFragment extends Fragment {

    /**
     * The name of each shop.
     */
    private ArrayList<String> shopsNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        loadData();

        initRecyclerView(root);

        return root;
    }

    /**
     * Loads the shops registered in the application.
     */
    private void loadData() {
        shopsNames.add("Hola");
        shopsNames.add("Adios");
        shopsNames.add("kasjd");
        shopsNames.add("aksjdkb");
        shopsNames.add("Adios");
        shopsNames.add("kasjd");
        shopsNames.add("aksjdkb");
        shopsNames.add("Adios");
        shopsNames.add("kasjd");
        shopsNames.add("aksjdkb");

        ShopInterface shopInterface = ShopAPIClient.getClient();
        Shop shop = new Shop("EpicGames", "www.epicgames.com");
        // Call<Shop> call = (Call<Shop>) shopInterface.createShop();
        Call<SimpleXmlConverterFactory> call = shopInterface.createShop(shop);

        /*call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {

            }
        });*/
    }

    /**
     * Inits the recycler view with its adapter and loads in it the loaded shops.
     * @param root The view.
     */
    private void initRecyclerView(View root){
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        ShopsRecyclerViewAdapter adapter = new ShopsRecyclerViewAdapter(root.getContext(), shopsNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}