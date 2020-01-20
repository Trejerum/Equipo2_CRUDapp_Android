package com.example.equipo2_crudapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShopsActivity extends AppCompatActivity {

    private ArrayList<String> shopsNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsNames.add("Hola");
        shopsNames.add("Adios");
        shopsNames.add("kasjd");
        shopsNames.add("aksjdkb");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ShopsRecyclerViewAdapter adapter = new ShopsRecyclerViewAdapter(this, shopsNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
