package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testingproject.models.Customer;
import com.example.testingproject.params.paramsCustomer;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

public class DataHandlerCustomer extends SQLiteOpenHelper {
    private Context context;
    public DataHandlerCustomer(@Nullable Context context) {

        super(context, paramsCustomer.DB_NAME, null, paramsCustomer.DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + paramsCustomer.TABLE_NAME + "("
                + paramsCustomer.KEY_ID + " INTEGER PRIMARY KEY," + paramsCustomer.KEY_NAME
                + " TEXT, " + paramsCustomer.KEY_AGE + " TEXT, " + paramsCustomer.KEY_GENDER
                + " TEXT, " + paramsCustomer.KEY_EMAIL + " TEXT, " + paramsCustomer.KEY_PHONE
                + " TEXT, " + paramsCustomer.KEY_SUBS + " TEXT, "+paramsCustomer.KEY_CHOOSE
                + " TEXT " + ")";
        db.execSQL(create);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ paramsCustomer.TABLE_NAME);
        onCreate(db);
    }
    public void addMember(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsCustomer.KEY_NAME,customer.getName());
        values.put(paramsCustomer.KEY_AGE,customer.getAge());
        values.put(paramsCustomer.KEY_GENDER,customer.getGender());
        values.put(paramsCustomer.KEY_EMAIL,customer.getEmail());
        values.put(paramsCustomer.KEY_PHONE,customer.getPhone());
        values.put(paramsCustomer.KEY_SUBS,customer.getSubs());
        values.put(paramsCustomer.KEY_CHOOSE,customer.getChosen());
        db.insert(paramsCustomer.TABLE_NAME,null,values);
        db.close();
    }
    public List<Customer> getMembers(){
        List<Customer> MemberDetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsCustomer.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
               Customer customer = new Customer();
               try{
               customer.setId(Integer.parseInt(cursor.getString(0)));
               }catch (Exception e){ e.printStackTrace(); }
               customer.setName(cursor.getString(1));
               customer.setAge(cursor.getString(2));
               customer.setGender(cursor.getString(3));
               customer.setEmail(cursor.getString(4));
               customer.setPhone(cursor.getString(5));
               customer.setSubs(cursor.getString(6));
               customer.setChosen(cursor.getString(7));
               MemberDetails.add(customer);
            }
            while (cursor.moveToNext());
        }
        return MemberDetails;
    }
   public Cursor getCustomer(){
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + paramsCustomer.TABLE_NAME;
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(select,null);
        }
        return cursor;
    }
    public void removeCustomer(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsCustomer.TABLE_NAME, paramsCustomer.KEY_ID + "=?",new String[]{String.valueOf(id)});
//        db.close();
    }
    public int getCount(){
        String query = "SELECT  * FROM " + paramsCustomer.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    public void updateCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsCustomer.KEY_NAME,customer.getName());
        values.put(paramsCustomer.KEY_AGE,customer.getAge());
        values.put(paramsCustomer.KEY_GENDER,customer.getGender());
        values.put(paramsCustomer.KEY_EMAIL,customer.getEmail());
        values.put(paramsCustomer.KEY_PHONE,customer.getPhone());
        values.put(paramsCustomer.KEY_SUBS,customer.getSubs());
        values.put(paramsCustomer.KEY_CHOOSE,customer.getChosen());

        long result = db.update(paramsCustomer.TABLE_NAME,values,paramsCustomer.KEY_ID + "=?",
                new String[]{String.valueOf(customer.getId())});
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public List<Customer> searchCustomer(String name){
        List<Customer> MemberDetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsCustomer.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                try{
                    customer.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                customer.setName(cursor.getString(1));
                customer.setAge(cursor.getString(2));
                customer.setGender(cursor.getString(3));
                customer.setEmail(cursor.getString(4));
                customer.setPhone(cursor.getString(5));
                customer.setSubs(cursor.getString(6));
                customer.setChosen(cursor.getString(7));

                if(customer.getName().equals(name))MemberDetails.add(customer);
            }
            while (cursor.moveToNext());
        }
        return MemberDetails;
    }

}
