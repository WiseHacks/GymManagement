package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.Customer;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class TrainerDetailsActivity extends AppCompatActivity {

    private Button btnAddTrainer;
    private RecyclerView TrainersList,SearchedTrainers;
    private ImageView imgSearchTrainer;
    private EditText edtSearchNameTrainer;
//    private static final int TIME_INTERVAL = 2000;
//    private long mBackPressed;
    private int mob=0;
    DataHandlerTrainer db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_details);
        initValues();
        db = new DataHandlerTrainer(TrainerDetailsActivity.this);
        btnAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerDetailsActivity.this,TrainerFormActivity.class);
                startActivity(intent);
            }
        });
        imgSearchTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Trainer> kira = db.getTrainers();
                String s = edtSearchNameTrainer.getText().toString().toLowerCase();
                String s2="";
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i) != ' ')s2 = s2 + s.charAt(i);
                }
                List<Trainer> temp = new ArrayList<>();
                for (Trainer t:
                     kira) {
                        String A1 = t.getName().toLowerCase();
                        String A2="";
                    for(int i=0;i<A1.length();i++){
                        if(A1.charAt(i) != ' ')A2 = A2 + A1.charAt(i);
                    }
                    if(A2.equals(s2))temp.add(t);
                }
//                ArrayList<Trainer> temp = (ArrayList<Trainer>) db.searchTrainer(edtSearchNameTrainer.getText().toString());
                if(temp.size()==0){
                    Toast.makeText(TrainerDetailsActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TrainerDetailsActivity.this,"Trainer Found", Toast.LENGTH_SHORT).show();
                    TrainersList.setVisibility(View.GONE);
                    SearchedTrainers.setVisibility(View.VISIBLE);
                    CustomAdapterTrainer adapterTrainer = new CustomAdapterTrainer(TrainerDetailsActivity.this,TrainerDetailsActivity.this,temp,db);
                    mob=1;
                    SearchedTrainers.setAdapter(adapterTrainer);
                    SearchedTrainers.setLayoutManager(new LinearLayoutManager(TrainerDetailsActivity.this));
                }
            }
        });
        List<Trainer> Trainers;
        Trainers = db.getTrainers();
        CustomAdapterTrainer adapterTrainer = new CustomAdapterTrainer(TrainerDetailsActivity.this,TrainerDetailsActivity.this,Trainers,db);
        TrainersList.setAdapter(adapterTrainer);
        TrainersList.setLayoutManager(new LinearLayoutManager(TrainerDetailsActivity.this));
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
        btnAddTrainer = findViewById(R.id.btnAddTrainer);
        TrainersList = findViewById(R.id.TrainerList);
        imgSearchTrainer = findViewById(R.id.imgSearchTrainer);
        edtSearchNameTrainer = findViewById(R.id.edtSearchNameTrainer);
        SearchedTrainers = findViewById(R.id.SearchedTrainers);
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
        if(mob==1){
            TrainersList.setVisibility(View.VISIBLE);
            SearchedTrainers.setVisibility(View.GONE);
            edtSearchNameTrainer.setText("");
            mob=0;
        }
        else {
//            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                finish();
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
//            } else {
//
//                Toast.makeText(getBaseContext(), "Click Again to close the activity", Toast.LENGTH_SHORT).show();
//            }
        }
//        mBackPressed = System.currentTimeMillis();
    }
}