package com.example.cricketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MydatabaseHelper myDB;
    ArrayList<String>  pl_id,ply_name,ply_match,ply_runs,ply_50,ply_100,ply_rate;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView =findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,AddActivity.class);
                startActivity(intent);

            }
        });
        myDB = new MydatabaseHelper(Home.this);
        pl_id = new ArrayList<>();
        ply_name = new ArrayList<>();
        ply_match = new ArrayList<>();
        ply_runs = new ArrayList<>();
        ply_50= new ArrayList<>();
        ply_100 = new ArrayList<>();
        ply_rate= new ArrayList<>();

        StoreDataArray();
        customAdapter = new CustomAdapter(Home.this, this,pl_id, ply_name, ply_match,ply_runs,ply_50,ply_100,ply_rate);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void  StoreDataArray(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                pl_id.add(cursor.getString(0));
                ply_name.add(cursor.getString(1));
                ply_match.add(cursor.getString(2));
                ply_runs.add(cursor.getString(3));
                ply_50.add(cursor.getString(4));
                ply_100.add(cursor.getString(5));
                ply_rate.add(cursor.getString(6));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirm();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure want to delete  All ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MydatabaseHelper myDB = new MydatabaseHelper(Home.this);
                myDB.deleteAllData();
                Intent intent = new Intent(Home.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });
        builder.create().show();
    }
}