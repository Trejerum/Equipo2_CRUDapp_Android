package com.example.equipo2_crudapp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class AddSoftwareActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextSoftwareName;
    private Spinner spinnerSoftwareType;
    private AutoCompleteTextView editTextParentSoftware;
    private EditText editTextPublisher;
    private EditText editTextReleaseDate;
    private EditText editTextDescription;
    private Button buttonCancel;
    private Button buttonAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_software);

        editTextSoftwareName = findViewById(R.id.editTextSoftwareName);
        spinnerSoftwareType = findViewById(R.id.spinnerSoftwareType);
        editTextParentSoftware = findViewById(R.id.autoCompleteTextViewParentSoftware);
        editTextPublisher = findViewById(R.id.editTextPublisher);
        editTextReleaseDate = findViewById(R.id.editTextReleaseDate);
        editTextDescription = findViewById(R.id.editTextDescription);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonAccept = findViewById(R.id.buttonAccept);

        editTextReleaseDate.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.SoftwareTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSoftwareType.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editTextReleaseDate:
                showDatePickerDialog();
                break;
            case R.id.buttonCancel:

                break;
            case R.id.buttonAccept:

                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTextReleaseDate.setText(selectedDate);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
