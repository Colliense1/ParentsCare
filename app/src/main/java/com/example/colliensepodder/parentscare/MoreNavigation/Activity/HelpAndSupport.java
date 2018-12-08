package com.example.colliensepodder.parentscare.MoreNavigation.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.colliensepodder.parentscare.R;

public class HelpAndSupport extends AppCompatActivity {

    Button sendEmailToDevelopper;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_support);

        //toolbar = findViewById(R.id.toolbar);
        sendEmailToDevelopper = findViewById(R.id.sendEmailToDevelopper);

        sendEmailToDevelopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                String [] s = {"colliensepodder25@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL,s);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Send Email To Developper"));
                startActivity(email);
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
