package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAppointments extends AppCompatActivity {

    Toolbar toolbar;
    TextView headerMyAppointment;
    RecyclerView MyAppointmentRV;
    LinearLayout linearLayoutNoMyAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
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
        //ii.putExtra("mode","1");
        startActivity(ii);
    }
}
