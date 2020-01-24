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
import com.example.equipo2_crudapp_android.ui.adapters.ShopsRecyclerViewAdapter;

import java.util.ArrayList;

public class ShopsFragment extends Fragment {

    private ArrayList<String> shopsNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

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

        initRecyclerView(root);

        return root;
    }

    private void initRecyclerView(View root){
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        ShopsRecyclerViewAdapter adapter = new ShopsRecyclerViewAdapter(root.getContext(), shopsNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
    }
}