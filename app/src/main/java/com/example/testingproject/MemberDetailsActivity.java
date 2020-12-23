package com.example.testingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testingproject.DataHandler.DataHandlerCustomer;
import com.example.testingproject.models.Customer;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class MemberDetailsActivity extends AppCompatActivity {
    private Button btnAddMember/*,btnRemoveMember*/;
    private RecyclerView MembersList,SearchedMembers;
    private ImageView imgSearch;
    private EditText edtSearchName;
    private int faye=0;
    DataHandlerCustomer db;
//    private static final int TIME_INTERVAL = 3000;
//    private long mBackPressed;
//    ArrayList<String> Member_id,Member_name,Member_age,Member_gender,Member_email,Member_phone,Member_subs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);
        initValues();
        db = new DataHandlerCustomer(MemberDetailsActivity.this);
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberDetailsActivity.this,MemberFormActivity.class);
                startActivity(intent);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Customer> kira = (ArrayList<Customer>) db.getMembers();
                String s = edtSearchName.getText().toString().toLowerCase();
//                s.toLowerCase();
                String s2="";
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i) != ' ')s2 = s2 + s.charAt(i);
                }
                ArrayList<Customer> temp = new ArrayList<>();
                for (Customer c:
                        kira) {
                    String A1 = c.getName().toLowerCase();
//                    A1.toLowerCase();
                    String A2="";
                    for(int i=0;i<A1.length();i++){
                        if(A1.charAt(i) != ' ')A2 = A2 + A1.charAt(i);
                    }
                    if(A2.equals(s2))temp.add(c);
                }
//                ArrayList<Customer> temp = (ArrayList<Customer>) db.searchCustomer(edtSearchName.getText().toString());
                if(temp.size()==0){
                    Toast.makeText(MemberDetailsActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                else {
                    faye =1;
                    Toast.makeText(MemberDetailsActivity.this,"Member Found", Toast.LENGTH_SHORT).show();
                    MembersList.setVisibility(View.GONE);
                    SearchedMembers.setVisibility(View.VISIBLE);
                    CustomAdapter Adapter = new CustomAdapter(MemberDetailsActivity.this,MemberDetailsActivity.this,temp,db);
                    SearchedMembers.setAdapter(Adapter);
                    SearchedMembers.setLayoutManager(new LinearLayoutManager(MemberDetailsActivity.this));
                }
            }
        });
        List<Customer> Members;
        Members = db.getMembers();
        CustomAdapter adapter = new CustomAdapter(MemberDetailsActivity.this,MemberDetailsActivity.this,Members,db);
        MembersList.setAdapter(adapter);
        MembersList.setLayoutManager(new LinearLayoutManager(MemberDetailsActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void initValues() {
        btnAddMember = findViewById(R.id.btnAddMember);
        MembersList = findViewById(R.id.MembersList);
        imgSearch = findViewById(R.id.imgSearch);
        edtSearchName = findViewById(R.id.edtSearchName);
        SearchedMembers = findViewById(R.id.SearchedMembers);
//        btnRemoveMember = findViewById(R.id.btnRemoveMember);
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
    public void onBackPressed() {
        if(faye==1){
            SearchedMembers.setVisibility(View.GONE);
            MembersList.setVisibility(View.VISIBLE);
            edtSearchName.setText("");
            faye = 0;
        }
        else {
//            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                finish();
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
//            } else {
//                Toast.makeText(getBaseContext(), "Click Again to close the activity", Toast.LENGTH_SHORT).show();
//            }
        }
//        mBackPressed = System.currentTimeMillis();
    }
}