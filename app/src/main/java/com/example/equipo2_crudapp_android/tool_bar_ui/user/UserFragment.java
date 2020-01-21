package com.example.equipo2_crudapp_android.tool_bar_ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.equipo2_crudapp_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserFragment extends Fragment implements View.OnClickListener {

    private Button buttonChangePassword;
    private EditText editTextLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        editTextLogin = root.findViewById(R.id.editTextLogin);
        buttonChangePassword = root.findViewById(R.id.buttonChangePassword);

        buttonChangePassword.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonChangePassword) {

        }
    }
}