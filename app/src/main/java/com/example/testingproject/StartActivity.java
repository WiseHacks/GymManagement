package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class StartActivity extends AppCompatActivity {
    private Button btnMemberDetails,btnTrainerDetails,btnGymHallDetails,btnAbout,btnAvailableTrainers,btnExercisePlan,btnEquipment,btnFeedback;
    /*private final String feedbackEmail = "lbccpvtgroup@gmail.com";
    private final String feedbackSubject = "Feedback for Gym-Management Application";
    private final String feedbackBody = "Any kind of Feedback ->";
    private final String chooserTitle = "Feedback";*/

    //    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initValues();/*
        View someView = findViewById(R.id.startViewid);
        View root = someView.getRootView();
        root.setBackground(getResources().getDrawable(R.drawable.dots));*/

        btnMemberDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MemberDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnTrainerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,TrainerDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnGymHallDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,HallDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnAvailableTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,TrainerAvailActivity.class);
                startActivity(intent);
            }
        });
        btnExercisePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,PlanDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,EquipmentDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                builder.setTitle("Gym Management -");
                builder.setMessage("Developed By Divyesh :)\n\nInstructions :\n\n" +
                        "1.Gym must be closed before 12 AM\n" +
                        "2.Trainers work for 8 hrs, Gym hall are opened for 12 hrs\n" +
                        "3.Subscription plan is active for 30 days\n" +
                        "4.Time shown in card of each class is of 24hr format\n" +
                        "5.Choose plan from the list of exercise plan and this can be null too\n" +
                        "6.Equipments are available in different halls, can be moved to other halls and change availability in the list");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Go to Web", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(StartActivity.this,WebsiteActivity.class);
                        intent.putExtra("url","https://www.google.com/");
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
              emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"lbccpvtgroup@gmail.com"});
              emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback for Gym-Management Application");
              emailIntent.putExtra(Intent.EXTRA_TEXT,"Any kind of Feedback ->");
              startActivity(Intent.createChooser(emailIntent,"Send Email.."));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void onBackPressed() {
        finishAffinity();
    }

    private void initValues() {
        btnMemberDetails = findViewById(R.id.btnMemberDetails);
        btnTrainerDetails = findViewById(R.id.btnTrainerDetails);
        btnGymHallDetails = findViewById(R.id.btnGymHallDetails);
        btnAvailableTrainers = findViewById(R.id.btnAvailableTrainers);
        btnAbout = findViewById(R.id.btnAbout);
        btnEquipment = findViewById(R.id.btnEquipments);
        btnExercisePlan = findViewById(R.id.btnExercisePlan);
        btnFeedback = findViewById(R.id.btnFeedback);
    }

}