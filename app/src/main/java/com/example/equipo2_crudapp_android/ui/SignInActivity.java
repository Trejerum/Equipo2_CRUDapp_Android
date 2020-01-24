package com.example.equipo2_crudapp_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.equipo2_crudapp_android.R;

/**
 * Controller for SignInActivity
 * @author Diego Corral
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private TextView textViewLoginWarning;
    private TextView textViewPasswordWarning;
    private TextView buttonSignUp;
    private Button buttonSignIn;
    private boolean connection;

    private EditText editTextLogin;
    private EditText editTextPassword;

    private boolean checkedSyntax;

    /**
     * This method is executed when the activity is opened an sets all elements of the view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textViewLoginWarning = findViewById(R.id.textViewLoginWarning);
        editTextLogin = findViewById(R.id.editTextLogin);

        textViewPasswordWarning = findViewById(R.id.textViewPasswordWarning);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSignIn = (Button)findViewById(R.id.buttonSignIn);

        buttonSignUp = findViewById(R.id.buttonSignUp);

        Button buttonExit = findViewById(R.id.buttonExit);

        textViewLoginWarning.setVisibility(View.INVISIBLE);
        textViewPasswordWarning.setVisibility(View.INVISIBLE);

        buttonSignIn.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        editTextLogin.setOnFocusChangeListener(this);
        editTextPassword.setOnFocusChangeListener(this);
    }

    /**
     *  Event for when an element is clicked.
     *
     * @param v Element calling this method
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonSignUp) {
            /*buttonSignUp.setTextColor(getResources().getColor(R.color.visitedLink));

            Intent intent = new Intent(SignInController.this, SignUpController.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/
        } else if (v.getId() == R.id.buttonSignIn) {

            syntaxCheck();

            if (checkedSyntax){
                /*User user = new User(editTextLogin.getText().toString(), editTextPassword.getText().toString());
                clientMessage = new Message();
                clientMessage.setData(user);
                clientMessage.setMessageText(MessageText.SIGNIN);
                clientThread = new ClientThread(clientMessage, getString(R.string.serverAddress),
                        Integer.parseInt(getString(R.string.port)));
                clientThread.start();
                try {
                    clientThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                serverMessage = clientThread.getServerMessage();
                if(serverMessage == null){
                    Toast toast =Toast.makeText(getApplicationContext(), "Error connecting " +
                            "to the server...", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(serverMessage.getMessageText() == MessageText.OK){
                    Toast toast = Toast.makeText(getApplicationContext()
                            , "Sign in completed successfully", Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("SignUpController", "Sign up completed successfully...");

                    */
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    // intent.putExtra("user", (User)serverMessage.getData());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }/*else if(serverMessage.getMessageText() == MessageText.ERROR){
                    if(serverMessage.getData() instanceof ServerException){
                        Toast toast = Toast.makeText(getApplicationContext()
                                , "Error trying to connect " +
                                        "to the server. Please try again later...", Toast.LENGTH_SHORT);
                        toast.show();
                    }else if(serverMessage.getData() instanceof IncorrectUserException){
                        Toast toast = Toast.makeText(getApplicationContext()
                                , "The user is not correct...", Toast.LENGTH_SHORT);
                        toast.show();
                    }else if(serverMessage.getData() instanceof IncorrectPasswordException){
                        Toast toast = Toast.makeText(getApplicationContext()
                                , "The password is not correct...", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }*/
        } else if (v.getId() == R.id.buttonExit) {

            finish();
            System.exit(0);
        }
    }

    /**
     * Override of the method onFocusChange.
     *
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
        } else if (!editTextPassword.getText().equals("")) {
            textViewPasswordWarning.setVisibility(View.VISIBLE);
            checkedSyntax = false;
        }
    }
}
