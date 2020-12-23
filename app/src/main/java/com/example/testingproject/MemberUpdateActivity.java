package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MemberUpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText edtEnterAge2,edtEnterEmail2,edtEnterPhone2;
    private TextView txtMemberupdateCD,txtChossenPlanup,txtview18;
    private Button btnMemberUpdated2,btnSetToday,btnChangePlan;
    private String age,email,phone,subs,name,gender,chosenplan;
    private Spinner spinnerup;
    private int id;
    private String choosenep = "Initial";
    DataHandlerExercise dbPlan = new DataHandlerExercise(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);
        initValues();
        getAndSetIntentData();
        DataHandlerCustomer db = new DataHandlerCustomer(MemberUpdateActivity.this);
        ArrayList<ExercisePlan> plans = (ArrayList<ExercisePlan>) dbPlan.getPlans();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.add("Select");
        spinnerAdapter.add("NA");
        spinnerup.setAdapter(spinnerAdapter);
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

        btnSetToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String CD = sdf.format(new Date());
                txtMemberupdateCD.setText(CD);
            }
        });
        btnChangePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MemberUpdateActivity.this);
                builder.setMessage("Change Plan?");
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        txtview18.setVisibility(View.VISIBLE);
                        spinnerup.setVisibility(View.VISIBLE);
                        spinnerup.setOnItemSelectedListener(MemberUpdateActivity.this);

//                        btnChangePlan.setVisibility(View.GONE);
                        btnChangePlan.setEnabled(false);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });builder.create().show();
            }
        });
        btnMemberUpdated2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MemberUpdateActivity.this);
                builder.setMessage("Update Details?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Customer customer = new Customer();
                        customer.setId(id);
                        customer.setName(name);
                        customer.setAge(edtEnterAge2.getText().toString());
                        customer.setGender(gender);
                        customer.setEmail(edtEnterEmail2.getText().toString());
                        customer.setPhone(edtEnterPhone2.getText().toString());
                        customer.setSubs(txtMemberupdateCD.getText().toString());
                        customer.setChosen(choosenep);
                        if(edtEnterAge2.getText().toString().equals("") ||
                                edtEnterEmail2.getText().toString().equals("")||
                                edtEnterPhone2.getText().toString().equals("")){
                            Toast.makeText(MemberUpdateActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.parseInt(edtEnterAge2.getText().toString())>70 || Integer.parseInt(edtEnterAge2.getText().toString())<10){
                            Toast.makeText(MemberUpdateActivity.this, "Whoa!!Check the Age", Toast.LENGTH_SHORT).show();
                        }
                        else if (edtEnterPhone2.getText().toString().length()!=10){
                            Toast.makeText(MemberUpdateActivity.this, "Wrong Contact no.", Toast.LENGTH_SHORT).show();

                        }
                        else if(choosenep.equals("Select")){
                            Toast.makeText(MemberUpdateActivity.this, "Please select Exercise Plan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.updateCustomer(customer);
                            Intent intent = new Intent(MemberUpdateActivity.this, MemberDetailsActivity.class);
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
        edtEnterAge2 = findViewById(R.id.edtEnterAge2);
        edtEnterEmail2 = findViewById(R.id.edtEnterEmail2);
        edtEnterPhone2 = findViewById(R.id.edtEnterPhone2);
        txtMemberupdateCD = findViewById(R.id.txtMemberupdateCD);
        txtview18 = findViewById(R.id.textView18);
        txtChossenPlanup = findViewById(R.id.txtChoosenPlanup);
        btnMemberUpdated2 = findViewById(R.id.btnMemberUpdated2);
        btnSetToday = findViewById(R.id.btnSetToday);
        btnChangePlan = findViewById(R.id.btnChangePlan);
        spinnerup = findViewById(R.id.spinnerup);
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
        if(getIntent().hasExtra("name") && getIntent().hasExtra("age") && getIntent().hasExtra("gender")
                && getIntent().hasExtra("email") && getIntent().hasExtra("phone") && getIntent().hasExtra("subs")){
                //getting data
                id = Integer.parseInt(getIntent().getStringExtra("id"));
                age = getIntent().getStringExtra("age");
                email = getIntent().getStringExtra("email");
                phone = getIntent().getStringExtra("phone");
                subs = getIntent().getStringExtra("subs");
                name = getIntent().getStringExtra("name");
                gender = getIntent().getStringExtra("gender");
                chosenplan = getIntent().getStringExtra("chosenplan");
                //setting
                edtEnterAge2.setText(age);
                edtEnterEmail2.setText(email);
                edtEnterPhone2.setText(phone);
                txtMemberupdateCD.setText(subs);
                txtChossenPlanup.setText(chosenplan);
                choosenep = txtChossenPlanup.getText().toString();
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choosenep = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}