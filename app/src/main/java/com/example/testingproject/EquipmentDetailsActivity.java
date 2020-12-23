package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerEquipment;
import com.example.testingproject.models.Equipment;
import com.example.testingproject.models.Trainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EquipmentDetailsActivity extends AppCompatActivity {
    private Button btnAddEquipment;
    private RecyclerView EquipmentList,SearchedEquipments;
    private ImageView imgSearchEquipment;
    private EditText edtSearchNameEquipment;
//    private static final int TIME_INTERVAL = 2000;
//    private long mBackPressed;
    private int mugen = 0;
    DataHandlerEquipment db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);initValues();
        db = new DataHandlerEquipment(EquipmentDetailsActivity.this);
        btnAddEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquipmentDetailsActivity.this,EquipmentFormActivity.class);
                startActivity(intent);
            }
        });
        imgSearchEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Equipment> kira = (ArrayList<Equipment>) db.getEquipments();
                String s = edtSearchNameEquipment.getText().toString().toLowerCase();
                String s2="";
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i) != ' ')s2 = s2 + s.charAt(i);
                }
                ArrayList<Equipment> temp = new ArrayList<>();
                for (Equipment eq:
                        kira) {
                    String A1 = eq.getName().toLowerCase();
//                    A1.toLowerCase();
                    String A2="";
                    for(int i=0;i<A1.length();i++){
                        if(A1.charAt(i) != ' ')A2 = A2 + A1.charAt(i);
                    }
                    if(A2.equals(s2))temp.add(eq);
                }
//                ArrayList<Equipment> temp = (ArrayList<Equipment>) db.searchTrainer(edtSearchNameEquipment.getText().toString());
                if(temp.size()==0){
                    mugen = 0;
                    Toast.makeText(EquipmentDetailsActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                else {
                    mugen = 1;
                    Toast.makeText(EquipmentDetailsActivity.this,"Equipment Found", Toast.LENGTH_SHORT).show();
                    EquipmentList.setVisibility(View.GONE);
                    SearchedEquipments.setVisibility(View.VISIBLE);
                    CustomEquip adapter = new CustomEquip(EquipmentDetailsActivity.this,EquipmentDetailsActivity.this,temp,db);
                    SearchedEquipments.setAdapter(adapter);
                    SearchedEquipments.setLayoutManager(new LinearLayoutManager(EquipmentDetailsActivity.this));
                }
            }
        });
        List<Equipment> equipments;
        equipments = db.getEquipments();
        CustomEquip adapter = new CustomEquip(EquipmentDetailsActivity.this,EquipmentDetailsActivity.this,equipments,db);
        EquipmentList.setAdapter(adapter);
        EquipmentList.setLayoutManager(new LinearLayoutManager(EquipmentDetailsActivity.this));
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    private void initValues() {
        btnAddEquipment = findViewById(R.id.btnAddEquipment);
        EquipmentList = findViewById(R.id.EquipmentList);
        imgSearchEquipment = findViewById(R.id.imgSearchEquipment);
        edtSearchNameEquipment = findViewById(R.id.edtSearchNameEquipment);
        SearchedEquipments = findViewById(R.id.SearchedEquipments);
    }
    @Override
    public void onBackPressed() {
        if(mugen==1){
            EquipmentList.setVisibility(View.VISIBLE);
            SearchedEquipments.setVisibility(View.GONE);
            edtSearchNameEquipment.setText("");
            mugen=0;
        }
        else {
            finish();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        }
//        mBackPressed = System.currentTimeMillis();
    }
}