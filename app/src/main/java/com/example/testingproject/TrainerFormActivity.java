package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.Customer;
import com.example.testingproject.models.Trainer;

public class TrainerFormActivity extends AppCompatActivity {
    private EditText edtEnterTrainerName,edtEnterTrainerEmail,edtEnterTrainerPhone,edtAssignedHr;
    private Button btnTrainerAdded,btnTrainerAddedAgain;
    private RadioGroup radioGroupTime;
    private RadioButton btnAM,btnPM;
    private  String  check;
    DataHandlerTrainer db = new DataHandlerTrainer(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_form);
        initValues();

        btnTrainerAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEnterTrainerName.getText().toString().equals("") || edtEnterTrainerEmail.getText().toString().equals("")
                        || edtEnterTrainerPhone.getText().toString().equals("") || edtAssignedHr.getText().toString().equals("")
                        || Integer.parseInt(edtAssignedHr.getText().toString())>12 || Integer.parseInt(edtAssignedHr.getText().toString())<1){
                    Toast.makeText(TrainerFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else if(edtEnterTrainerPhone.getText().toString().length()!=10){
                    Toast.makeText(TrainerFormActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                }
                else {
                    Trainer trainer;
                    trainer = new Trainer();
                    trainer.setName(edtEnterTrainerName.getText().toString());
                    trainer.setEmail(edtEnterTrainerEmail.getText().toString());
                    trainer.setPhone(edtEnterTrainerPhone.getText().toString());
                    switch (radioGroupTime.getCheckedRadioButtonId()){
                        case R.id.btnAM:
                            check = "AM";
                            break;
                        case R.id.btnPM:
                            check = "PM";
                            break;
                        default:break;
                    }
                    if(check.equals("AM")){
                        if(edtAssignedHr.getText().toString().equals("12")){
                            trainer.setAssigned("00");

                        }
                        else {
                            trainer.setAssigned(String.valueOf(Integer.parseInt(edtAssignedHr.getText().toString())));
                        }

                    }
                    else{
                        if(edtAssignedHr.getText().toString().equals("12")){
                            trainer.setAssigned("12");
                        }
                        else {
                            trainer.setAssigned(String.valueOf((Integer.parseInt(edtAssignedHr.getText().toString()) + 12)));
                        }
                    }
                    db.addTrainer(trainer);
                    Toast.makeText(TrainerFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TrainerFormActivity.this, TrainerDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnTrainerAddedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEnterTrainerName.getText().toString().equals("") || edtEnterTrainerEmail.getText().toString().equals("")
                        || edtEnterTrainerPhone.getText().toString().equals("") || edtAssignedHr.getText().toString().equals("")
                        || Integer.parseInt(edtAssignedHr.getText().toString())>12 || Integer.parseInt(edtAssignedHr.getText().toString())<1){
                    Toast.makeText(TrainerFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else if(edtEnterTrainerPhone.getText().toString().length()!=10){
                    Toast.makeText(TrainerFormActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                }
                else {
                    Trainer trainer;
                    trainer = new Trainer();
                    trainer.setName(edtEnterTrainerName.getText().toString());
                    trainer.setEmail(edtEnterTrainerEmail.getText().toString());
                    trainer.setPhone(edtEnterTrainerPhone.getText().toString());
                    switch (radioGroupTime.getCheckedRadioButtonId()){
                        case R.id.btnAM:
                            check = "AM";
                            break;
                        case R.id.btnPM:
                            check = "PM";
                            break;
                        default:break;
                    }
                    if(check.equals("AM")){
                        if(edtAssignedHr.getText().toString().equals("12")){
                            trainer.setAssigned("00");

                        }
                        else {
                            trainer.setAssigned(String.valueOf(Integer.parseInt(edtAssignedHr.getText().toString())));
                        }

                    }
                    else{
                        if(edtAssignedHr.getText().toString().equals("12")){
                            trainer.setAssigned("12");
                        }
                        else {
                            trainer.setAssigned(String.valueOf((Integer.parseInt(edtAssignedHr.getText().toString()) + 12)));
                        }
                    }
                    db.addTrainer(trainer);
                    Toast.makeText(TrainerFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TrainerFormActivity.this, TrainerFormActivity.class);
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
    private void initValues() {
        edtEnterTrainerName = findViewById(R.id.edtEnterTrainerName);
        edtEnterTrainerEmail = findViewById(R.id.edtEnterTrainerEmail);
        edtEnterTrainerPhone = findViewById(R.id.edtEnterTrainerPhone);
        edtAssignedHr = findViewById(R.id.edtAssignedHr);
        radioGroupTime = findViewById(R.id.radioGroupTime);
        btnAM = findViewById(R.id.btnAM);
        btnPM= findViewById(R.id.btnPM);
        btnTrainerAdded = findViewById(R.id.btnTrainerAdded);
        btnTrainerAddedAgain = findViewById(R.id.btnTrainerAddedAgain);
    }
}