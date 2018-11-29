package com.example.colliensepodder.parentscare;

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

public class MyAppointments extends AppCompatActivity {

    Toolbar toolbar;
    TextView headerMyAppointment;
    TextView textViewMyAppointment;
    RecyclerView MyAppointmentRV;
    LinearLayout linearLayoutNoMyAppointment;
    LinearLayout linearLayoutAddMyAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        toolbar= (Toolbar)findViewById(R.id.toolbar);

        //Rlayout = (LinearLayout) findViewById(R.id.rlayout);
        linearLayoutNoMyAppointment = (LinearLayout) findViewById(R.id.linearLayoutNoMyAppointment);
        linearLayoutAddMyAppointment = (LinearLayout) findViewById(R.id.linearLayoutAddMyAppointment);
        headerMyAppointment= (TextView)findViewById(R.id.headerMyAppointment);
        textViewMyAppointment= (TextView)findViewById(R.id.textViewMyAppointment);

    }

    @Override
    protected void onResume() {

        super.onResume();
        MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
        ArrayList<AppointmentInfo> appointmentInfos = obj.retriveAllDoctorAppointment();
        if(appointmentInfos.size()!=0) {
            MyAppointmentRV= (RecyclerView)findViewById(R.id.MyAppointmentRV);
            linearLayoutNoMyAppointment.setVisibility(View.INVISIBLE);
            LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            MyAppointmentRV.setLayoutManager(LayoutManagaer);

            MyAppointmentAdopter adapter = new MyAppointmentAdopter(this, appointmentInfos , new Callback() {
                @Override
                public void Result(String result) {
                    onResume();
                }
            });
            MyAppointmentRV.setAdapter(adapter);
        }
        else {
            linearLayoutNoMyAppointment.setVisibility(View.VISIBLE);

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }

    public void goToAddAppointmentActivity(View view) {
        Intent ii=new Intent(this,AddMyAppointsmentsActivity.class);
        ii.putExtra("mode","1");
        startActivity(ii);
    }
}
