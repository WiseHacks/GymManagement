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
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerGymHall;
import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.GymHall;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerAvailActivity extends AppCompatActivity {
    private String CT;
    RecyclerView AvailTList;
     List<Trainer> finalTrainers = new ArrayList<>();
     ArrayList<Boolean> visited = new ArrayList<>();
     Map<Trainer,Integer> vs = new HashMap<>();
     private String temp;
//    private ArrayList<Trainer> FinalTrainer = new ArrayList<>();s
    DataHandlerGymHall dbHall = new DataHandlerGymHall(this);
    //TODO : IF date2>date1 && time2<time1 and ......if date2<date1 and month2>month1 &&tim2<t1 --->or --> d2<d1,m2<m1<y2>y1 || okay--optional
    DataHandlerTrainer dbTrainer  = new DataHandlerTrainer(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_avail);
        AvailTList = findViewById(R.id.AvailTList);
        List<Trainer> trainers = dbTrainer.getTrainers();
        List<GymHall> gymHalls = dbHall.getHalls();
        Calendar currentTime = Calendar.getInstance();
        CT = String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY));
        Log.d("Er","Current = " + CT);
        if(gymHalls.size()==0){
            Toast.makeText(this, "Hall list is empty", Toast.LENGTH_SHORT).show();
        }
        else {
            for (GymHall g :
                    gymHalls) {
                if ((Integer.parseInt(CT) >= Integer.parseInt(g.getHrs())) && (Integer.parseInt(CT) <= (Integer.parseInt(g.getHrs()) + 12))) {
                    for (Trainer t :
                            trainers) {
                        if ((Integer.parseInt(CT) >= Integer.parseInt(t.getAssigned())) && (Integer.parseInt(CT) <= (Integer.parseInt(t.getAssigned()) + 7))) {
                            try {
                                if (vs.get(t) == 0) {
                                    temp = "oom";
                                }
                            } catch (Exception e) {
                                vs.put(t, 1);
                                finalTrainers.add(t);
                            }
                        }
                    }
                }
            }

            for (Trainer t :
                    finalTrainers) {
                Log.d("Er", "name = " + t.getName());
            }
            if (finalTrainers.size() == 0) {
                Toast.makeText(this, "No Trainer Available At This Hour", Toast.LENGTH_SHORT).show();
            } else {
                CustomAvailable adapter = new CustomAvailable(TrainerAvailActivity.this, TrainerAvailActivity.this, finalTrainers, dbTrainer);
                AvailTList.setAdapter(adapter);
                AvailTList.setLayoutManager(new LinearLayoutManager(TrainerAvailActivity.this));
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

    }
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
}