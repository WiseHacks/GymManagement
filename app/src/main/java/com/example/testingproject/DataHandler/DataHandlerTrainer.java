package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testingproject.models.Customer;
import com.example.testingproject.models.Trainer;
import com.example.testingproject.params.paramsCustomer;
import com.example.testingproject.params.paramsTrainer;

import java.util.ArrayList;
import java.util.List;

public class DataHandlerTrainer extends SQLiteOpenHelper {
    private Context context;
    public DataHandlerTrainer(@Nullable Context context) {
        super(context, paramsTrainer.DB_NAME, null, paramsTrainer.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + paramsTrainer.TABLE_NAME + "("
                + paramsTrainer.KEY_ID + " INTEGER PRIMARY KEY," + paramsTrainer.KEY_NAME
                + " TEXT, " + paramsTrainer.KEY_EMAIL + " TEXT, " + paramsTrainer.KEY_PHONE
                + " TEXT, " + paramsTrainer.KEY_ASSIGN + " TEXT " + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ paramsTrainer.TABLE_NAME);
        onCreate(db);
    }
    public void addTrainer(Trainer trainer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsTrainer.KEY_NAME,trainer.getName());
        values.put(paramsTrainer.KEY_EMAIL,trainer.getEmail());
        values.put(paramsTrainer.KEY_PHONE,trainer.getPhone());
        values.put(paramsTrainer.KEY_ASSIGN,trainer.getAssigned());
        db.insert(paramsTrainer.TABLE_NAME,null,values);
        db.close();
    }
    public List<Trainer> getTrainers(){
        List<Trainer> TrainerDetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsTrainer.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Trainer trainer = new Trainer();
                try{
                    trainer.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                trainer.setName(cursor.getString(1));
                trainer.setEmail(cursor.getString(2));
                trainer.setPhone(cursor.getString(3));
                trainer.setAssigned(cursor.getString(4));
                TrainerDetails.add(trainer);
            }
            while (cursor.moveToNext());
        }
        return TrainerDetails;
    }
    public void removeTrainer(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsTrainer.TABLE_NAME, paramsTrainer.KEY_ID + "=?",new String[]{String.valueOf(id)});
   //     db.close();
    }
    public int getCount(){
        String query = "SELECT  * FROM " + paramsTrainer.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    public void updateTrainer(Trainer trainer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsTrainer.KEY_NAME,trainer.getName());
        values.put(paramsTrainer.KEY_EMAIL,trainer.getEmail());
        values.put(paramsTrainer.KEY_PHONE,trainer.getPhone());
        values.put(paramsTrainer.KEY_ASSIGN,trainer.getAssigned());
        long result = db.update(paramsTrainer.TABLE_NAME,values,paramsTrainer.KEY_ID + "=?",
                new String[]{String.valueOf(trainer.getId())});
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    //Todo : Search Query??
    public List<Trainer> searchTrainer(String name){
        List<Trainer> TrainerDetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsTrainer.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                Trainer trainer = new Trainer();
                try{
                    trainer.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                trainer.setName(cursor.getString(1));
                trainer.setEmail(cursor.getString(2));
                trainer.setPhone(cursor.getString(3));
                trainer.setAssigned(cursor.getString(4));
                if(trainer.getName().equals(name))TrainerDetails.add(trainer);
            }
            while (cursor.moveToNext());
        }
        return TrainerDetails;
    }

}

