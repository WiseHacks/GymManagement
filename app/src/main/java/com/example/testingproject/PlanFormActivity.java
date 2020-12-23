package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerExercise;
import com.example.testingproject.models.ExercisePlan;

public class PlanFormActivity extends AppCompatActivity {
    private EditText edtPlanName,edtPlanEquip,edtPlanDesc,edtPlanFrom;
    private Button btnPlanAdded,btnplanAddedAgain;
    DataHandlerExercise db = new DataHandlerExercise(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_form);
        edtPlanName = findViewById(R.id.edtPlanName);
        edtPlanEquip = findViewById(R.id.edtPlanEquip);
        edtPlanDesc = findViewById(R.id.edtPlanDesc);
        edtPlanFrom = findViewById(R.id.edtPlanFrom);
        btnPlanAdded = findViewById(R.id.btnplanAdded);
        btnplanAddedAgain = findViewById(R.id.btnplanAddedAgain);
        btnPlanAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPlanName.getText().toString().equals("") || edtPlanEquip.getText().toString().equals("") || edtPlanDesc.getText().toString().equals("")
                || edtPlanFrom.getText().toString().equals("")){
                    Toast.makeText(PlanFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else{

                    ExercisePlan exercisePlan = new ExercisePlan();
                    exercisePlan.setName(edtPlanName.getText().toString());
                    exercisePlan.setReqEquip(edtPlanEquip.getText().toString());
                    exercisePlan.setDescription(edtPlanDesc.getText().toString());
                    exercisePlan.setSubmitedBy(edtPlanFrom.getText().toString());

                    db.addPlan(exercisePlan);
                    Toast.makeText(PlanFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlanFormActivity.this, PlanDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnplanAddedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPlanName.getText().toString().equals("") || edtPlanEquip.getText().toString().equals("") || edtPlanDesc.getText().toString().equals("")){
                    Toast.makeText(PlanFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else{

                    ExercisePlan exercisePlan = new ExercisePlan();
                    exercisePlan.setName(edtPlanName.getText().toString());
                    exercisePlan.setReqEquip(edtPlanEquip.getText().toString());
                    exercisePlan.setDescription(edtPlanDesc.getText().toString());
                    exercisePlan.setSubmitedBy(edtPlanFrom.getText().toString());

                    db.addPlan(exercisePlan);
                    Toast.makeText(PlanFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlanFormActivity.this, PlanFormActivity.class);
                    startActivity(intent);
                }
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
}