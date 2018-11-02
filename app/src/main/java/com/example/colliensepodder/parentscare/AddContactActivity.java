package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.colliensepodder.parentscare.EmergencyContactAdopter.updatedcontact;

public class AddContactActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone;

    String mode;
    TextView textViewDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);


        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");
        } else {
            editTextName.setText(updatedcontact.getContactName());
            editTextPhone.setText(updatedcontact.getContactNumber());
            textViewDone.setText("Update");
        }
    }

    public void clickAddContact(View view) {

        if (editTextName.getText().toString().equals("")) {
            editTextName.setError("This field can't be empty");
            return;
        } else if (editTextPhone.getText().toString().equals("")) {
            editTextPhone.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                Contact newContact = new Contact(editTextName.getText().toString(), editTextPhone.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addEmergencyContact(newContact);
                Toast.makeText(getApplicationContext(), "Emergency Contact Added succesfull " + g, Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Contact newContact = new Contact(editTextName.getText().toString(), editTextPhone.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateEmergencyContact(newContact, updatedcontact);
                Toast.makeText(getApplicationContext(), "Emergency Contact Updated succesfull " + g, Toast.LENGTH_SHORT).show();
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
