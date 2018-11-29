package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by colliensepodder on 8/30/2018.
 */

public class MyDiary extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView DiaryRV;
    LinearLayout linearLayoutNoDiary;
    TextView headerMyDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        linearLayoutNoDiary =(LinearLayout)findViewById(R.id.linearLayoutNoDiary);
        headerMyDiary =(TextView) findViewById(R.id.headerMyDiary);
    }

    @Override
    protected void onResume() {
        super.onResume();

        MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
        ArrayList<Diary> diaries = obj.retriveAllDiary();
        if(diaries.size()!=0) {
            DiaryRV= (RecyclerView)findViewById(R.id.DiaryRV);
            linearLayoutNoDiary.setVisibility(View.INVISIBLE);
            LinearLayoutManager LayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            DiaryRV.setLayoutManager(LayoutManagaer);
            DiaryAdopter adapter = new DiaryAdopter(this, diaries, new Callback() {
                @Override
                public void Result(String result) {
                    onResume();
                }
            });
            DiaryRV.setAdapter(adapter);
        }
        else {
            linearLayoutNoDiary.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();

    }

    public void goToAddDiaryActivity(View view) {
        Intent ii=new Intent(this,AddDiaryActivity.class);
        ii.putExtra("mode","1");
        startActivity(ii);
    }
}
