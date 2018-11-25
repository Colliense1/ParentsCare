package com.example.colliensepodder.parentscare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.colliensepodder.parentscare.DoctorAdopter.updateddoctor;
import static com.example.colliensepodder.parentscare.EmergencyContactAdopter.updatedcontact;

public class AddDoctorActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextSpeciality;
    String mode;
    TextView textViewDone;
    CircleImageView imageViewMale;
    CircleImageView imageViewFemale;


    private int avatar = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSpeciality = (EditText) findViewById(R.id.editTextSpeciality);
        imageViewMale = (CircleImageView)findViewById(R.id.imageViewMale);
        imageViewFemale = (CircleImageView)findViewById(R.id.imageViewFemale);

        editTextSpeciality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] charSequenceArr = new CharSequence[]
                        {"Dental & Maxillo-Facial Surgery", "Obstetrics & Gynecology",
                                "Cardiology", "Dermatology", "Anesthesiology", "Nephrology", "Neurology",
                                "Gastroenterology", "Ophthalmology", "Allergy & Immunology", "Otolaryngology",
                                "Psychiatry", "General Practice",
                                "General Surgery", "Medicine",
                                "Orthopaedics", "Paediatrics", "Urology", "Other"};
                new AlertDialog.Builder(AddDoctorActivity.this).setTitle("Select Speciality")
                        .setIcon(R.drawable.image_zero_case_doctors)
                        .setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (i == charSequenceArr.length - 1) {
                                    final View editText = new EditText(AddDoctorActivity.this);
                                    new AlertDialog.Builder(AddDoctorActivity.this)
                                            .setTitle("Type speciality").setView(editText)
                                            .setIcon(R.drawable.image_zero_case_doctors).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //editTextSpeciality.setText(editText.toString());
                                        }
                                    }).show();
                                    return;
                                }
                                AddDoctorActivity.this.editTextSpeciality.setText(charSequenceArr[i]);

                            }
                        }).create().show();
            }
        });

        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");
        } else {
            editTextName.setText(updateddoctor.getDoctorName());
            editTextPhone.setText(updateddoctor.getDoctorNumber());
            editTextEmail.setText(updateddoctor.getDoctorEmail());
            editTextSpeciality.setText(updateddoctor.getSpeciality());
            textViewDone.setText("Update");
        }

    }

    public void clickAddDoctor(View view) {

        if (editTextName.getText().toString().equals("")) {
            editTextName.setError("This field can't be empty");
            return;
        } else if (editTextPhone.getText().toString().equals("")) {
            editTextPhone.setError("This field can't be empty");
            return;
        } else if (editTextSpeciality.getText().toString().equals("")) {
            editTextSpeciality.setError("This field can't be empty");
            return;
        }else if (editTextEmail.getText().toString().equals("")) {
            editTextEmail.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                Doctorinfo newDoctorinfo = new Doctorinfo
                        (editTextName.getText().toString(),
                                editTextPhone.getText().toString(),
                                editTextEmail.getText().toString(),
                                editTextSpeciality.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addDoctor(newDoctorinfo);
                Toast.makeText(getApplicationContext(), "Doctor Added succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Doctorinfo newDoctorinfo = new Doctorinfo
                        (editTextName.getText().toString(),
                                editTextPhone.getText().toString(),
                                editTextEmail.getText().toString(),
                                editTextSpeciality.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateDoctor(newDoctorinfo, updateddoctor);
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


    public void imgmaleAvatar(){
        this.imageViewMale.setBackground(ContextCompat.getDrawable(this,R.drawable.background_red_circle));
        this.imageViewFemale.setBackground(null);
        this.avatar = 1;
    }

    public void imgfemaleAvater(){
        this.imageViewFemale.setBackground(ContextCompat.getDrawable(this,R.drawable.background_red_circle));
        this.imageViewMale.setBackground(null);
        this.avatar = 2;
    }

}
