package com.example.colliensepodder.parentscare;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import com.example.colliensepodder.parentscare.AlarmReceiver;
import com.example.colliensepodder.parentscare.Medicine;
import com.example.colliensepodder.parentscare.MedicinePerRow;
import com.example.colliensepodder.parentscare.MyService;
import com.example.colliensepodder.parentscare.MyRecyclerViewAdapter;
import com.example.colliensepodder.parentscare.R;
import com.example.colliensepodder.parentscare.MedicineManagementDatabase;
import com.example.colliensepodder.parentscare.MainFragment;
import  com.example.colliensepodder.parentscare.MainFragment.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import static com.example.colliensepodder.parentscare.MyRecyclerViewAdapter.MNAME;


public class Pillreminder extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, MyRecyclerViewAdapter.ItemClickListener {
    RecyclerView medicineRV;
    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
    TextView time;
    ArrayList<String> name;
    String medicineName = null;
    String dateFormat;
    LinearLayout noMedicine;
    ArrayList<String> medicine = new ArrayList<>();
    int day, month, year, hour, minute;
    boolean doubleBackToExitPressedOnce = false;
    final static int RQS_1 = 1;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;

   // MaterialBetterSpinner betterSpinner,bspinner;
    //Toolbar mtoolbar;
    //String [] SPINNERLIST = {"0","1","2","3","4","5","6","7","8","9"};
   // String [] spinnerLIST = {".00",".25",".50",".75"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pillreminder);

        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermission()) {
                requestPermission();
            }
        }

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        medicineRV = findViewById(R.id.MedicineRV);
        time = findViewById(R.id.Time);
        time.setVisibility(View.GONE);
        noMedicine = findViewById(R.id.NoMedicineLayout);
        noMedicine.setVisibility(View.GONE);
        setCalender();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Alarm();
    }

    public int getMonth(String date) {
        int str1 = date.indexOf("/");
        int str2 = date.lastIndexOf("/");
        String st1 = date.substring(0, str1);
        String st2 = date.substring(str1 + 1, str2);
        return Integer.parseInt(st2);
    }

    public int getDay(String date) {
        int str1 = date.indexOf("/");
        int str2 = date.lastIndexOf("/");
        String st1 = date.substring(0, str1);
        int ind = Integer.valueOf(st1);
        return ind;
    }

    public int getYear(String date) {
        int str1 = date.indexOf("/");
        int str2 = date.lastIndexOf("/");
        String st1 = date.substring(0, str1);
        String st2 = date.substring(str1 + 1, str2);
        String st3 = date.substring(str2 + 1, date.length());
        return Integer.parseInt(st3);
    }

    public int getHour1(String time) {
        int str1 = time.indexOf(":");
        String st1 = time.substring(0, str1);
        int h = Integer.parseInt(st1);
        return h;
    }

    public int getHour(String time) {
        int str1 = time.indexOf(":");
        String st1 = time.substring(0, str1);
        int h = Integer.parseInt(st1);
        // if (h >= 12) h = h % 12;
        return h;
    }

    public int getMin(String time) {
        int str1 = time.indexOf(":");
        String st2 = time.substring(str1 + 1, time.length());
        return Integer.parseInt(st2);
    }

    private void Alarm() {
        MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
        ArrayList<MedicinePerRow> row = obj.retriveAllDateTime();
        for (int i = 0; i < row.size(); ++i) {
            Calendar cal = Calendar.getInstance();
            Calendar current = Calendar.getInstance();
            cal.set(getYear(row.get(i).getMedicineTakenDate())
                    , getMonth(row.get(i).getMedicineTakenDate())
                    , getDay(row.get(i).getMedicineTakenDate())
                    , getHour(row.get(i).getMedicineTime())
                    , getMin(row.get(i).getMedicineTime()), 0);
            if (cal.compareTo(current) <= 0) {

            } else {
                setAlarm(cal);
            }
        }
    /*    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);*/
    }

    private void callMainFragment(String date, String mName) {
        Bundle bundle = new Bundle();
        bundle.putString("date", date);
        bundle.putString("name", mName);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        transaction.replace(R.id.MainFragmentLayout, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        AlarmReceiver MyService = new AlarmReceiver();
        Intent mServiceIntent = new Intent(getApplicationContext(), MyService.getClass());
        if (!isMyServiceRunning(MyService.getClass())) {
            startService(mServiceIntent);
        }
        super.onDestroy();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    private void setMedicineOnRv(ArrayList<String> mName, String NAME) {
        medicine.clear();
        MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
        ArrayList<Medicine> medicines = obj.retriveAllMedicineInfo();
        ArrayList<String> medicineName = new ArrayList<>();
        for (int i = 0; i < mName.size(); i++) {
            if (!medicineName.contains(mName.get(i).toString())) {
                medicineName.add(mName.get(i).toString());
            }
        }
        if (medicineName.size() == 0) {
            noMedicine.setVisibility(View.VISIBLE);
        } else {
            noMedicine.setVisibility(View.GONE);
        }

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(Pillreminder.this, LinearLayoutManager.HORIZONTAL, false);
        medicineRV.setLayoutManager(horizontalLayoutManagaer);
        for (int i = 0; i < medicines.size(); i++) {
            medicine.add(medicines.get(i).getMedicineName());
        }
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(Pillreminder.this, medicineName, NAME);
        medicineRV.setAdapter(adapter);
        medicineRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onRestart() {
        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        } else {
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);

            startActivity(intent);
            overridePendingTransition(0, 0);
        }
        super.onRestart();
    }

    private void setCalender() {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        final HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .datesNumberOnScreen(7)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                int dd = Integer.valueOf(DateFormat.format("dd", date).toString());
                int mm = Integer.valueOf(DateFormat.format("MM", date).toString()) - 1;
                int yy = Integer.valueOf(DateFormat.format("yyyy", date).toString());
                dateFormat = dd + "/" + mm + "/" + yy;

                MedicineManagementDatabase obj = new MedicineManagementDatabase(Pillreminder.this);
                name = obj.retriveMedicineNameByDate(dateFormat);

                if (medicineName == null && name.size() != 0) {
                    medicineName = name.get(0).toString();
                } else if (!name.contains(medicineName) && name.size() != 0)
                    medicineName = name.get(0).toString();
                callMainFragment(dateFormat, medicineName);
                setMedicineOnRv(name, medicineName);
            }
        });
    }

    private String getCurrrentTime() {
        String time = null;
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.AM_PM) == Calendar.AM) {
            time = "" + now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + " AM";

        } else {
            time = "" + now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + " PM";
        }
        return time;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

        } else if (item.getItemId() == R.id.MedicineReviewID) {
            Intent ii = new Intent(this, MedicinePreviewActivity.class);
            startActivity(ii);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setAlarm(Calendar targetCal) {

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        intentArray.add(pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onItemClick(View view, int position) {
        medicineName = name.get(position);
        callMainFragment(dateFormat, MNAME);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Pillreminder.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

    }
}


