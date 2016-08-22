package com.example.mateus.appointmentbook;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.mateus.appointmentbook.model.Student;

/**
 * Created by mateus on 8/21/16.
 */
public class FormHelper {

    private final EditText nameField;
    private final EditText addressField;
    private final EditText phoneField;
    private final EditText siteField;
    private final RatingBar rateField;

    private Student student;

    public FormHelper(FormActivity activity){
        nameField  = (EditText) activity.findViewById(R.id.form_name);
        addressField  = (EditText) activity.findViewById(R.id.form_address);
        phoneField  = (EditText) activity.findViewById(R.id.form_phone);
        siteField  = (EditText) activity.findViewById(R.id.form_site);
        rateField  = (RatingBar) activity.findViewById(R.id.form_rate);
        student = new Student();
    }

    public Student getStudent() {
        Student student = new Student();
        student.setName(nameField.getText().toString());
        student.setAddress(addressField.getText().toString());
        student.setPhone(phoneField.getText().toString());
        student.setSite(siteField.getText().toString());
        student.setRate(Double.valueOf(rateField.getProgress()));

        return student;
    }

    public void fillForm(Student student) {
        nameField.setText(student.getName());
        addressField.setText(student.getAddress());
        phoneField.setText(student.getPhone());
        siteField.setText(student.getSite());
        rateField.setProgress(student.getRate().intValue());
        this.student = student;
    }
}
