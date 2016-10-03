package com.example.me5013zu.emphasistotext;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by me5013zu on 9/27/16.
 */


public class EmphasisDialogFragment extends DialogFragment {


    //choices in an array
    final CharSequence[] choices = {"Capitalize", "Add Exclamation Points", "Add a Smiley"};
    //array list to keep selected choices
    final ArrayList<Integer> selectedItems = new ArrayList<Integer>();

    //interface for listener
    interface SelectedChoiceListener {
        void selectedChoice(ArrayList<Integer> choice);
    }

    private SelectedChoiceListener mListener;

    //new instance - no arguments
    public static EmphasisDialogFragment newInstance() {
        EmphasisDialogFragment fragment = new EmphasisDialogFragment();
        return fragment;
    }

    //attaches a listener to the activity
    //activity is then linked to the mainactivity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SelectedChoiceListener) {
            mListener = (SelectedChoiceListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + "Must implement SelectedChoiceListener");
        }
    }

    //builds the dialog box
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //sets the title
        builder.setTitle("Emphasis your text!")
                //adds multiple choices to the dialog box with the array/charSequence as the choices
                .setMultiChoiceItems(choices, null, new DialogInterface.OnMultiChoiceClickListener() {
                    //adds which ever choices clicked to the array and removes them if unclicked.
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedItem, boolean isChecked) {
                        if(isChecked) {
                            selectedItems.add(selectedItem);
                        } else if (selectedItems.contains(selectedItem)) {
                            selectedItems.remove(Integer.valueOf(selectedItem));
                        }
                        //sends which items were clicked to the selectedChoice array and method in the MainActivity
                        mListener.selectedChoice(selectedItems);
                    }


                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selectedItems.isEmpty()) {
                            //does nothing, closes the dialog box.
                        } else {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                            builder2.setTitle("Your Emphasized Text!")
                                    .setMessage(MainActivity.mEmphasizedText)
                                    .setPositiveButton(android.R.string.ok, null)
                                    .create();
                            builder2.show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing, closes the dialog box.
                    }
                });

        return builder.create();

        //this link helped me develop code for the mulitple choice dialog boxes
        //http://stackoverflow.com/questions/16954196/alertdialog-with-checkbox-in-android
    }
}
