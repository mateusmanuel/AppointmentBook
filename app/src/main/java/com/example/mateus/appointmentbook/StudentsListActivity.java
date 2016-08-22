package com.example.mateus.appointmentbook;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mateus.appointmentbook.dao.StudentDAO;
import com.example.mateus.appointmentbook.model.Student;

import java.util.List;

import static com.example.mateus.appointmentbook.R.*;

public class StudentsListActivity extends AppCompatActivity {

    private ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_students_list);

        studentsList = (ListView) findViewById(id.students_list);

        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Student student = (Student) studentsList.getItemAtPosition(position);

                Intent goToForm = new Intent(StudentsListActivity.this, FormActivity.class);
                goToForm.putExtra("student", student);
                startActivity(goToForm);
            }
        });

        Button newStudent = (Button) findViewById(id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToForm = new Intent(StudentsListActivity.this, FormActivity.class);
                startActivity(goToForm);
            }
        });

        registerForContextMenu(studentsList);
    }

    private void loadList() {
        StudentDAO dao  = new StudentDAO(this);
        List<Student> students = dao.findStudents();
        dao.close();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_expandable_list_item_1, students);
        studentsList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        loadList();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) studentsList.getItemAtPosition(info.position);

                StudentDAO dao = new StudentDAO(StudentsListActivity.this);
                dao.delete(student);
                dao.close();

                loadList();
                return false;
            }
        });
    }
}
