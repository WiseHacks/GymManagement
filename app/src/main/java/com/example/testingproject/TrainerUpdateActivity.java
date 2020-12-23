package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class TrainerUpdateActivity extends AppCompatActivity {
    private EditText edtEnterEmailTrainer2,edtEnterPhoneTrainer2,edtStartinghour;
    private Button btnTrainerUpdated2;
    private RadioGroup radioGroupTimeup;
    private RadioButton btnAMup,btnPMup;
    private String email,phone,name,Assign,check;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_update);
        initValues();
        getAndSetIntentData();
        DataHandlerTrainer db = new DataHandlerTrainer(TrainerUpdateActivity.this);
        btnTrainerUpdated2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TrainerUpdateActivity.this);
                builder.setMessage("Update Details?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Trainer trainer = new Trainer();
                        trainer.setId(id);
                        trainer.setName(name);
//                        trainer.setAssigned(Assign);
                        trainer.setEmail(edtEnterEmailTrainer2.getText().toString());
                        trainer.setPhone(edtEnterPhoneTrainer2.getText().toString());
                        switch (radioGroupTimeup.getCheckedRadioButtonId()){
                            case R.id.btnAMup:
                                check = "AM";
                                break;
                            case R.id.btnPMup:
                                check = "PM";
                                break;
                            default:break;
                        }
                        if(edtStartinghour.getText().toString().equals("")
                            || Integer.parseInt(edtStartinghour.getText().toString())>12 || Integer.parseInt(edtStartinghour.getText().toString())<1){
                            Toast.makeText(TrainerUpdateActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            if (check.equals("AM")) {
                                if (edtStartinghour.getText().toString().equals("12")) {
                                    trainer.setAssigned("00");
                                } else {
                                    trainer.setAssigned(String.valueOf(Integer.parseInt(edtStartinghour.getText().toString())));
                                }

                            } else {
                                if (edtStartinghour.getText().toString().equals("12")) {
                                    trainer.setAssigned("12");
                                } else {
                                    trainer.setAssigned(String.valueOf((Integer.parseInt(edtStartinghour.getText().toString()) + 12)));

                                }

                            }
                            if(edtEnterEmailTrainer2.getText().toString().equals("")
                                    || edtEnterPhoneTrainer2.getText().toString().equals("")
                                    ){
                                Toast.makeText(TrainerUpdateActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();

                            }
                            else if(edtEnterPhoneTrainer2.getText().toString().length()!=10){
                                Toast.makeText(TrainerUpdateActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                db.updateTrainer(trainer);
                                //Toast.makeText(TrainerUpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TrainerUpdateActivity.this, TrainerDetailsActivity.class);
                                startActivity(intent);
                            }
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

        edtEnterEmailTrainer2 = findViewById(R.id.edtEnterEmailTrainer2);
        edtEnterPhoneTrainer2 = findViewById(R.id.edtenterPhoneTrainer2);
        edtStartinghour = findViewById(R.id.edtStartinghour);
        btnTrainerUpdated2 = findViewById(R.id.btnTrainerUpdated2);
        radioGroupTimeup = findViewById(R.id.radioGroupTimeup);
        btnAMup = findViewById(R.id.btnAMup);
        btnPMup = findViewById(R.id.btnPMup);
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
        if(getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("phone")/* && getIntent().hasExtra("assign")*/){
            //getting data
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            email = getIntent().getStringExtra("email");
            phone = getIntent().getStringExtra("phone");
            name = getIntent().getStringExtra("name");
            Assign = getIntent().getStringExtra("Assign");
            //setting
            edtEnterEmailTrainer2.setText(email);
            edtEnterPhoneTrainer2.setText(phone);
            Log.d("Hr",Assign);
            if(Integer.parseInt(Assign)<12){
                edtStartinghour.setText(Assign);
                btnAMup.setChecked(true);
                btnPMup.setChecked(false);

            }
            else if(Integer.parseInt(Assign)==12){
                edtStartinghour.setText(Assign);
                btnPMup.setChecked(true);
                btnAMup.setChecked(false);
            }
            else {
                edtStartinghour.setText(String.valueOf(Integer.parseInt(Assign) - 12));
                btnPMup.setChecked(true);
                btnAMup.setChecked(false);
            }
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}