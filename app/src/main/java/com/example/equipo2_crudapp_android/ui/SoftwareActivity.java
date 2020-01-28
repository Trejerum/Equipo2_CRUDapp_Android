package com.example.equipo2_crudapp_android.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.equipo2_crudapp_android.R;

public class SoftwareActivity extends AppCompatActivity {
    private EditText editTextSoftwareTitle;
    private EditText editTextSoftwarePublisher;
    private EditText editTextSoftwareReleaseDate;
    private CheckBox checkBoxSoftwareEdit;
    private Spinner spinnerSoftwareType;
    private EditText editTextSoftwareDescription;
    private ListView listViewSoftwareExtensions;
    private ListView listViewSoftwareOffers;
    private ImageView imageViewSoftware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_activity);

        editTextSoftwareTitle = this.findViewById(R.id.editTextSoftwareTitle);
        editTextSoftwarePublisher = this.findViewById(R.id.editTextSoftwarePublisher);
        editTextSoftwareDescription = this.findViewById(R.id.editTextSoftwareDescription);

    }
}
