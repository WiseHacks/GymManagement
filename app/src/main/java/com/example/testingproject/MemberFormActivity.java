package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerCustomer;
import com.example.testingproject.DataHandler.DataHandlerExercise;
import com.example.testingproject.models.Customer;
import com.example.testingproject.models.ExercisePlan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MemberFormActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private EditText edtEnterName,edtEnterAge,edtEnterEmail,edtEnterPhone;
    private TextView txtMemberCD;
    private Button btnMemberAdded,btnMemberAddedAgain;
    private RadioGroup radioGroup;
    private RadioButton btnMale,btnFemale,btnOther;
    private Spinner spinner;
    private String chosenOne = "Select";
    DataHandlerCustomer db = new  DataHandlerCustomer(this);
    DataHandlerExercise dbPlan = new DataHandlerExercise(this);
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_form);
        initValues();

        ArrayList<ExercisePlan> plans = (ArrayList<ExercisePlan>) dbPlan.getPlans();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.add("Select");
        spinnerAdapter.add("NA");
        spinner.setAdapter(spinnerAdapter);
        ArrayList<String> planName=new ArrayList<>();
        for (ExercisePlan ep:
             plans) {
            planName.add(ep.getName());
        }
        Collections.sort(planName);
        for (String s:
             planName) {
            spinnerAdapter.add(s);
        }
        spinnerAdapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(this);
        btnMemberAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtEnterName.getText().toString().equals("") || edtEnterAge.getText().toString().equals("")
                        || edtEnterEmail.getText().toString().equals("")
                        || edtEnterPhone.getText().toString().equals("")
                        ){
                    Toast.makeText(MemberFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(edtEnterAge.getText().toString())>70 || Integer.parseInt(edtEnterAge.getText().toString())<10){
                    Toast.makeText(MemberFormActivity.this, "Whoa!!Check the Age", Toast.LENGTH_SHORT).show();
                }
                else if (edtEnterPhone.getText().toString().length()!=10){
                    Toast.makeText(MemberFormActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                }

                else if(chosenOne.equals("Select")){
                    Toast.makeText(MemberFormActivity.this, "Please select Exercise Plan", Toast.LENGTH_SHORT).show();
                }
            else {
                Customer customer;
                customer = new Customer();
                customer.setName(edtEnterName.getText().toString());
                customer.setAge(edtEnterAge.getText().toString());
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.btnMale:
                        customer.setGender("Male");
                        break;
                    case R.id.btnFemale:
                        customer.setGender("Female");
                        break;
                    case R.id.btnOther:
                        customer.setGender("Other");
                        break;
                    default:break;

                }

//                customer.setGender(edtEnterGender.getText().toString());
                customer.setEmail(edtEnterEmail.getText().toString());
                customer.setPhone(edtEnterPhone.getText().toString());
                customer.setSubs(txtMemberCD.getText().toString());
                customer.setChosen(chosenOne);
                db.addMember(customer);
                Toast.makeText(MemberFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MemberFormActivity.this, MemberDetailsActivity.class);
                startActivity(intent);
            }
        }
         });
        btnMemberAddedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtEnterName.getText().toString().equals("") || edtEnterAge.getText().toString().equals("")
                        || edtEnterEmail.getText().toString().equals("")
                        || edtEnterPhone.getText().toString().equals("")
                ){
                    Toast.makeText(MemberFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(edtEnterAge.getText().toString())>70 || Integer.parseInt(edtEnterAge.getText().toString())<10){
                    Toast.makeText(MemberFormActivity.this, "Whoa!!Check the Age", Toast.LENGTH_SHORT).show();
                }
                else if (edtEnterPhone.getText().toString().length()!=10){
                    Toast.makeText(MemberFormActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                }

                else if(chosenOne.equals("Select")){
                    Toast.makeText(MemberFormActivity.this, "Please select Exercise Plan", Toast.LENGTH_SHORT).show();
                }
            else {
                Customer customer;
                customer = new Customer();
                customer.setName(edtEnterName.getText().toString());
                customer.setAge(edtEnterAge.getText().toString());
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.btnMale:
                        customer.setGender("Male");
                        break;
                    case R.id.btnFemale:
                        customer.setGender("Female");
                        break;
                    case R.id.btnOther:
                        customer.setGender("Other");
                        break;
                    default:break;

                }

//                customer.setGender(edtEnterGender.getText().toString());
                customer.setEmail(edtEnterEmail.getText().toString());
                customer.setPhone(edtEnterPhone.getText().toString());
                customer.setSubs(txtMemberCD.getText().toString());
                customer.setChosen(chosenOne);
                db.addMember(customer);
                Toast.makeText(MemberFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MemberFormActivity.this, MemberFormActivity.class);
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
        edtEnterName = findViewById(R.id.edtEnterName);
        edtEnterAge = findViewById(R.id.edtEnterAge);
        edtEnterEmail = findViewById(R.id.edtEnterEmail);
        edtEnterPhone = findViewById(R.id.edtEnterPhone);
        txtMemberCD = findViewById(R.id.txtMemberCD);
        btnMemberAdded = findViewById(R.id.btnMemberAdded);
        btnMemberAddedAgain = findViewById(R.id.btnMemberAddedAgain);
        radioGroup = findViewById(R.id.radioGroup);
        btnMale = findViewById(R.id.btnMale);
        btnFemale = findViewById(R.id.btnFemale);
        btnOther = findViewById(R.id.btnOther);
        spinner = findViewById(R.id.spinner);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String CD = sdf.format(new Date());
        txtMemberCD.setText(CD);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chosenOne = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}