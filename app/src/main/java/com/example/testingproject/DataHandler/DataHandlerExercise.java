package com.example.testingproject.DataHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testingproject.R;
import com.example.testingproject.models.ExercisePlan;
import com.example.testingproject.params.paramsExercise;

import java.util.ArrayList;
import java.util.List;

public class DataHandlerExercise extends SQLiteOpenHelper {
    private Context context;
    public DataHandlerExercise(@Nullable Context context) {
        super(context, paramsExercise.DB_NAME, null, paramsExercise.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT, %s TEXT, %s TEXT, %s TEXT )", paramsExercise.TABLE_NAME, paramsExercise.KEY_ID, paramsExercise.KEY_NAME, paramsExercise.KEY_EQUIP, paramsExercise.KEY_DESC, paramsExercise.KEY_FROM);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ paramsExercise.TABLE_NAME);
        onCreate(db);
    }
    public void addPlan(ExercisePlan exercisePlan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsExercise.KEY_NAME,exercisePlan.getName());
        values.put(paramsExercise.KEY_EQUIP,exercisePlan.getReqEquip());
        values.put(paramsExercise.KEY_DESC,exercisePlan.getDescription());
        values.put(paramsExercise.KEY_FROM,exercisePlan.getSubmitedBy());
        db.insert(paramsExercise.TABLE_NAME,null,values);
        db.close();
    }

    public List<ExercisePlan> getPlans(){
        List<ExercisePlan> exercisePlans = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsExercise.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                ExercisePlan exercisePlan = new ExercisePlan();
                try{
                    exercisePlan.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                exercisePlan.setName(cursor.getString(1));
                exercisePlan.setReqEquip(cursor.getString(2));
                exercisePlan.setDescription(cursor.getString(3));
                exercisePlan.setSubmitedBy(cursor.getString(4));
                exercisePlans.add(exercisePlan);
            }
            while (cursor.moveToNext());
        }
        return exercisePlans;
    }
    public void removePlan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(paramsExercise.TABLE_NAME, paramsExercise.KEY_ID + "=?",new String[]{String.valueOf(id)});
        //     db.close();
    }
    public int getCount(){
        String query = "SELECT  * FROM " + paramsExercise.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    public void updatePlan(ExercisePlan exercisePlan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(paramsExercise.KEY_NAME,exercisePlan.getName());
        values.put(paramsExercise.KEY_EQUIP,exercisePlan.getReqEquip());
        values.put(paramsExercise.KEY_DESC,exercisePlan.getDescription());
        values.put(paramsExercise.KEY_FROM,exercisePlan.getSubmitedBy());
        long result = db.update(paramsExercise.TABLE_NAME,values,paramsExercise.KEY_ID + "=?",
                new String[]{String.valueOf(exercisePlan.getId())});
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    //Todo : Search Query??
    //TODO : Big todo...Ask plan in member form
    public List<ExercisePlan> searchPlan(String name){
        List<ExercisePlan> exercisePlans = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + paramsExercise.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()) {
            do {
                ExercisePlan exercisePlan = new ExercisePlan();
                try{
                    exercisePlan.setId(Integer.parseInt(cursor.getString(0)));
                }catch (Exception e){ e.printStackTrace(); }
                exercisePlan.setName(cursor.getString(1));
                exercisePlan.setReqEquip(cursor.getString(2));
                exercisePlan.setDescription(cursor.getString(3));
                exercisePlan.setSubmitedBy(cursor.getString(4));
                if(exercisePlan.getName().equals(name))exercisePlans.add(exercisePlan);
            }
            while (cursor.moveToNext());
        }
        return exercisePlans;
    }

}
