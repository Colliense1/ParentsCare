package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class Emergencycontacts extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView eContactsRV;
    //ImageView imageView;
    TextView textView1,textView2,textView3;
    //RecyclerView recyclerView;
    LinearLayout Rlayout,LinearLayoutNoEmergencyContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycontacts);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //imageView = (ImageView) findViewById(R.id.imageViewHamburger);
        textView1 = (TextView) findViewById(R.id.headerEmergencyContacts);
        textView2 = (TextView) findViewById(R.id.textViewAddEmergencyContacts);
        Rlayout =  findViewById(R.id.rlayout);
        LinearLayoutNoEmergencyContacts = findViewById(R.id.linearLayoutNoEmergencyContacts);
        eContactsRV =  findViewById(R.id.EContactsRV);
        //textView3 = (TextView) findViewById(R.id.toolbar);
        //recyclerView = (RecyclerView)findViewById(R.id.recyclerViewEmergencyContacts);
    }
        @Override
        protected void onResume() {

            super.onResume();

            MedicineManagementDatabase obj = new MedicineManagementDatabase(this);

            ArrayList<Contact> contacts = obj.retriveAllEmergencyContact();
            if(contacts.size()!=0) {
                LinearLayoutNoEmergencyContacts.setVisibility(View.INVISIBLE);
                LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                eContactsRV.setLayoutManager(LayoutManagaer);
                EmergencyContactAdopter adapter = new EmergencyContactAdopter(this, contacts, new Callback() {
                    @Override
                    public void Result(String result) {
                        onResume();
                    }
                });
                eContactsRV.setAdapter(adapter);
            }
            else {
                LinearLayoutNoEmergencyContacts.setVisibility(View.VISIBLE);
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
        Intent ii=new Intent(this,AddContactActivity.class);
        ii.putExtra("mode","1");
        startActivity(ii);

    }
}
