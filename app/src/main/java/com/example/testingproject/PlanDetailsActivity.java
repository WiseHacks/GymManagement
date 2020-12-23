package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerExercise;
import com.example.testingproject.models.ExercisePlan;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class PlanDetailsActivity extends AppCompatActivity {

    private Button btnAddPlan;
    private RecyclerView PlanList,SearchedPlans;
    private ImageView imgSearchPlan;
    private EditText edtSearchNamePlan;
//    private static final int TIME_INTERVAL = 2000;
//    private long mBackPressed;
    private int spike=0;
    DataHandlerExercise db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        initValues();
        db = new DataHandlerExercise(PlanDetailsActivity.this);
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanDetailsActivity.this,PlanFormActivity.class);
                startActivity(intent);
            }
        });
        imgSearchPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ExercisePlan> kira = (ArrayList<ExercisePlan>) db.getPlans();
                String s = edtSearchNamePlan.getText().toString().toLowerCase();
//                s.toLowerCase();
                String s2="";
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i) != ' ')s2 = s2 + s.charAt(i);
                }
                ArrayList<ExercisePlan> temp = new ArrayList<>();
                for (ExercisePlan e:
                        kira) {
                    String A1 = e.getName().toLowerCase();
//                    A1.toLowerCase();
                    String A2="";
                    for(int i=0;i<A1.length();i++){
                        if(A1.charAt(i) != ' ')A2 = A2 + A1.charAt(i);
                    }
                    if(A2.equals(s2))temp.add(e);
                }
//                ArrayList<ExercisePlan> temp = (ArrayList<ExercisePlan>) db.searchPlan(edtSearchNamePlan.getText().toString());
                if(temp.size()==0){
                    Toast.makeText(PlanDetailsActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PlanDetailsActivity.this,"Exercise plan Found", Toast.LENGTH_SHORT).show();
                    PlanList.setVisibility(View.GONE);
                    SearchedPlans.setVisibility(View.VISIBLE);
                    spike=1;
                    CustomPlan adapter = new CustomPlan(PlanDetailsActivity.this,PlanDetailsActivity.this,temp,db);

                    SearchedPlans.setAdapter(adapter);
                    SearchedPlans.setLayoutManager(new LinearLayoutManager(PlanDetailsActivity.this));
                }
            }
        });
        List<ExercisePlan> exercisePlans;
        exercisePlans = db.getPlans();
        CustomPlan adapter = new CustomPlan(PlanDetailsActivity.this,PlanDetailsActivity.this,exercisePlans,db);
        PlanList.setAdapter(adapter);
        PlanList.setLayoutManager(new LinearLayoutManager(PlanDetailsActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    private void initValues() {
        btnAddPlan = findViewById(R.id.btnAddPlan);
        PlanList = findViewById(R.id.PlanList);
        imgSearchPlan = findViewById(R.id.imgSearchPlan);
        edtSearchNamePlan = findViewById(R.id.edtSearchNamePlan);
        SearchedPlans = findViewById(R.id.SearchedPlans);
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
        if(spike==1){
            PlanList.setVisibility(View.VISIBLE);
            SearchedPlans.setVisibility(View.GONE);
            edtSearchNamePlan.setText("");
            spike=0;
        }
        else {
//            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                finish();
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
//            } else {
//                Toast.makeText(getBaseContext(), "Click Again to close the activity", Toast.LENGTH_SHORT).show();
//            }
        }
//        mBackPressed = System.currentTimeMillis();
    }
}