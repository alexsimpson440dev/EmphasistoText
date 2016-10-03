package com.example.me5013zu.emphasistotext;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EmphasisDialogFragment.SelectedChoiceListener {

    //static string to send data to the emphasisDialogFragment
    public static String mEmphasizedText;
    EditText mEditText;
    Button mEmphasis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text_emphasis);

        mEmphasis = (Button) findViewById(R.id.emphasis_button);
        mEmphasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog and check boxes
                EmphasisDialogFragment dialog = EmphasisDialogFragment.newInstance();
                dialog.show(getFragmentManager(), "Emphasis Dialog");
            }
        });

        //on click listener sets the edit text field to nothing for the user
        //when it is clicked
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }

    @Override
    public void selectedChoice(ArrayList<Integer> choice) {
        //sets the static string equal to what ever is in the edit text field
        mEmphasizedText = mEditText.getText().toString();

        //if any of the choices contains one of the array indexes, it will add certain aspects/emphasises to the text
        if (choice.contains(0)) {
            mEmphasizedText = mEmphasizedText.toUpperCase();
        } if (choice.contains(1)) {
            mEmphasizedText += "!!!";
        } if (choice.contains(2)) {
            mEmphasizedText += ":)";
        }
    }
}
