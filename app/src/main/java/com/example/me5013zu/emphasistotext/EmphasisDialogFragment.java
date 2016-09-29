package com.example.me5013zu.emphasistotext;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SelectedChoiceListener) {
            mListener = (SelectedChoiceListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + "Must implement SelectedChoiceListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Emphasis your text!")
                .setMultiChoiceItems(choices, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedItem, boolean isChecked) {
                        if(isChecked) {
                            selectedItems.add(selectedItem);
                        } else if (selectedItems.contains(selectedItem)) {
                            selectedItems.remove(Integer.valueOf(selectedItem));
                        }
                        mListener.selectedChoice(selectedItems);
                    }

                });

        return builder.create();

        //http://stackoverflow.com/questions/16954196/alertdialog-with-checkbox-in-android
    }
}
