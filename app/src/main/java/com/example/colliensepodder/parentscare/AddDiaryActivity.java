package com.example.colliensepodder.parentscare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.colliensepodder.parentscare.DiaryAdopter.updateddiary;
import static com.example.colliensepodder.parentscare.EmergencyContactAdopter.updatedcontact;


/**
 * Created by colliensepodder on 8/30/2018.
 */

public class AddDiaryActivity extends AppCompatActivity {

    EditText editTextNote;
    String mode;
    TextView textViewDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        editTextNote = (EditText) findViewById(R.id.editTextNote);

        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");
        } else {
            editTextNote.setText(updateddiary.getDiaryText());
            textViewDone.setText("Update");
        }
    }

    public void clickAddDiary(View view) {

        if (editTextNote.getText().toString().equals("")) {
            editTextNote.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                Diary newdiaries = new Diary(editTextNote.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addDiary(newdiaries);
                Toast.makeText(getApplicationContext(), "Diary Added succesfully ", Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Diary newDiaries = new Diary(editTextNote.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateDiary(newDiaries,updateddiary);
                Toast.makeText(getApplicationContext(), "Diary Updated succesfull ", Toast.LENGTH_SHORT).show();
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
