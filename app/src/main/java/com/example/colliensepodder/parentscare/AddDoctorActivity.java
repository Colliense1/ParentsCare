package com.example.colliensepodder.parentscare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.colliensepodder.parentscare.DoctorAdopter.updateddoctor;
import static com.example.colliensepodder.parentscare.EmergencyContactAdopter.updatedcontact;

public class AddDoctorActivity extends AppCompatActivity {
    EditText editTextName,editTextPhone,editTextEmail;

    String mode;
    TextView textViewDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");
        } else {
            editTextName.setText(updateddoctor.getDoctorName());
            editTextPhone.setText(updateddoctor.getDoctorNumber());
            editTextEmail.setText(updateddoctor.getDoctorEmail());
            textViewDone.setText("Update");
        }

    }
    public void clickAddDoctor(View view) {

        if(editTextName.getText().toString().equals("")){
            editTextName.setError("This field can't be empty");
            return;
        }else if(editTextPhone.getText().toString().equals("")){
            editTextPhone.setError("This field can't be empty");
            return;
        }else if(editTextEmail.getText().toString().equals("")) {
            editTextEmail.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                Doctorinfo newDoctorinfo = new Doctorinfo
                        (editTextName.getText().toString(),
                        editTextPhone.getText().toString(),
                        editTextEmail.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addDoctor(newDoctorinfo);
                Toast.makeText(getApplicationContext(), "Doctor Added succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();
            }else{
                Doctorinfo newDoctorinfo = new Doctorinfo
                        (editTextName.getText().toString(),
                        editTextPhone.getText().toString(),
                        editTextEmail.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateDoctor(newDoctorinfo,updateddoctor);
                Toast.makeText(getApplicationContext(), "Doctor Updated succesfully " + g, Toast.LENGTH_SHORT).show();
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
