package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
  EditText Name,editTextNumber,editTextNumber2,editTextNumber4,editTextNumber5,editTextNumber7;
  Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Name=findViewById(R.id.Name);
        editTextNumber=findViewById(R.id.editTextNumber);
        editTextNumber2=findViewById(R.id.editTextNumber2);
        editTextNumber4=findViewById(R.id.editTextNumber4);
        editTextNumber5=findViewById(R.id.editTextNumber5);
        editTextNumber7=findViewById(R.id.editTextNumber7);

        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MydatabaseHelper myDB = new MydatabaseHelper(AddActivity.this);
                myDB.addplayer(
                        Name.getText().toString().trim(),
                        Integer.valueOf(editTextNumber.getText().toString().trim()),
                        Integer.valueOf(editTextNumber2.getText().toString().trim()),
                        Integer.valueOf(editTextNumber4.getText().toString().trim()),
                        Integer.valueOf(editTextNumber5.getText().toString().trim()),
                        editTextNumber7.getText().toString().trim()


                );

                finish();
            }
        });

    }
}