package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.sqlite.AdminSQLiteOpenHelper;

import java.util.ArrayList;

import equipo2_crudapp_classes.classes.User;

/**
 * Controller for SignInActivity.
 * @author Diego Corral
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    /**
     * Warning. Its shown if the login is not valid.
     */
    private TextView textViewLoginWarning;

    /**
     * Warning. Its shown if the password is not valid.
     */
    private TextView textViewPasswordWarning;

    /**
     * Opens the SignUpActivity for the user to sign up in the application.
     */
    private TextView buttonSignUp;

    /**
     * If clicked opens the ForgotPasswordActivity for the user to reset his password.
     */
    private TextView textViewForgotPassword;

    /**
     * Signs in the application. Opens the MainActivity. Firstly validates the syntax of the data
     * introduced.
     */
    private Button buttonSignIn;

    /**
     * If activated remembers the current user's data for the next time he wants to sign into the
     * application.
     */
    private CheckBox checkBoxRemember;

    /**
     * EditText for the user to introduce his login.
     */
    private EditText editTextLogin;

    /**
     * EditText for the user to introduce his password.
     */
    private EditText editTextPassword;
    private ImageView imageViewLogo;

    /**
     * Validates the syntax of the data introduced. If valid true, otherwise false.
     */
    private boolean checkedSyntax;

    /**
     * Loads the user's credentials if the remember me option is activated.
     */
    private ArrayList<String> credentials = new ArrayList<>();

    /**
     * This method is executed when the activity is opened an sets all elements of the view.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializeElements();
        loadCredentialsIfRemembered();
    }

    /**
     *  Event for when an element is clicked.
     * @param v Element calling this method
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonSignUp) {
            /*buttonSignUp.setTextColor(getResources().getColor(R.color.visitedLink));

            Intent intent = new Intent(SignInActivity.this, SignUpController.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/
        } else if (v.getId() == R.id.buttonSignIn) {

            syntaxCheck();

            if (checkedSyntax){
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
                imageViewLogo.startAnimation(animation);
                User user = new User();
                user.setLogin(editTextLogin.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                if(checkBoxRemember.isChecked()) {
                    if(credentials.size() > 0) {
                        if(credentials.get(0) !=editTextLogin.getText().toString()
                                || credentials.get(1) != editTextPassword.getText().toString()) {
                            deleteCredentials(credentials.get(0));
                        }
                    }
                    insertCredentials();
                } else {
                    if(credentials.size() > 0) {
                        deleteCredentials(credentials.get(0));
                    }
                }
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.cust_slide_in, R.anim.cust_slide_out);
                finish();
            }
        } else if (v.getId() == R.id.textViewForgotPassword) {
            Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    /**
     * Delete saved credentials
     * @param login user's login
     */
    private void deleteCredentials(String login) {
        AdminSQLiteOpenHelper administration = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase database = administration.getWritableDatabase();

        try{
            database.delete("credentials","login = ?", new String[]{login});
        }catch(Exception e){
            Log.e("sqlite deleting error", e.getMessage());
        }
        database.close();
    }

    /**
     * Save the current user's credentials
     */
    private void insertCredentials() {

        AdminSQLiteOpenHelper administration = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase database = administration.getWritableDatabase();

        try{
            ContentValues record = new ContentValues();
            record.put("login", editTextLogin.getText().toString());
            record.put("password", editTextPassword.getText().toString());

            database.insert("credentials", null, record);
        }catch(Exception e){
            Log.e("sqlite insertion error", e.getMessage());
        }
        database.close();
    }

    /**
     * Loads the user credentials if the remember me option is activated.
     * @return user's credentials
     */
    private ArrayList<String> getCredentials() {
        AdminSQLiteOpenHelper administration = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase database = administration.getWritableDatabase();
        ArrayList<String> ret = new ArrayList<>();

        try{
            if(editTextLogin.getText().toString() != "" && editTextPassword.getText().toString() != "") {
                Cursor row = database.rawQuery("SELECT login, password from credentials", null);

                if(row.moveToFirst()) {
                    ret.add(row.getString(0));
                    ret.add(row.getString(1));
                }
            }
        }catch(Exception e){
            Log.e("sqlite selection error", e.getMessage());
        }
        database.close();

        return ret;
    }

    /**
     * Override of the method onFocusChange.
     * @param v Element calling this method.
     * @param hasFocus If the element has the focus or not.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            syntaxCheck();
        }
    }

    /**
     * Method to check the syntax of the fields, trimming them beforehand.
     */
    private void syntaxCheck() {
        checkedSyntax = true;
        editTextLogin.setText(editTextLogin.getText().toString().trim());
        editTextPassword.setText(editTextPassword.getText().toString().trim());

        if (editTextLogin.getText().toString().length() >= 3
                && editTextLogin.getText().toString().length() < 18
                && editTextLogin.getText().toString().matches("[a-zA-Z0-9\\.\\-\\*]+")) {

            textViewLoginWarning.setVisibility(View.INVISIBLE);
        } else if (!editTextLogin.getText().equals("")) {
            textViewLoginWarning.setVisibility(View.VISIBLE);
            checkedSyntax = false;
        }

        if (editTextPassword.getText().toString().length() >= 3
                && editTextPassword.getText().toString().length() < 18
                && editTextPassword.getText().toString().matches("[a-zA-Z0-9\\.\\-\\*]+")) {
            textViewPasswordWarning.setVisibility(View.INVISIBLE);
        }else if (!editTextPassword.getText().equals("")) {
            textViewPasswordWarning.setVisibility(View.VISIBLE);
            checkedSyntax = false;
        }
    }

    /**
     * Check if there're credentials of the user saved.
     */
    private void loadCredentialsIfRemembered() {
        credentials = getCredentials();
        if(credentials.size()>0) {
            editTextLogin.setText(credentials.get(0));
            editTextPassword.setText(credentials.get(1));
            checkBoxRemember.setChecked(true);
        }
    }

    /**
     * Initialize the elements of the activity. Finds the views, sets listeners, etc.
     */
    private void initializeElements() {
        textViewLoginWarning = findViewById(R.id.textViewLoginWarning);
        editTextLogin = findViewById(R.id.editTextLogin);
        textViewPasswordWarning = findViewById(R.id.textViewPasswordWarning);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);

        textViewLoginWarning.setVisibility(View.INVISIBLE);
        textViewPasswordWarning.setVisibility(View.INVISIBLE);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        editTextLogin.setOnFocusChangeListener(this);
        editTextPassword.setOnFocusChangeListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }
}
