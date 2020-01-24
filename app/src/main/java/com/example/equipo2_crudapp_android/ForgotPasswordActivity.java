package com.example.equipo2_crudapp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewIntroduceEmail;
    private TextView textViewIntroduceCode;
    private EditText editTextChangePasswordEmail;
    private EditText editTextChangePasswordCode;
    private Button buttonEmailNext;
    private Button buttonCodeNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initializeElements();

        buttonEmailNext.setOnClickListener(this);
        buttonCodeNext.setOnClickListener(this);

    }

    private void initializeElements() {
        textViewIntroduceEmail = findViewById(R.id.textViewIntroduceEmail);
        textViewIntroduceCode = findViewById(R.id.textViewIntroduceCode);
        editTextChangePasswordEmail = findViewById(R.id.editTextChangePasswordEmail);
        editTextChangePasswordCode = findViewById(R.id.editTextChangePasswordCode);
        buttonEmailNext = findViewById(R.id.buttonEmailNext);
        buttonCodeNext = findViewById(R.id.buttonCodeNext);
    }

    @Override
    public void onClick(View v) {

    }
}
