package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerGymHall;
import com.example.testingproject.models.GymHall;

public class HallFormActivity extends AppCompatActivity {
    private EditText edtHallName,edtHallHr;
    private Button btnHallAdded,btnAMh,btnPMh,btnHallAddedAgain;
    private String check;
    private RadioGroup radioGroupHall;
    DataHandlerGymHall db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_form);
        edtHallHr = findViewById(R.id.edtHallHr);
        edtHallName = findViewById(R.id.edtHallName);
        btnHallAdded = findViewById(R.id.btnHallAdded);
        btnHallAddedAgain = findViewById(R.id.btnHallAddedAgain);
        btnAMh= findViewById(R.id.btnAMh);
        btnPMh = findViewById(R.id.btnPMh);
        radioGroupHall = findViewById(R.id.radioGroupHall);

        db = new DataHandlerGymHall(this);

        btnHallAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtHallName.getText().toString().equals("") || edtHallHr.getText().toString().equals("")
                    || Integer.parseInt(edtHallHr.getText().toString())>12 || Integer.parseInt(edtHallHr.getText().toString())<1){
                    Toast.makeText(HallFormActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                }
                else{
                    GymHall gymHall = new GymHall();
                    gymHall.setName(edtHallName.getText().toString());
                    switch (radioGroupHall.getCheckedRadioButtonId()){
                        case R.id.btnAMh:
                            check = "AM";
                            break;
                        case R.id.btnPMh:
                            check = "PM";
                            break;
                        default:break;
                    }
                    if(check.equals("AM")){
                        if(edtHallHr.getText().toString().equals("12")){
                            gymHall.setHrs("00");

                        }
                        else {
                            gymHall.setHrs(String.valueOf(Integer.parseInt(edtHallHr.getText().toString())));
                        }
                    }
                    else{
                        if(edtHallHr.getText().toString().equals("12")){
                            gymHall.setHrs("12");

                        }
                        else {
                            gymHall.setHrs(String.valueOf((Integer.parseInt(edtHallHr.getText().toString()) + 12)));

                        }
                    }
                    db.addHall(gymHall);
                    Toast.makeText(HallFormActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HallFormActivity.this,HallDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnHallAddedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtHallName.getText().toString().equals("") || edtHallHr.getText().toString().equals("")
                        || Integer.parseInt(edtHallHr.getText().toString())>12 || Integer.parseInt(edtHallHr.getText().toString())<1){
                    Toast.makeText(HallFormActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                }
                else{
                    GymHall gymHall = new GymHall();
                    gymHall.setName(edtHallName.getText().toString());
                    switch (radioGroupHall.getCheckedRadioButtonId()){
                        case R.id.btnAMh:
                            check = "AM";
                            break;
                        case R.id.btnPMh:
                            check = "PM";
                            break;
                        default:break;
                    }
                    if(check.equals("AM")){
                        if(edtHallHr.getText().toString().equals("12")){
                            gymHall.setHrs("00");

                        }
                        else {
                            gymHall.setHrs(String.valueOf(Integer.parseInt(edtHallHr.getText().toString())));
                        }
                    }
                    else{
                        if(edtHallHr.getText().toString().equals("12")){
                            gymHall.setHrs("12");

                        }
                        else {
                            gymHall.setHrs(String.valueOf((Integer.parseInt(edtHallHr.getText().toString()) + 12)));

                        }
                    }
                    db.addHall(gymHall);
                    Toast.makeText(HallFormActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HallFormActivity.this,HallFormActivity.class);
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