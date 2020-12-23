package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerExercise;
import com.example.testingproject.models.ExercisePlan;

public class PlanUpdateActivity extends AppCompatActivity {
    private EditText edtUpdatePlanEquip,edtUpdatePlanDesc;
    private Button btnUpdatePlan;

    private String equip,desc,name,from;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_update);
        initValues();
        getAndSetIntentData();
        DataHandlerExercise db = new DataHandlerExercise(PlanUpdateActivity.this);
        btnUpdatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlanUpdateActivity.this);
                builder.setMessage("Update Details?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExercisePlan exercisePlan= new ExercisePlan();
                        exercisePlan.setId(id);
                        exercisePlan.setName(name);
                        exercisePlan.setReqEquip(edtUpdatePlanEquip.getText().toString());
                        exercisePlan.setDescription(edtUpdatePlanDesc.getText().toString());
                        exercisePlan.setSubmitedBy(from);
                        if(edtUpdatePlanEquip.getText().toString().equals("") || edtUpdatePlanDesc.getText().toString().equals("")){
                            Toast.makeText(PlanUpdateActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.updatePlan(exercisePlan);
//                            Toast.makeText(PlanUpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PlanUpdateActivity.this, PlanDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initValues() {
        edtUpdatePlanEquip = findViewById(R.id.edtUpdatePlanEquip);
        edtUpdatePlanDesc = findViewById(R.id.edtUpdatePlanDesc);
        btnUpdatePlan = findViewById(R.id.btnUpdatePlan);
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
    public void getAndSetIntentData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("equip") && getIntent().hasExtra("desc") && getIntent().hasExtra("id")){
            //getting data
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            equip = getIntent().getStringExtra("equip");
            desc = getIntent().getStringExtra("desc");
            name = getIntent().getStringExtra("name");
            from = getIntent().getStringExtra("from");
            edtUpdatePlanEquip.setText(equip);
            edtUpdatePlanDesc.setText(desc);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}