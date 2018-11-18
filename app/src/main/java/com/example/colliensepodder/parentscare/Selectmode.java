package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Size;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.w3c.dom.Text;

public class Selectmode extends AppCompatActivity {

    CircleMenu circleMenu;
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmode);

        mtoolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("                  SelectMode");

        circleMenu = (CircleMenu)findViewById(R.id.circle_menu);


        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),
                R.mipmap.icon_menu,
                R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.icon_parents)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_child);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Toast.makeText(Selectmode.this, "Parents", Toast.LENGTH_SHORT).show();
                                                             Intent i = new Intent(Selectmode.this,Parentslogin.class);
                                                             startActivity(i);
                                                             break;
                                                         case 1:
                                                             Toast.makeText(Selectmode.this, "Child", Toast.LENGTH_SHORT).show();
                                                             Intent ii = new Intent(Selectmode.this,Childlogin.class);
                                                             startActivity(ii);
                                                             break;

                                                     }
                                                 }
                                             }

        );

       /* circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {
                                                        // Toast.makeText(Selectmode.this, "Menu Opend", Toast.LENGTH_SHORT).show();
                                                     }

                                                     @Override
                                                     public void onMenuClosed() {
                                                        // Toast.makeText(Selectmode.this, "Menu Closed", Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
        );*/


    }
    /*@Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }*/



   /* public void rb1(View view) {
        Intent i = new Intent(this,Parentslogin.class);
        startActivity(i);
    }

    public void rb2(View view) {
        Intent i = new Intent(this,Childlogin.class);
        startActivity(i);
    }*/
}
