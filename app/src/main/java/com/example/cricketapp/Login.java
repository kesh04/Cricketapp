

package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  Login  extends AppCompatActivity {
    Button btnView;
    EditText username,password;

    String user;
    String pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnView=findViewById(R.id.btn1);
     username = findViewById(R.id.username);
     password = findViewById(R.id.password);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = username.getText().toString();
                pw =password.getText().toString();

                if (user.equals("Admin")&&pw.equals("1234")){
                    Toast.makeText(Login.this, "Login", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}