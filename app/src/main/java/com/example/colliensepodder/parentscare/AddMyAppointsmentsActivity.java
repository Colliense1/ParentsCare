package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.colliensepodder.parentscare.DoctorAdopter.updateddoctor;
import static com.example.colliensepodder.parentscare.MyAppointmentAdopter.updateddoctorappointment;

public class AddMyAppointsmentsActivity extends AppCompatActivity {

    EditText editMyAppointmentTitle;
    EditText editMyAppointmentName;
    EditText editTimeAppointmentPicker;
    EditText editTextLocation;
    Button textViewDone;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_appointsments);

        editMyAppointmentTitle = findViewById(R.id.editMyAppointmentTitle);
        editMyAppointmentName = findViewById(R.id.editMyAppointmentName);
        editTimeAppointmentPicker = findViewById(R.id.editTimeAppointmentPicker);
        editTextLocation = findViewById(R.id.editTextLocation);
        textViewDone = findViewById(R.id.textViewDone);

        editMyAppointmentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMyAppointsmentsActivity.this,SelectDoctorActivity.class);
                startActivity(i);
            }
        });

        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");

        } else {
            editMyAppointmentTitle.setText(updateddoctorappointment.getDoctorAppointmentTitle());
            editMyAppointmentName.setText(updateddoctorappointment.getDoctorAppointmentName());
            editTextLocation.setText(updateddoctorappointment.getDoctorAppointmentLocation());
            textViewDone.setText("Update");
        }

    }
    public void clickAddAppointments(View view) {

        if (editMyAppointmentTitle.getText().toString().equals("")) {
            editMyAppointmentTitle.setError("This field can't be empty");
            return;
        } else if (editMyAppointmentName.getText().toString().equals("")) {
            editMyAppointmentName.setError("This field can't be empty");
            return;
        }else if (editTextLocation.getText().toString().equals("")) {
            editTextLocation.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                AppointmentInfo newappointment = new AppointmentInfo
                        (editMyAppointmentTitle.getText().toString(),
                                editMyAppointmentName.getText().toString(),
                                editTextLocation.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addDoctorAppointment(newappointment);
                Toast.makeText(getApplicationContext(), "Appointment Added succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                AppointmentInfo newappointment = new AppointmentInfo
                        (editMyAppointmentTitle.getText().toString(),
                                editMyAppointmentName.getText().toString(),
                                editTextLocation.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateDoctorAppointment(newappointment, updateddoctorappointment);
                Toast.makeText(getApplicationContext(), "Appointment Updated succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();

            }
        }

    }

    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }
}
