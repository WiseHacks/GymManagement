package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testingproject.DataHandler.DataHandlerGym;
import com.example.testingproject.models.Gym;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddGym;
    private ImageView imgGymIntro;
    private List<Gym> MyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();

        DataHandlerGym db = new DataHandlerGym(MainActivity.this);
        View someView = findViewById(R.id.mainViewid);
        View root = someView.getRootView();
        root.setBackground(getResources().getDrawable(R.drawable.gymbgc));

        MyList = db.getGym();
        if(MyList.isEmpty()){
            btnAddGym.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,AddGymActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(intent);
        }
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
    private void initValues() {
        btnAddGym = findViewById(R.id.btnAddGym);
        imgGymIntro = findViewById(R.id.imgGymIntro);
    }

}