package com.wordpress.priyankvex.couchbasesimplesample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextCity;
    FloatingActionButton buttonSave;
    TextView textViewRecordCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        findViewsById();
    }

    private void setUpToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void findViewsById(){
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        buttonSave = (FloatingActionButton) findViewById(R.id.fab);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
            }
        });
    }

    private void saveForm(){
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String city = editTextCity.getText().toString();
        Map<String, Object> content = new HashMap<>();
        content.put("firstName", firstName);
        content.put("lastName", lastName);
        content.put("city", city);
        DatabaseHelper.saveDocument(content);
        Toast.makeText(getApplicationContext(), "Form Saved", Toast.LENGTH_SHORT).show();
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextCity.setText("");
        // Update the display
        updateDocumentCount();
    }

    private void updateDocumentCount(){
        int documentCount = ApplicationController.getInstance().getDatabase().getDocumentCount();
        textViewRecordCount.setText("Records Saved : " + documentCount);
    }
}
