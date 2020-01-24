package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

/**
 * Controller for
 */
public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewPasswordNotValid;
    private TextView textViewPasswordDoesntMatch;
    private TextView textViewPasswordIncorrect;
    private Button buttonCancel;
    private Button buttonChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initializeElements();

        textViewPasswordNotValid.setVisibility(View.INVISIBLE);
        textViewPasswordDoesntMatch.setVisibility(View.INVISIBLE);
        textViewPasswordIncorrect.setVisibility(View.INVISIBLE);

        buttonChangePassword.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
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

    private void initializeElements() {
        textViewPasswordNotValid = this.findViewById(R.id.textViewPasswordNotValid);
        textViewPasswordDoesntMatch = this.findViewById(R.id.textViewPasswordDoesntMatch);
        textViewPasswordIncorrect = this.findViewById(R.id.textViewPasswordIncorrect);
        buttonCancel = this.findViewById(R.id.buttonCancel);
        buttonChangePassword = this.findViewById(R.id.buttonChangePassword);
    }
}
