package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

/**
 * Class to add a new offer to the database.
 */
public class AddOfferActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private EditText editTextSoftwareName;
    private TextView textViewSoftwareNameWarning;
    private EditText editTextShop;
    private TextView textViewShopWarning;
    private EditText editTextExpirationDate;
    private TextView textViewExpirationDateWarning;
    private EditText editTextBasePrice;
    private TextView textViewBasePriceWarning;
    private EditText editTextDiscount;
    private TextView textViewDiscountWarning;
    private EditText editTextUrl;
    private TextView textViewUrlWarning;

    private boolean checkedFields = false;

    /**
     * Method to set up the view, assigning events and listeners to its elements.
     *
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

        editTextSoftwareName = findViewById(R.id.editTextSoftwareName);
        textViewSoftwareNameWarning = findViewById(R.id.textViewSoftwareNameWarning);
        editTextShop = findViewById(R.id.editTextOfferShop);
        textViewShopWarning = findViewById(R.id.textViewOfferShopWarning);
        editTextExpirationDate = findViewById(R.id.editTextOfferExpirationDate);
        textViewExpirationDateWarning = findViewById(R.id.textViewExpirationDateWarning);
        editTextBasePrice = findViewById(R.id.editTextBasePrice);
        textViewBasePriceWarning = findViewById(R.id.textViewBasePriceWarning);
        editTextDiscount = findViewById(R.id.editTextDiscount);
        textViewDiscountWarning = findViewById(R.id.textViewDiscountWarning);
        editTextUrl = findViewById(R.id.editTextUrl);
        textViewUrlWarning = findViewById(R.id.textViewUrlWarning);

        textViewSoftwareNameWarning.setVisibility(View.INVISIBLE);
        textViewShopWarning.setVisibility(View.INVISIBLE);
        textViewExpirationDateWarning.setVisibility(View.INVISIBLE);
        textViewBasePriceWarning.setVisibility(View.INVISIBLE);
        textViewDiscountWarning.setVisibility(View.INVISIBLE);
        textViewUrlWarning.setVisibility(View.INVISIBLE);

        Button buttonAccept = findViewById(R.id.buttonAccept);
        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonAccept.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        editTextSoftwareName.setOnFocusChangeListener(this);
        editTextShop.setOnFocusChangeListener(this);
        editTextBasePrice.setOnFocusChangeListener(this);
        editTextDiscount.setOnFocusChangeListener(this);
        editTextUrl.setOnFocusChangeListener(this);

        editTextExpirationDate.setOnClickListener(this);
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
                editTextSoftwareName.setText("Repollo");
                showDatePickerDialog();
                break;
            case R.id.buttonCancel:
                Intent intent = new Intent(AddOfferActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonAccept:
                handleButtonAccept();
                break;
        }
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
            editTextShop.setText(editTextShop.getText().toString().trim());
            editTextBasePrice.setText(editTextBasePrice.getText().toString().trim());
            editTextDiscount.setText(editTextDiscount.getText().toString().trim());
            editTextUrl.setText(editTextUrl.getText().toString().trim());

            checkFields();
        }
    }

     /**
     * This method handles the event fired when the button accept is pressed. It launches the method
     * to check the validity of the fields and then makes sure that there are no empty fields. If
     * everything is correct, it creates a new offer with the data from the fields in the
     * database.
     */
    private void handleButtonAccept() {
        checkFields();

        if (editTextSoftwareName.getText().toString().equals("")) {
            textViewSoftwareNameWarning.setVisibility(View.VISIBLE);
            textViewSoftwareNameWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextShop.getText().toString().equals("")) {
            textViewShopWarning.setVisibility(View.VISIBLE);
            textViewShopWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextExpirationDate.getText().toString().equals("")) {
            textViewExpirationDateWarning.setVisibility(View.VISIBLE);
            textViewExpirationDateWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextBasePrice.getText().toString().equals("")) {
            textViewBasePriceWarning.setVisibility(View.VISIBLE);
            textViewBasePriceWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextDiscount.getText().toString().equals("")) {
            textViewDiscountWarning.setVisibility(View.VISIBLE);
            textViewDiscountWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }
        if (editTextUrl.getText().toString().equals("")) {
            textViewUrlWarning.setVisibility(View.VISIBLE);
            textViewUrlWarning.setText(R.string.emptyTextViewWarning);
            checkedFields = false;
        }

        if (checkedFields) {

        }
    }

    /**
     * Method to show a DatePicker when the user clicks on the editTextExpirationDate.
     */
    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                editTextExpirationDate.setText(selectedDate);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
        editTextUrl.setText("Patata");
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
        if (editTextShop.getText().length() >= 3
                && editTextShop.getText().length() < 18
                && editTextShop.getText().toString().matches("[a-zA-Z0-9.\\-*_]+")) {

            textViewShopWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextShop.getText().toString().equals("")) {
            textViewShopWarning.setVisibility(View.VISIBLE);
            textViewShopWarning.setText(R.string.textViewShopWarning);
            checkedFields = false;
        }
        if (editTextBasePrice.getText().length() >= 3
                && editTextBasePrice.getText().length() < 18
                && editTextBasePrice.getText().toString().matches("^[0-9]{1,3}([,.][0-9]{1,2})?$")) {

            textViewBasePriceWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextBasePrice.getText().toString().equals("")) {
            textViewBasePriceWarning.setVisibility(View.VISIBLE);
            textViewBasePriceWarning.setText(R.string.textViewBasePriceWarning);
            checkedFields = false;
        }
        if (editTextDiscount.getText().length() >= 3
                && editTextDiscount.getText().length() < 18
                && editTextDiscount.getText().toString().matches("\\d{1,2}")) {

            textViewDiscountWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextDiscount.getText().toString().equals("")) {
            textViewDiscountWarning.setVisibility(View.VISIBLE);
            textViewDiscountWarning.setText(R.string.textViewDiscountWarning);
            checkedFields = false;
        }
        if (editTextUrl.getText().length() >= 3
                && editTextUrl.getText().length() < 18
                && editTextUrl.getText().toString().matches("[a-zA-Z0-9.*_/=?\\-()'|@#$&]+")) {

            textViewUrlWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextUrl.getText().toString().equals("")) {
            textViewUrlWarning.setVisibility(View.VISIBLE);
            textViewUrlWarning.setText(R.string.textViewUrlWarning);
            checkedFields = false;
        }
    }
}
