package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

/**
 * Controller for the forgot password activity.
 * @author Diego Corral
 */
public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Text that shows the user what he has to introduce in the editText. In this case the email
     * where the code for changing the password is going to be sent.
     */
    private TextView textViewIntroduceEmail;

    /**
     * Text that shows the user what he has to introduce in the editText. In this case the Code
     * sent to his email account.
     */
    private TextView textViewIntroduceCode;

    /**
     * EditText for the user to introduce his email account.
     */
    private EditText editTextChangePasswordEmail;

    /**
     * EditText for the user to introduce the code sent to his email account.
     */
    private EditText editTextChangePasswordCode;

    /**
     * Changes from the email introduction to the code validation.
     */
    private Button buttonEmailNext;

    /**
     * Opens the changeForgotPasswordActivity for the user to change his password.
     */
    private Button buttonCodeNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initializeElements();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Initialize the elements of the activity. Finds the views, sets listeners, etc.
     */
    private void initializeElements() {
        textViewIntroduceEmail = findViewById(R.id.textViewIntroduceEmail);
        textViewIntroduceCode = findViewById(R.id.textViewIntroduceCode);
        editTextChangePasswordEmail = findViewById(R.id.editTextChangePasswordEmail);
        editTextChangePasswordCode = findViewById(R.id.editTextChangePasswordCode);
        buttonEmailNext = findViewById(R.id.buttonEmailNext);
        buttonCodeNext = findViewById(R.id.buttonCodeNext);

        buttonEmailNext.setOnClickListener(this);
        buttonCodeNext.setOnClickListener(this);
    }
}
