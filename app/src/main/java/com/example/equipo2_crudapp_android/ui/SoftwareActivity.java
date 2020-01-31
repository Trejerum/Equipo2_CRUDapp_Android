package com.example.equipo2_crudapp_android.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.equipo2_crudapp_android.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import equipo2_crudapp_classes.classes.Offer;
import equipo2_crudapp_classes.classes.Software;
import equipo2_crudapp_classes.enumerators.SoftwareType;

public class SoftwareActivity extends AppCompatActivity implements View.OnClickListener {
    private Software software;
    private EditText editTextSoftwareTitle;
    private EditText editTextSoftwarePublisher;
    private TextView textViewSoftwareDate;
    private CheckBox checkBoxSoftwareEdit;
    private Spinner spinnerSoftwareType;
    private EditText editTextSoftwareDescription;
    private ListView listViewSoftwareExtensions;
    private ListView listViewSoftwareOffers;
    private ImageView imageViewSoftware;

    private List<Offer> offers;
    private List<Software> extensions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_activity);

        editTextSoftwareTitle = this.findViewById(R.id.editTextSoftwareTitle);
        editTextSoftwarePublisher = this.findViewById(R.id.editTextSoftwarePublisher);
        editTextSoftwareDescription = this.findViewById(R.id.editTextSoftwareDescription);
        textViewSoftwareDate = this.findViewById(R.id.textViewSoftwareDate);
        checkBoxSoftwareEdit = this.findViewById(R.id.checkBoxSoftwareEdit);
        spinnerSoftwareType = this.findViewById(R.id.spinnerSoftwareType);
        listViewSoftwareExtensions = this.findViewById(R.id.listViewSoftwareExtensions);
        listViewSoftwareOffers = this.findViewById(R.id.listViewSoftwareOffers);
        imageViewSoftware = this.findViewById(R.id.imageViewSoftware);

        //offers = software.getOffers();

        textViewSoftwareDate.setOnClickListener(this);
        spinnerSoftwareType.setAdapter(new ArrayAdapter<SoftwareType>(this, android.R.layout.simple_spinner_item, SoftwareType.values()));
        checkBoxSoftwareEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                View view = getWindow().getDecorView().getRootView();
                toggleEdit();
            }
        });
        toggleEdit();
        //TODO: recibir software
        //populateData(software);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSoftwareDate:
                showDatePickerDialog();
                break;
        }
    }


    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void toggleEdit () {
        if(checkBoxSoftwareEdit.isChecked()) {
            checkBoxSoftwareEdit.setText("Save");

            setReadOnly(editTextSoftwareTitle, false);
            setReadOnly(editTextSoftwarePublisher, false);
            setReadOnly(textViewSoftwareDate, false);
            setReadOnly(editTextSoftwareDescription, false);
            spinnerSoftwareType.setEnabled(true);
        }
        else {
            checkBoxSoftwareEdit.setText("Edit");
            hideKeyboard(getWindow().getDecorView().getRootView());
            setReadOnly(editTextSoftwareTitle, true);
            setReadOnly(editTextSoftwarePublisher, true);
            setReadOnly(textViewSoftwareDate, true);
            setReadOnly(editTextSoftwareDescription, true);
            spinnerSoftwareType.setEnabled(false);


        }
    }

    private void populateData(Software software) {
        editTextSoftwareTitle.setText(software.getName());
        editTextSoftwarePublisher.setText(software.getPublisher());
        textViewSoftwareDate.setText(software.getReleaseDate().toString());
        editTextSoftwareDescription.setText(software.getDescription());
        spinnerSoftwareType.setSelection(software.getSoftwareType().ordinal());

        List<String> offersUrl = new ArrayList<String>();
        for (int i = 0; i < offers.size(); i++) {
            offersUrl.add(offers.get(i).getUrl());
        }
        ArrayAdapter<String> arrayOffersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, offersUrl);
        listViewSoftwareOffers.setAdapter(arrayOffersAdapter);

//        List<String> extensionName = new ArrayList<String>();
//        for (int i = 0; i < extensions.size(); i++) {
//            extensionName.add(extensions.get(i).getName());
//        }
//        ArrayAdapter<String> arrayExtensionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, extensionName);
//        listViewSoftwareOffers.setAdapter(arrayExtensionsAdapter);

        imageViewSoftware.setImageResource(0);
    }

    private void updateData() {
        //TODO: probar populateData() y updateData()
        Software softwareUpdate = new Software();
        softwareUpdate.setName(editTextSoftwareTitle.getText().toString());
        softwareUpdate.setPublisher(editTextSoftwarePublisher.getText().toString());
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        try {
            softwareUpdate.setReleaseDate(dateFormat.parse(textViewSoftwareDate.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        softwareUpdate.setSoftwareType(((SoftwareType) spinnerSoftwareType.getSelectedItem()));

        //TODO: enviar software actualizado
    }

    private  void setReadOnly(TextView editText, boolean readOnly) {
        editText.setFocusable(!readOnly);
        editText.setFocusableInTouchMode(!readOnly);
        editText.setClickable(!readOnly);
        editText.setLongClickable(!readOnly);
        editText.setCursorVisible(!readOnly);
    }

    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                textViewSoftwareDate.setText(selectedDate);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
