package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerEquipment;
import com.example.testingproject.models.Equipment;

public class EquipmentUpdateActivity extends AppCompatActivity {
    private EditText edtEquipUnitUpdated,edtLocationEquipUpdated;
    private Button btnEquipUpdated;
    private int id;
    private String name,unit,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_update);
        edtEquipUnitUpdated = findViewById(R.id.edtEquipUnitUpdated);
        edtLocationEquipUpdated = findViewById(R.id.edtLocationEquipUpdated);
        btnEquipUpdated = findViewById(R.id.btnEquipUpdated);
        getAndSetIntentData();
        DataHandlerEquipment db = new DataHandlerEquipment(EquipmentUpdateActivity.this);
        btnEquipUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EquipmentUpdateActivity.this);
                builder.setMessage("Update Details?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Equipment equipment = new Equipment();
                        equipment.setId(id);
                        equipment.setName(name);
                        equipment.setUnits(edtEquipUnitUpdated.getText().toString());
                        equipment.setLocation(edtLocationEquipUpdated.getText().toString());
                        if(edtEquipUnitUpdated.getText().toString().equals("") || edtLocationEquipUpdated.getText().toString().equals("")){
                            Toast.makeText(EquipmentUpdateActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.updateEquipment(equipment);
//                            Toast.makeText(EquipmentUpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EquipmentUpdateActivity.this, EquipmentDetailsActivity.class);
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
        if(getIntent().hasExtra("name") && getIntent().hasExtra("unit") && getIntent().hasExtra("location")){
            //getting data
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            unit = getIntent().getStringExtra("unit");
            location = getIntent().getStringExtra("location");
            name = getIntent().getStringExtra("name");
            edtEquipUnitUpdated.setText(unit);
            edtLocationEquipUpdated.setText(location);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}