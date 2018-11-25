package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMyAppointsmentsActivity extends AppCompatActivity {

    EditText editMyAppointmentTitle;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_appointsments);

        editMyAppointmentTitle = findViewById(R.id.editMyAppointmentTitle);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextLocation = findViewById(R.id.editTextLocation);

        editTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMyAppointsmentsActivity.this,Doctor.class);
                startActivity(i);
            }
        });
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }
}
