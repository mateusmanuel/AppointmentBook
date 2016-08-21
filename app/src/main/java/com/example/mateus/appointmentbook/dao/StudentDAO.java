package com.example.mateus.appointmentbook.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mateus on 8/21/16.
 */
public class StudentDAO extends SQLiteOpenHelper{

    public StudentDAO(Context context) {
        super(context, "AppointmentBook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT, phone TEXT, site TEXT, rate REAL";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Students";
        db.execSQL(sql);
        onCreate(db);
    }
}
