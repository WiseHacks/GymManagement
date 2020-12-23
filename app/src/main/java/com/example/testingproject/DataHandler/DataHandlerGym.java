package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.testingproject.models.Gym;
import com.example.testingproject.params.paramsGym;

import java.util.ArrayList;
import java.util.List;

public class DataHandlerGym extends SQLiteOpenHelper {
    public DataHandlerGym(@Nullable Context context) {
        super(context, paramsGym.DB_NAME, null, paramsGym.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+ paramsGym.TABLE_NAME + "("
                + paramsGym.KEY_ID + " INTEGER PRIMARY KEY," + paramsGym.KEY_NAME
                + " TEXT, "+ paramsGym.KEY_ADDRESS+ " TEXT " + ")";
        Log.d("dbHand","Query created");
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addGym(Gym gym){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsGym.KEY_NAME,gym.getName());
        values.put(paramsGym.KEY_ADDRESS,gym.getAddress());
        db.insert(paramsGym.TABLE_NAME,null,values);
        db.close();
    }
    public List<Gym> getGym(){
        List<Gym> Mygymdetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsGym.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Gym gym = new Gym();
                gym.setName(cursor.getString(1));
                gym.setAddress(cursor.getString(2));
                Mygymdetails.add(gym);
            }
            while (cursor.moveToNext());
        }
        return Mygymdetails;
    }
    public void removeGym(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsGym.TABLE_NAME, paramsGym.KEY_ID + "=?",new String[]{String.valueOf(id)});
    }
    public int getCount(){
        String query = "SELECT  * FROM " + paramsGym.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

}
