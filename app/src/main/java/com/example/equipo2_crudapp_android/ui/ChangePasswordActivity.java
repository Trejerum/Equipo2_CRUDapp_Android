package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

/**
 * Controller for the change password view.
 * @author Diego Corral
 */
public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Warning. It's shown if the password is not valid.
     */
    private TextView textViewPasswordNotValid;

    /**
     * Warning. It's shown if the passwords doesn't match.
     */
    private TextView textViewPasswordDoesntMatch;

    /**
     * Warning. It's shown if the old password is incorrect.
     */
    private TextView textViewPasswordIncorrect;

    /**
     * Finishes this activity. Cancels the password changing.
     */
    private Button buttonCancel;

    /**
     * Changes the user's password
     */
    private Button buttonChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initializeElements();

    }

    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();

        if(id == buttonChangePassword.getId()) {

        } else {
            finish();
        }
    }

    /**
     * Initializes the elements of the activity. Finds the views, sets listeners, etc.
     */
    private void initializeElements() {
        textViewPasswordNotValid = this.findViewById(R.id.textViewPasswordNotValid);
        textViewPasswordDoesntMatch = this.findViewById(R.id.textViewPasswordDoesntMatch);
        textViewPasswordIncorrect = this.findViewById(R.id.textViewPasswordIncorrect);
        buttonCancel = this.findViewById(R.id.buttonCancel);
        buttonChangePassword = this.findViewById(R.id.buttonChangePassword);

        textViewPasswordNotValid.setVisibility(View.INVISIBLE);
        textViewPasswordDoesntMatch.setVisibility(View.INVISIBLE);
        textViewPasswordIncorrect.setVisibility(View.INVISIBLE);

        buttonChangePassword.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }
}
