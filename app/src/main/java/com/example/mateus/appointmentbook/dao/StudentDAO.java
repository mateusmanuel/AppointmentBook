package com.example.mateus.appointmentbook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.mateus.appointmentbook.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateus on 8/21/16.
 */
public class StudentDAO extends SQLiteOpenHelper {

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

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getStudentData(student);

        db.insert("Students", null, data);
    }

    @NonNull
    private ContentValues getStudentData(Student student) {
        ContentValues data = new ContentValues();
        data.put("name", student.getName());
        data.put("adress", student.getAddress());
        data.put("phone", student.getPhone());
        data.put("site", student.getSite());
        data.put("rate", student.getRate());
        return data;
    }

    public List<Student> findStudents() {
        String sql = "SELECT * FROM Students;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Student> students = new ArrayList<Student>();
        while (c.moveToNext()) {
            Student student = new Student();

            student.setId(c.getLong(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAddress(c.getString(c.getColumnIndex("address")));
            student.setPhone(c.getString(c.getColumnIndex("phone")));
            student.setSite(c.getString(c.getColumnIndex("site")));
            student.setRate(c.getDouble(c.getColumnIndex("rate")));

            students.add(student);
        }
        c.close();

        return students;
    }

    public void delete(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {student.getId().toString()};
        db.delete("Students", "id = ?", params);
    }

    public void update(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getStudentData(student);

        String[] params = {student.getId().toString()};
        db.update("Students", data, "id = ?", params);
    }
}