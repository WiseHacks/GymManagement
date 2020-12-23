package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerEquipment;
import com.example.testingproject.models.Equipment;

public class EquipmentFormActivity extends AppCompatActivity {
    private EditText edtNameEquipment,edtUnitEquipment,edtLocationEquipment;
    private Button btnEquipAdded,btnQuipAddedAgain;
    DataHandlerEquipment db = new DataHandlerEquipment(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_form);
        edtNameEquipment = findViewById(R.id.edtNameEquipment);
        edtUnitEquipment = findViewById(R.id.edtUnitEquipment);
        edtLocationEquipment = findViewById(R.id.edtLocationEquipment);
        btnEquipAdded = findViewById(R.id.btnEquipAdded);
        btnQuipAddedAgain = findViewById(R.id.btnQuipAddedAgain);
        btnEquipAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNameEquipment.getText().toString().equals("") ||
                        edtUnitEquipment.getText().toString().equals("") ||
                                edtLocationEquipment.getText().toString().equals("")) {
                    Toast.makeText(EquipmentFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Equipment equipment = new Equipment();
                    equipment.setName(edtNameEquipment.getText().toString());
                    equipment.setUnits( edtUnitEquipment.getText().toString());
                    equipment.setLocation(edtLocationEquipment.getText().toString());
                    db.addEquipment(equipment);
                    Toast.makeText(EquipmentFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EquipmentFormActivity.this, EquipmentDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnQuipAddedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNameEquipment.getText().toString().equals("") ||
                        edtUnitEquipment.getText().toString().equals("") ||
                        edtLocationEquipment.getText().toString().equals("")) {
                    Toast.makeText(EquipmentFormActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Equipment equipment = new Equipment();
                    equipment.setName(edtNameEquipment.getText().toString());
                    equipment.setUnits( edtUnitEquipment.getText().toString());
                    equipment.setLocation(edtLocationEquipment.getText().toString());
                    db.addEquipment(equipment);
                    Toast.makeText(EquipmentFormActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EquipmentFormActivity.this, EquipmentFormActivity.class);
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