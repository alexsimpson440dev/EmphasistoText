package com.example.me5013zu.emphasistotext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EmphasisDialogFragment.SelectedChoiceListener {

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
    }

    @Override
    public void selectedChoice(ArrayList<Integer> choice) {
        mEditText.setText(choice.toString());
    }
}
