package com.example.equipo2_crudapp_android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.ui.adapters.ResultsListAdapter;

import java.util.ArrayList;
import java.util.List;

import equipo2_crudapp_classes.classes.Software;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextResultsSearch;
    private Button buttonResultsSearch;
    private RadioGroup radioGroupResults;
    private RadioButton radioButtonResultsName;
    private RadioButton radioButtonResultsPublisher;
    private RadioButton radioButtonResultsDate;
    private CheckBox checkBoxResultsProgram;
    private CheckBox checkBoxResultsExtension;
    private CheckBox checkBoxResultsGame;
    private RadioButton radioButtonResultsAscendent;
    private RadioButton radioButtonResultsDescendent;
    private ListView listViewResults;

    private List<Software> softwares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        editTextResultsSearch = this.findViewById(R.id.editTextResultsSearch);
        buttonResultsSearch = this.findViewById(R.id.buttonResultsSearch);
        radioButtonResultsName = this.findViewById(R.id.radioButtonResultsName);
        radioButtonResultsPublisher = this.findViewById(R.id.radioButtonResultsPublisher);
        radioButtonResultsDate = this.findViewById(R.id.radioButtonResultsDate);
        radioButtonResultsAscendent  =this.findViewById(R.id.radioButtonResultsAscendent);
        radioButtonResultsDescendent = this.findViewById(R.id.radioButtonResultsDescendent);
        checkBoxResultsProgram = this.findViewById(R.id.checkBoxResultsProgram);
        checkBoxResultsExtension = this.findViewById(R.id.checkBoxExtension);
        checkBoxResultsGame = this.findViewById(R.id.checkBoxResultsGame);
        listViewResults = this.findViewById(R.id.listViewResults);
        radioGroupResults = this.findViewById(R.id.radioGroupResults);
        softwares = new ArrayList<Software>();

        buttonResultsSearch.setOnClickListener(this);

        //TODO: recibir lista software
        Software s1 = new Software();
        Software s2 = new Software();
        s1.setSoftwareId(1);
        s2.setSoftwareId(2);
        s1.setName("s1");
        s2.setName("s2");

        softwares.add(s1);
        softwares.add(s2);

        ResultsListAdapter listAdapter = new ResultsListAdapter(this, softwares);
        listViewResults.setAdapter(listAdapter);
    }

    public void filter () {
        //TODO completar funcion
        switch (radioGroupResults.getCheckedRadioButtonId()) {
            case R.id.radioButtonResultsName:
                break;

            case R.id.radioButtonResultsPublisher:
                break;

            case R.id.radioButtonResultsDate:
                break;
        }

        if(checkBoxResultsProgram.isChecked()) {

        }
        if(checkBoxResultsExtension.isChecked()) {

        }
        if(checkBoxResultsGame.isChecked()) {

        }

        if(radioButtonResultsAscendent.isChecked()) {

        }
        else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonResultsSearch:
                break;
        }
    }
}