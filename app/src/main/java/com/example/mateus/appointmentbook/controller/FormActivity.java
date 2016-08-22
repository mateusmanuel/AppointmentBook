package com.example.mateus.appointmentbook.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mateus.appointmentbook.R;
import com.example.mateus.appointmentbook.dao.StudentDAO;
import com.example.mateus.appointmentbook.model.Student;

public class FormActivity extends AppCompatActivity {

    private FormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new FormHelper(this);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if(student != null){
            helper.fillForm(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.form_menu:
                Student student = helper.getStudent();

                StudentDAO dao = new StudentDAO(this);
                if(student.getId() != null){
                    dao.update(student);
                } else {
                    dao.insert(student);
                }
                dao.close();

                Toast.makeText(FormActivity.this, student.getName() + " Saved!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
