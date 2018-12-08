package com.example.colliensepodder.parentscare;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.colliensepodder.parentscare.Helper.LocalHelper;
import com.example.colliensepodder.parentscare.MoreNavigation.Activity.HelpAndSupport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import io.paperdb.Paper;

public class ParentsNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseAuth mAuth;

    public static TextView tv;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHelper.onAttach(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_nav);


        //change language
        Paper.init(this);
        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "en");
        updateView((String) Paper.book().read("language"));


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        tv = (TextView) headerLayout.findViewById(R.id.tv);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            tv.setText(user.getPhoneNumber().toString());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("       Parent's care");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.rel,
                homeFragment,
                homeFragment.getTag()).commit();


    }

    private void updateView(String lang) {
        Context context = LocalHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parents_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.language_en) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
            //return true;
        } else if (id == R.id.language_bn) {
            Paper.book().write("language", "bn");
            updateView((String) Paper.book().read("language"));
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.rel,
                    homeFragment,
                    homeFragment.getTag()).commit();


        } else if (id == R.id.nav_medicine) {
            Intent i = new Intent(this, MedicinePreviewActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_reminder) {
            Intent i = new Intent(this, Pillreminder.class);
            startActivity(i);

        } else if (id == R.id.nav_appointments) {
            Intent i = new Intent(this, MyAppointments.class);
            startActivity(i);

        } else if (id == R.id.nav_library) {

            Intent i = new Intent(this, MedicineLibrary.class);
            startActivity(i);

        } else if (id == R.id.nav_doctor) {
            Intent i = new Intent(this, Doctor.class);
            startActivity(i);

        } else if (id == R.id.nav_contacts) {
            Intent i = new Intent(this, Emergencycontacts.class);
            startActivity(i);

        } else if (id == R.id.nav_diary) {
            Intent i = new Intent(this, MyDiary.class);
            startActivity(i);

        } else if (id == R.id.nav_stats) {

        } else if (id == R.id.nav_bmi) {

            Intent i = new Intent(this, BmiActivity.class);
            startActivity(i);

            /*BmiFragment bmiFragment = new BmiFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.rel,
                    bmiFragment,
                    bmiFragment.getTag()).commit();*/

        } else if (id == R.id.nav_signout) {

            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, Parentslogin.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_moreapps) {

        } else if (id == R.id.nav_help) {
            Intent i = new Intent(this, HelpAndSupport.class);
            startActivity(i);

        } else if (id == R.id.nav_condition) {

        } else if (id == R.id.nav_privacy) {

        } else if (id == R.id.nav_about) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
