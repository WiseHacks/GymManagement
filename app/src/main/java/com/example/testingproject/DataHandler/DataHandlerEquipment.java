package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testingproject.models.Equipment;
import com.example.testingproject.params.paramsEquipment;

import java.util.ArrayList;
import java.util.List;


public class DataHandlerEquipment extends SQLiteOpenHelper {
    private Context context;
    public DataHandlerEquipment(@Nullable Context context) {
        super(context, paramsEquipment.DB_NAME, null, paramsEquipment.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + paramsEquipment.TABLE_NAME + "("
                + paramsEquipment.KEY_ID + " INTEGER PRIMARY KEY," + paramsEquipment.KEY_NAME
                + " TEXT, " + paramsEquipment.KEY_UNIT + " TEXT, " + paramsEquipment.KEY_LOCATION
                 + " TEXT " + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ paramsEquipment.TABLE_NAME);
        onCreate(db);
    }

    public void addEquipment(Equipment equipment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsEquipment.KEY_NAME,equipment.getName());
        values.put(paramsEquipment.KEY_UNIT,equipment.getUnits());
        values.put(paramsEquipment.KEY_LOCATION,equipment.getLocation());
        db.insert(paramsEquipment.TABLE_NAME,null,values);
        db.close();
    }
    public List<Equipment> getEquipments(){
        List<Equipment> equipmentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsEquipment.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Equipment equipment = new Equipment();
                try{
                    equipment.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                equipment.setName(cursor.getString(1));
                equipment.setUnits(cursor.getString(2));
                equipment.setLocation(cursor.getString(3));
                equipmentList.add(equipment);
            }
            while (cursor.moveToNext());
        }
        return equipmentList;
    }
    public void removeEquipment(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsEquipment.TABLE_NAME, paramsEquipment.KEY_ID + "=?",new String[]{String.valueOf(id)});
        //     db.close();
    }
    public int getCount(){
        String query = "SELECT  * FROM " + paramsEquipment.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    public void updateEquipment(Equipment equipment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsEquipment.KEY_NAME,equipment.getName());
        values.put(paramsEquipment.KEY_UNIT,equipment.getUnits());
        values.put(paramsEquipment.KEY_LOCATION,equipment.getLocation());
        long result = db.update(paramsEquipment.TABLE_NAME,values,paramsEquipment.KEY_ID + "=?",
                new String[]{String.valueOf(equipment.getId())});
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    //Todo : Search Query??
    public List<Equipment> searchTrainer(String name){
        List<Equipment> equipmentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsEquipment.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Equipment equipment = new Equipment();
                try{
                    equipment.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                equipment.setName(cursor.getString(1));
                equipment.setUnits(cursor.getString(2));
                equipment.setLocation(cursor.getString(3));
                if(equipment.getName().equals(name))equipmentList.add(equipment);
            }
            while (cursor.moveToNext());
        }
        return equipmentList;
    }
}
