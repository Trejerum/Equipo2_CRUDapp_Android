package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddSoftwareActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText editTextSoftwareName;
    private TextView textViewSoftwareNameWarning;
    private Spinner spinnerSoftwareType;
    private TextView textViewSoftwareTypeWarning;
    private AutoCompleteTextView editTextParentSoftware;
    private TextView textViewParentSoftwareWarning;
    private EditText editTextPublisher;
    private TextView textViewPublisherWarning;
    private EditText editTextReleaseDate;
    private TextView textViewReleaseDateWarning;
    private EditText editTextDescription;
    private TextView textViewDescriptionWarning;
    private Button buttonCancel;
    private Button buttonAccept;

    private boolean checkedFields = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_software);

        editTextSoftwareName = findViewById(R.id.editTextSoftwareName);
        textViewSoftwareNameWarning = findViewById(R.id.textViewSoftwareNameWarning);
        spinnerSoftwareType = findViewById(R.id.spinnerSoftwareType);
        textViewSoftwareTypeWarning = findViewById(R.id.textViewSoftwareTypeWarning);
        editTextParentSoftware = findViewById(R.id.autoCompleteTextViewParentSoftware);
        textViewParentSoftwareWarning = findViewById(R.id.textViewParentSoftwareWarning);
        editTextPublisher = findViewById(R.id.editTextPublisher);
        textViewPublisherWarning = findViewById(R.id.textViewPublisherWarning);
        editTextReleaseDate = findViewById(R.id.editTextReleaseDate);
        textViewReleaseDateWarning = findViewById(R.id.textViewReleaseDateWarning);
        editTextDescription = findViewById(R.id.editTextDescription);
        textViewDescriptionWarning = findViewById(R.id.textViewDescriptionWarning);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonAccept = findViewById(R.id.buttonAccept);

        editTextReleaseDate.setOnClickListener(this);

        editTextSoftwareName.setOnFocusChangeListener(this);
        editTextParentSoftware.setOnFocusChangeListener(this);
        editTextPublisher.setOnFocusChangeListener(this);
        editTextDescription.setOnFocusChangeListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.SoftwareTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSoftwareType.setAdapter(adapter);
        spinnerSoftwareType.setSelection(1);

        spinnerSoftwareType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewSoftwareTypeWarning.setVisibility(View.INVISIBLE);

                if (position == 3) {
                    editTextParentSoftware.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                handleButtonAccept();
                break;
        }
    }

    private void handleButtonAccept() {
        checkFields();

        if (editTextSoftwareName.getText().equals("")) {
            textViewSoftwareNameWarning.setVisibility(View.VISIBLE);
            textViewSoftwareNameWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextPublisher.getText().equals("")) {
            textViewPublisherWarning.setVisibility(View.VISIBLE);
            textViewPublisherWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextParentSoftware.getText().equals("")) {
            textViewParentSoftwareWarning.setVisibility(View.VISIBLE);
            textViewParentSoftwareWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextReleaseDate.getText().equals("")) {
            textViewReleaseDateWarning.setVisibility(View.VISIBLE);
            textViewReleaseDateWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (spinnerSoftwareType.getSelectedItem() == null) {
            textViewReleaseDateWarning.setVisibility(View.VISIBLE);
            checkedFields = false;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTextReleaseDate.setText(selectedDate);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (!hasFocus) {
            editTextSoftwareName.setText(editTextSoftwareName.getText().toString().trim());
            editTextPublisher.setText(editTextPublisher.getText().toString().trim());
            editTextDescription.setText(editTextDescription.getText().toString().trim());

            if (editTextParentSoftware.isEnabled()) {
                editTextParentSoftware.setText(editTextParentSoftware.getText().toString().trim());
            }

            checkFields();
        }
    }

    private void checkFields() {

        checkedFields = true;

        if (editTextSoftwareName.getText().length() >= 3
                && editTextSoftwareName.getText().length() < 18
                && editTextSoftwareName.getText().toString().matches("[a-zA-Z0-9\\.\\-\\*\\_]+")) {

            textViewSoftwareNameWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextSoftwareName.getText().equals("")) {
            textViewSoftwareNameWarning.setVisibility(View.VISIBLE);
            textViewSoftwareNameWarning.setText(R.string.textViewSoftwareNameWarning);
            checkedFields = false;
        }

        if (editTextPublisher.getText().length() >= 3
                && editTextPublisher.getText().length() < 18
                && editTextPublisher.getText().toString().matches("[a-zA-Z0-9\\.\\-\\*\\_]+")) {

            textViewPublisherWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextPublisher.getText().equals("")) {
            textViewPublisherWarning.setVisibility(View.VISIBLE);
            textViewPublisherWarning.setText(R.string.textViewPublisherWarning);
            checkedFields = false;
        }

        if (editTextParentSoftware.isEnabled()) {
            if (editTextParentSoftware.getText().length() >= 3
                    && editTextParentSoftware.getText().length() < 18
                    && editTextParentSoftware.getText().toString().matches("[a-zA-Z0-9\\.\\-\\*\\_]+")) {

                textViewParentSoftwareWarning.setVisibility(View.INVISIBLE);
            } else if (!editTextParentSoftware.getText().equals("")) {
                textViewParentSoftwareWarning.setVisibility(View.VISIBLE);
                textViewParentSoftwareWarning.setText(R.string.textViewParentSoftwareWarning);
                checkedFields = false;
            }
        }

        if (LocalDate.parse(editTextReleaseDate.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).isBefore(LocalDate.now().plusDays(1))) {
            textViewReleaseDateWarning.setVisibility((View.INVISIBLE));
        } else if (editTextReleaseDate.getText().equals("")) {
            textViewReleaseDateWarning.setVisibility((View.VISIBLE));
            textViewReleaseDateWarning.setText(R.string.textViewReleaseDateWarning);
        }
    }
}
