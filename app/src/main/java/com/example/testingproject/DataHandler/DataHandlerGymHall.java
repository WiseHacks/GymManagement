package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.testingproject.models.GymHall;
import com.example.testingproject.params.paramsGymHall;

import java.util.ArrayList;
import java.util.List;

public class DataHandlerGymHall extends SQLiteOpenHelper {
    private Context context;
    public DataHandlerGymHall(@Nullable Context context) {
        super(context, paramsGymHall.DB_NAME, null, paramsGymHall.DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + paramsGymHall.TABLE_NAME + "("
                + paramsGymHall.KEY_ID + " INTEGER PRIMARY KEY," + paramsGymHall.KEY_NAME
                + " TEXT, " + paramsGymHall.KEY_HOUR + " TEXT " + ")";
        db.execSQL(create);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + paramsGymHall.TABLE_NAME);
        onCreate(db);
    }

    public List<GymHall> getHalls(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<GymHall> Halls = new ArrayList<>();
        String select = "SELECT * FROM " + paramsGymHall.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                GymHall gymHall = new GymHall();
                try{
                    gymHall.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                gymHall.setName(cursor.getString(1));
                gymHall.setHrs(cursor.getString(2));
                Halls.add(gymHall);
            }
            while (cursor.moveToNext());
        }
        return Halls;
    }
    public void addHall(GymHall gymHall){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsGymHall.KEY_NAME,gymHall.getName());
        values.put(paramsGymHall.KEY_HOUR,gymHall.getHrs());
        db.insert(paramsGymHall.TABLE_NAME,null,values);
        db.close();
    }
    public void removeHall(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsGymHall.TABLE_NAME,paramsGymHall.KEY_ID + "=?",new String[]{String.valueOf(id)});
    }

}
