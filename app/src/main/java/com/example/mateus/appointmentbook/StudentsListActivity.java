package com.example.mateus.appointmentbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import static com.example.mateus.appointmentbook.R.*;

public class StudentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_students_list);

        String[] students = {"Mateus", "Renata", "Sabryna", "João Vitor", "João Paulo", "Roger", "Allan", "Ronyell", "Igor", "Hugo"};

        ListView studentsList = (ListView) findViewById(id.students_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, students);
        studentsList.setAdapter(adapter);

        Button newStudent = (Button) findViewById(id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToForm = new Intent(StudentsListActivity.this, FormActivity.class);
                startActivity(goToForm);
            }
        });
    }
}
