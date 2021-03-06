package com.example.colliensepodder.parentscare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by colliensepodder on 9/30/2018.
 */

public class SelectDoctorActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView doctorsRV;
    LinearLayout Rlayout;
    LinearLayout LinearLayoutNoDoctor;
    LinearLayout linearLayoutAddDoctor;
    TextView headerDoctors;
    TextView textViewAddDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_doctor);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Rlayout = (LinearLayout) findViewById(R.id.rlayout);
        LinearLayoutNoDoctor = (LinearLayout) findViewById(R.id.linearLayoutNoDoctor);
        linearLayoutAddDoctor = (LinearLayout) findViewById(R.id.linearLayoutAddDoctor);
        headerDoctors = (TextView) findViewById(R.id.headerDoctors);
        textViewAddDoctor = (TextView) findViewById(R.id.textViewAddDoctor);
    }

    @Override
    protected void onResume() {

        super.onResume();
        MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
        ArrayList<Doctorinfo> doctorinfos = obj.retriveAllDoctor();
        if (doctorinfos.size() != 0) {
            doctorsRV = (RecyclerView) findViewById(R.id.eDoctorsRV);
            LinearLayoutNoDoctor.setVisibility(View.INVISIBLE);
            LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            doctorsRV.setLayoutManager(LayoutManagaer);

            SelectDoctorAdapter adapter = new SelectDoctorAdapter(this, doctorinfos, new Callback() {
                @Override
                public void Result(String result) {
                    if (result.equals("0")) {
                        onResume();
                    } else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("name", result);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }

                }
            });
            doctorsRV.setAdapter(adapter);
        } else {
            LinearLayoutNoDoctor.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }

    public void goToAddActivity(View view) {
        Intent ii = new Intent(this, AddDoctorActivity.class);
        ii.putExtra("mode", "1");
        startActivity(ii);
    }
}
