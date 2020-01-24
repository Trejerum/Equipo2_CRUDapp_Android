package com.example.equipo2_crudapp_android.tool_bar_ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.equipo2_crudapp_android.client.interfaces.UserInterface;
import com.example.equipo2_crudapp_android.client.retrofit.UserAPIClient;
import com.example.equipo2_crudapp_android.ui.ChangePasswordActivity;
import com.example.equipo2_crudapp_android.R;

import equipo2_crudapp_classes.classes.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment for the user view
 * @author Diego Corral
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    // private User user;
    private Button buttonChangePassword;
    private EditText editTextLogin;
    private EditText editTextName;
    private EditText editTextEmail;
    private TextView textViewLoginNotValid;
    private TextView textViewEmailNotValid;
    private TextView textViewNameNotValid;
    private ToggleButton toggleButtonEdit;
    private boolean toggleButtonEditIsPressed;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        editTextLogin = root.findViewById(R.id.editTextLogin);
        buttonChangePassword = root.findViewById(R.id.buttonChangePassword);

        buttonChangePassword.setOnClickListener(this);

        initializeElements(root);

        textViewNameNotValid.setVisibility(View.INVISIBLE);
        textViewLoginNotValid.setVisibility(View.INVISIBLE);
        textViewEmailNotValid.setVisibility(View.INVISIBLE);

        toggleButtonEdit.setOnClickListener(this);
        toggleButtonEditIsPressed = false;

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
        }
    }


    private void handleEditAction(View view) {
        if(!toggleButtonEditIsPressed) {
            editTextLogin.setEnabled(true);
            editTextName.setEnabled(true);
            editTextEmail.setEnabled(true);

            toggleButtonEditIsPressed = true;
        } else {
            /*if(userDataIsModified()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION
                        , "Do you want to save the changes?"
                        , ButtonType.NO, ButtonType.YES
                        , ButtonType.CANCEL);
                Button yesButton = (Button) alert.getDialogPane()
                        .lookupButton(ButtonType.YES);
                yesButton.setId("yesButton");
                Button noButton = (Button) alert.getDialogPane()
                        .lookupButton(ButtonType.NO);
                noButton.setId("noButton");
                Button cancelButton = (Button) alert.getDialogPane()
                        .lookupButton(ButtonType.CANCEL);
                cancelButton.setId("cancelButton");
                Optional<ButtonType> result = alert.showAndWait();

                if(result.isPresent() && result.get()==ButtonType.YES) {
                    if(userDataSyntaxIsCorrect()){
                        textFieldLogin.setDisable(true);
                        textFieldName.setDisable(true);
                        textFieldEmail.setDisable(true);

                        toggleButtonEditIsPressed = false;
                    } else {
                        toggleButtonEdit.setSelected(true);
                        toggleButtonEditIsPressed = true;
                    }
                } else if(result.isPresent() && result.get()==ButtonType.NO) {
                    textFieldLogin.setText(user.getLogin());
                    textFieldName.setText(user.getFullName());
                    textFieldEmail.setText(user.getEmail());

                    textFieldLogin.setDisable(true);
                    textFieldName.setDisable(true);
                    textFieldEmail.setDisable(true);

                    labelLoginNotValid.setVisible(false);
                    labelNameNotValid.setVisible(false);
                    labelEmailNotValid.setVisible(false);

                    toggleButtonEditIsPressed = false;
                } else {
                    toggleButtonEdit.setSelected(true);
                    toggleButtonEditIsPressed = true;
                }
            } else {
                textFieldLogin.setDisable(true);
                textFieldName.setDisable(true);
                textFieldEmail.setDisable(true);
                toggleButtonEditIsPressed = false;
            }*/
        }

    }

    /**
     * Checks if the data of the user has been modified
     * @return true if modified, otherwise false
     */
    private boolean userDataIsModified(){
        boolean ret = false;

        /*if(!editTextLogin.getText().equalsIgnoreCase(user.getLogin())
                || !editTextName.getText().equals(user.getFullName())
                || !editTextEmail.getText().equals(user.getEmail())) {
            ret = true;
        }*/

        return ret;
    }

    private void initializeElements(View view) {
        textViewLoginNotValid = view.findViewById(R.id.textViewLoginNotValid);
        textViewNameNotValid = view.findViewById(R.id.textViewNameNotValid);
        textViewEmailNotValid = view.findViewById(R.id.textViewEmailNotValid);
        toggleButtonEdit = view.findViewById(R.id.toggleButtonEdit);
        buttonChangePassword = view.findViewById(R.id.buttonChangePassword);
        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
    }
}