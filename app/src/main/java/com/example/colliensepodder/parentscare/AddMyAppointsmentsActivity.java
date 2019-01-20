package com.example.colliensepodder.parentscare;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.colliensepodder.parentscare.AppointMentTime.TimePickerFragment;
import com.example.colliensepodder.parentscare.MoreNavigation.Activity.AlarmReceiverd;

import java.text.DateFormat;
import java.util.Calendar;

import static com.example.colliensepodder.parentscare.DoctorAdopter.updateddoctor;
import static com.example.colliensepodder.parentscare.MyAppointmentAdopter.updateddoctorappointment;

public class AddMyAppointsmentsActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    EditText editMyAppointmentTitle;
    EditText editMyAppointmentName;
    EditText editTimeAppointmentPicker;
    EditText editTextLocation;
    Button textViewDone;
    String mode;

    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_appointsments);

        editMyAppointmentTitle = findViewById(R.id.editMyAppointmentTitle);
        editMyAppointmentName = findViewById(R.id.editMyAppointmentName);
        editTimeAppointmentPicker = findViewById(R.id.editTimeAppointmentPicker);
        editTextLocation = findViewById(R.id.editTextLocation);
        textViewDone = findViewById(R.id.textViewDone);

        editTimeAppointmentPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.show(getFragmentManager(),"time_picker");
            }
        });

        editMyAppointmentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMyAppointsmentsActivity.this, SelectDoctorActivity.class);
                startActivityForResult(i, 10);
                //startActivity(i);
            }
        });

        textViewDone = findViewById(R.id.textViewDone);
        mode = getIntent().getStringExtra("mode");
        if (mode.equals("1")) {
            textViewDone.setText("Add");

        } else {
            editMyAppointmentTitle.setText(updateddoctorappointment.getDoctorAppointmentTitle());
            editMyAppointmentName.setText(updateddoctorappointment.getDoctorAppointmentName());
            editTextLocation.setText(updateddoctorappointment.getDoctorAppointmentLocation());
            textViewDone.setText("Update");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("name");
                editMyAppointmentName.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public void clickAddAppointments(View view) {

        if (editMyAppointmentTitle.getText().toString().equals("")) {
            editMyAppointmentTitle.setError("This field can't be empty");
            return;
        } else if (editMyAppointmentName.getText().toString().equals("")) {
            editMyAppointmentName.setError("This field can't be empty");
            return;
        } else if (editTextLocation.getText().toString().equals("")) {
            editTextLocation.setError("This field can't be empty");
            return;
        } else {

            if (mode.equals("1")) {
                AppointmentInfo newappointment = new AppointmentInfo
                        (editMyAppointmentTitle.getText().toString(),
                                editMyAppointmentName.getText().toString(),
                                editTextLocation.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.addDoctorAppointment(newappointment);
                Toast.makeText(getApplicationContext(), "Appointment Added succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                AppointmentInfo newappointment = new AppointmentInfo
                        (editMyAppointmentTitle.getText().toString(),
                                editMyAppointmentName.getText().toString(),
                                editTextLocation.getText().toString());
                MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
                long g = obj.updateDoctorAppointment(newappointment, updateddoctorappointment);
                Toast.makeText(getApplicationContext(), "Appointment Updated succesfully " + g, Toast.LENGTH_SHORT).show();
                this.finish();

            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

       /* yearFinal = year;
        monthFinal = year+1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMyAppointsmentsActivity.this,
                AddMyAppointsmentsActivity.this,hour,minute, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();*/
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        //long alatmStarttime = c.getTimeInMillis();
        updateTimeText(c);
        startAlarm(c);

    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiverd.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    private void updateTimeText(Calendar c) {
        String timeText ="";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        editTimeAppointmentPicker.setText(timeText);
    }
}
