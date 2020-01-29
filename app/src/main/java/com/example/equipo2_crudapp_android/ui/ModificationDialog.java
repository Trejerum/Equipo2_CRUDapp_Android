package com.example.equipo2_crudapp_android.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.equipo2_crudapp_android.enums.ModificationDialogResponse;
import com.example.equipo2_crudapp_android.R;

/**
 * Shows a confirmation dialog with three options: YES, NO, CANCEL.
 * @author Diego Corral
 */
public class ModificationDialog extends DialogFragment {

    /**
     * Response from the dialog.
     */
    private ModificationDialogResponse response;

    /**
     * Text shown in the dialog.
     */
    private String text = String.valueOf(R.string.modification_confirm);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.modification_confirm)
                .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        response = ModificationDialogResponse.YES;
                    }
                })
                .setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        response = ModificationDialogResponse.NO;
                    }
                }).setNeutralButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        response = ModificationDialogResponse.CANCEL;
                    }
                });

        return builder.create();
    }

    /**
     * Getter for the response.
     * @return YES, NO, CANCEL.
     */
    public ModificationDialogResponse getResponse() {
        return response;
    }

    /**
     * Setter for the response.
     * @param response Posible options: YES, NO, CANCEL.
     */
    public void setResponse(ModificationDialogResponse response) {
        this.response = response;
    }

    /**
     * Getter for the text of the dialog.
     * @return The text of the dialog.
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the text of the dialog.
     * @param text text for the dialog.
     */
    public void setText(String text) {
        this.text = text;
    }
}
