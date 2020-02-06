package com.example.equipo2_crudapp_android.tool_bar_ui.add_software;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.ui.DatePickerFragment;

public class AddSoftwareFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener{

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

    private boolean checkedFields = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_software, container, false);

        super.onCreate(savedInstanceState);

        editTextSoftwareName = root.findViewById(R.id.editTextSoftwareName);
        textViewSoftwareNameWarning = root.findViewById(R.id.textViewSoftwareNameWarning);
        spinnerSoftwareType = root.findViewById(R.id.spinnerSoftwareType);
        textViewSoftwareTypeWarning = root.findViewById(R.id.textViewSoftwareTypeWarning);
        editTextParentSoftware = root.findViewById(R.id.autoCompleteTextViewParentSoftware);
        textViewParentSoftwareWarning = root.findViewById(R.id.textViewParentSoftwareWarning);
        editTextPublisher = root.findViewById(R.id.editTextPublisher);
        textViewPublisherWarning = root.findViewById(R.id.textViewPublisherWarning);
        editTextReleaseDate = root.findViewById(R.id.editTextReleaseDate);
        textViewReleaseDateWarning = root.findViewById(R.id.textViewReleaseDateWarning);
        editTextDescription = root.findViewById(R.id.editTextDescription);
        textViewDescriptionWarning = root.findViewById(R.id.textViewDescriptionWarning);

        Button buttonCancel = root.findViewById(R.id.buttonCancel);
        Button buttonAccept = root.findViewById(R.id.buttonAccept);

        editTextReleaseDate.setOnClickListener(this);
        buttonAccept.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        editTextSoftwareName.setOnFocusChangeListener(this);
        editTextParentSoftware.setOnFocusChangeListener(this);
        editTextPublisher.setOnFocusChangeListener(this);
        editTextDescription.setOnFocusChangeListener(this);

        // Makes the textView warning that the field is empty invisible when the user writes
        // something in the description.
        editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textViewDescriptionWarning.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(), R.array.SoftwareTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSoftwareType.setAdapter(adapter);
        spinnerSoftwareType.setSelection(1);

        // When the user selects the option EXTENSION, enables the editTextParentSoftware.
        spinnerSoftwareType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewSoftwareTypeWarning.setVisibility(View.INVISIBLE);

                if (position == 2) {
                    editTextParentSoftware.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    /**
     * On click event to fire the functions of the editTextReleaseDate and the buttons cancel and
     * accept.
     *
     * @param view element firing the event.
     */
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

    /**
     * This method handles the event fired when the button accept is pressed. It launches the method
     * to check the validity of the fields and then makes sure that there are no empty fields. If
     * everything is correct, it creates a new software with the data from the fields in the
     * database.
     */
    private void handleButtonAccept() {
        checkFields();

        if (editTextSoftwareName.getText().toString().equals("")) {
            textViewSoftwareNameWarning.setVisibility(View.VISIBLE);
            textViewSoftwareNameWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextPublisher.getText().toString().equals("")) {
            textViewPublisherWarning.setVisibility(View.VISIBLE);
            textViewPublisherWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextParentSoftware.getText().toString().equals("")) {
            textViewParentSoftwareWarning.setVisibility(View.VISIBLE);
            textViewParentSoftwareWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextReleaseDate.getText().toString().equals("")) {
            textViewReleaseDateWarning.setVisibility(View.VISIBLE);
            textViewReleaseDateWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (spinnerSoftwareType.getSelectedItem() == null) {
            textViewReleaseDateWarning.setVisibility(View.VISIBLE);
            checkedFields = false;
        }
        if (editTextDescription.getText().toString().equals("")) {
            textViewDescriptionWarning.setVisibility(View.VISIBLE);
            checkedFields = false;
        }

        if (checkedFields) {

        }
    }

    /**
     * Method to show a DatePicker when the user clicks on the editTextReleaseDate.
     */
    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTextReleaseDate.setText(selectedDate);
        }
        });

        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * Method used when the editTexts lose focus. It trims the text from the editTexts so there are
     * no whitespaces at the beginning or at the end and then launches the method checkFields().
     *
     * @param v view triggering the event.
     * @param hasFocus whether it has been focused or it has lost focus.
     */
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

    /**
     * Method to check the validity of the fields. If any of the fields is not correct, it makes the
     * warning editText visible and sets its text to the warning from strings.xml.
     */
    private void checkFields() {

        checkedFields = true;

        if (editTextSoftwareName.getText().length() >= 3
                && editTextSoftwareName.getText().length() < 18
                && editTextSoftwareName.getText().toString().matches("[a-zA-Z0-9.\\-*_]+")) {

            textViewSoftwareNameWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextSoftwareName.getText().toString().equals("")) {
            textViewSoftwareNameWarning.setVisibility(View.VISIBLE);
            textViewSoftwareNameWarning.setText(R.string.textViewSoftwareNameWarning);
            checkedFields = false;
        }

        if (editTextPublisher.getText().length() >= 3
                && editTextPublisher.getText().length() < 18
                && editTextPublisher.getText().toString().matches("[a-zA-Z0-9.\\-*_]+")) {

            textViewPublisherWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextPublisher.getText().toString().equals("")) {
            textViewPublisherWarning.setVisibility(View.VISIBLE);
            textViewPublisherWarning.setText(R.string.textViewPublisherWarning);
            checkedFields = false;
        }

        if (editTextParentSoftware.isEnabled()) {
            if (editTextParentSoftware.getText().length() >= 3
                    && editTextParentSoftware.getText().length() < 18
                    && editTextParentSoftware.getText().toString().matches("[a-zA-Z0-9.\\-*_]+")) {

                textViewParentSoftwareWarning.setVisibility(View.INVISIBLE);
            } else if (!editTextParentSoftware.getText().toString().equals("")) {
                textViewParentSoftwareWarning.setVisibility(View.VISIBLE);
                textViewParentSoftwareWarning.setText(R.string.textViewParentSoftwareWarning);
                checkedFields = false;
            }
        }
    }
}