package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerGym;
import com.example.testingproject.models.Gym;

public class AddGymActivity extends AppCompatActivity {
    private EditText edtTxtGymName,edtTxtGymAddress;
    private Button btnAdded;
    private TextView txtCaution;
    DataHandlerGym db = new DataHandlerGym(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gym);
        initValues();
        btnAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTxtGymName.getText().toString().equals("") || edtTxtGymAddress.getText().toString().equals("")){
                    txtCaution.setVisibility(View.VISIBLE);
                    Toast.makeText(AddGymActivity.this, "Enter All Required Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    txtCaution.setVisibility(View.GONE);
                    Gym gym = new Gym(edtTxtGymName.getText().toString(),edtTxtGymAddress.getText().toString());
                    db.addGym(gym);
                    Toast.makeText(AddGymActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddGymActivity.this,StartActivity.class);
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
        edtTxtGymAddress = findViewById(R.id.edtTxtGymAddress);
        edtTxtGymName = findViewById(R.id.edtTxtGymName);
        btnAdded = findViewById(R.id.btnAdded);
        txtCaution = findViewById(R.id.txtCaution);
    }
}