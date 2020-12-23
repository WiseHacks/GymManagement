package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.testingproject.DataHandler.DataHandlerGymHall;
import com.example.testingproject.models.GymHall;

import java.util.ArrayList;
import java.util.List;

public class HallDetailsActivity extends AppCompatActivity {
    private Button btnAddHall;
    RecyclerView HallList;
    DataHandlerGymHall db=new DataHandlerGymHall(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details);
        btnAddHall=findViewById(R.id.btnAddHall);
        HallList = findViewById(R.id.HallList);
        btnAddHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HallDetailsActivity.this,HallFormActivity.class);
                startActivity(intent);
            }
        });
        List<GymHall> gymHalls;
        gymHalls = db.getHalls();
        CustomHall adapter = new CustomHall(HallDetailsActivity.this,HallDetailsActivity.this,gymHalls,db);
        HallList.setAdapter(adapter);
        HallList.setLayoutManager(new LinearLayoutManager(HallDetailsActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }

    }*/
   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case android.R.id.home:
               onBackPressed();
           default:
               break;
       }
       return super.onOptionsItemSelected(item);
   }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HallDetailsActivity.this,StartActivity.class);
        startActivity(intent);
        finish();
    }
}