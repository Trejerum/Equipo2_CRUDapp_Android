package com.example.equipo2_crudapp_android.tool_bar_ui.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.equipo2_crudapp_android.enums.ModificationDialogResponse;
import com.example.equipo2_crudapp_android.ui.ChangePasswordActivity;
import com.example.equipo2_crudapp_android.R;
import com.example.equipo2_crudapp_android.ui.MainActivity;
import com.example.equipo2_crudapp_android.ui.ModificationDialog;
import com.example.equipo2_crudapp_android.ui.SignInActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import equipo2_crudapp_classes.classes.User;

import static android.app.Activity.RESULT_OK;

/**
 * Fragment for the user view.
 * @author Diego Corral
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    /**
     * Opens ChangePasswordActivity for the user to change his password.
     */
    private Button buttonChangePassword;

    /**
     * EditText that shows the user's login. If edit mode is activated it allows the user to modify
     * his login.
     */
    private EditText editTextLogin;

    /**
     * EditText that shows the user's name. If edit mode is activated it allows the user to modify
     * his name.
     */
    private EditText editTextName;

    /**
     * EditText that shows the user's email. If edit mode is activated it allows the user to modify
     * his email.
     */
    private EditText editTextEmail;

    /**
     * Warning. It's shown if the login is not valid.
     */
    private TextView textViewLoginNotValid;

    /**
     * Warning. It's shown if the email if not valid.
     */
    private TextView textViewEmailNotValid;

    /**
     * Warning. It's shown if the name is not valid.
     */
    private TextView textViewNameNotValid;

    /**
     * Button that activates the edit mode. If pressed enables all the editText of the view, else
     * disables them and checks if its needed to modify the saved data.
     */
    private ToggleButton toggleButtonEdit;

    /**
     * Image of the user.
     */
    private ImageView imageViewUser;

    /**
     * Saves the state of the toggleButtonEdit.
     */
    private boolean toggleButtonEditIsPressed;

    /**
     * Opens the SignInActivity. Logs out of the application.
     */
    private Button buttonLogOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        initializeElements(root);

        /*UserInterface userInterface = UserAPIClient.getClient();
        Call<User> call = (Call<User>) userInterface.findUser(1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });*/


        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == buttonChangePassword.getId()) {
            Intent intent = new Intent(view.getContext(), ChangePasswordActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if(id == toggleButtonEdit.getId()) {
            handleEditAction(view);
        } else if(id == buttonLogOut.getId()) {
            Intent intent = new Intent(view.getContext(), SignInActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else if(id == imageViewUser.getId()) {
            if(toggleButtonEditIsPressed) {
                // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // startActivity(intent);
                takePhoto(view);
                MainActivity.takePhoto();
            }
        }
    }


    /**
     * Handles the toggleButtonEdit action. If pressed enables all the editText of the view, else
     * disables them and checks if its needed to modify the saved data.
     * @param view Current view.
     */
    private void handleEditAction(View view) {
        if(!toggleButtonEditIsPressed) {
            editTextLogin.setEnabled(true);
            editTextName.setEnabled(true);
            editTextEmail.setEnabled(true);

            toggleButtonEditIsPressed = true;
        } else {
            if(userDataIsModified()) {
                ModificationDialog modificationDialog = new ModificationDialog();
                modificationDialog.show(getFragmentManager(), "ModificationDialog");
                if(modificationDialog.getResponse() == ModificationDialogResponse.YES) {
                    if(userDataSyntaxIsCorrect()){
                        editTextLogin.setEnabled(false);
                        editTextName.setEnabled(false);
                        editTextEmail.setEnabled(false);

                        toggleButtonEditIsPressed = false;
                    } else {
                        toggleButtonEdit.setSelected(true);
                        toggleButtonEditIsPressed = true;
                    }
                } else if(modificationDialog.getResponse() == ModificationDialogResponse.NO) {
                    editTextLogin.setText(MainActivity.user.getLogin());
                    editTextName.setText(MainActivity.user.getFullName());
                    editTextEmail.setText(MainActivity.user.getEmail());

                    editTextLogin.setEnabled(false);
                    editTextName.setEnabled(false);
                    editTextEmail.setEnabled(false);

                    textViewLoginNotValid.setVisibility(View.INVISIBLE);
                    textViewNameNotValid.setVisibility(View.INVISIBLE);
                    textViewEmailNotValid.setVisibility(View.INVISIBLE);

                    toggleButtonEditIsPressed = false;
                } else {
                    toggleButtonEdit.setSelected(true);
                    toggleButtonEditIsPressed = true;
                }
            } else {
                editTextLogin.setEnabled(false);
                editTextName.setEnabled(false);
                editTextEmail.setEnabled(false);
                toggleButtonEditIsPressed = false;
            }
        }

    }

    /**
     * Checks if the data of the user has been modified
     * @return true if modified, otherwise false
     */
    private boolean userDataIsModified(){
        boolean ret = false;

        if(!editTextLogin.getText().equals(MainActivity.user.getLogin())
                || !editTextName.getText().equals(MainActivity.user.getFullName())
                || !editTextEmail.getText().equals(MainActivity.user.getEmail())) {
            ret = true;
        }

        return ret;
    }

    /**
     * Checks the syntax of the user's data fields.
     * @return true if corret, otherwise false.
     */
    private boolean userDataSyntaxIsCorrect() {
        boolean ret = true;
        final String NECESSARY_CHARS = "[a-zA-Z0-9\\.\\-\\*]+";

        // Validation of the login field
        if (editTextLogin.getText().length() >= 3
                && editTextLogin.getText().length() < 18
                && editTextLogin.getText().toString().matches(NECESSARY_CHARS)) {

            ret = true;
        } else {
            textViewLoginNotValid.setVisibility(View.VISIBLE);
            ret = false;
        }

        // Validation of the name field
        if (editTextName.getText().length() >= 3
                && editTextName.getText().length() < 64) {

            ret = true;
        } else {
            textViewNameNotValid.setVisibility(View.VISIBLE);
            ret = false;
        }

        // Validation of the email field
        if (editTextEmail.getText().length() >= 10
                && editTextEmail.getText().length() < 128
                && editTextEmail.getText().toString().
                matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            ret = true;
        } else {
            textViewEmailNotValid.setVisibility(View.VISIBLE);
            ret = false;
        }

        return ret;
    }

    /*private void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    }*/

    String currentPhotoPath;

    /**
     * Create an unique image name
     * @param view the view
     * @return image
     * @throws IOException
     */
    private File createImageFile(View view) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = view.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    private void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(view);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(view.getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                 getActivity().startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageViewUser.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Initializes the elements of the fragment. Finds the views, sets listeners, etc.
     * @param view Current view.
     */
    private void initializeElements(View view) {
        textViewLoginNotValid = view.findViewById(R.id.textViewLoginNotValid);
        textViewNameNotValid = view.findViewById(R.id.textViewNameNotValid);
        textViewEmailNotValid = view.findViewById(R.id.textViewEmailNotValid);
        toggleButtonEdit = view.findViewById(R.id.toggleButtonEdit);
        buttonChangePassword = view.findViewById(R.id.buttonChangePassword);
        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        buttonLogOut = view.findViewById(R.id.buttonLogOut);
        imageViewUser = view.findViewById(R.id.imageViewUser);

        textViewNameNotValid.setVisibility(View.INVISIBLE);
        textViewLoginNotValid.setVisibility(View.INVISIBLE);
        textViewEmailNotValid.setVisibility(View.INVISIBLE);

        buttonLogOut.setOnClickListener(this);
        buttonChangePassword.setOnClickListener(this);
        toggleButtonEdit.setOnClickListener(this);
        imageViewUser.setOnClickListener(this);
        toggleButtonEditIsPressed = false;

        editTextLogin.setEnabled(false);
        editTextName.setEnabled(false);
        editTextEmail.setEnabled(false);

        editTextLogin.setText(MainActivity.user.getLogin());
        editTextName.setText(MainActivity.user.getFullName());
        editTextEmail.setText(MainActivity.user.getEmail());
    }
}