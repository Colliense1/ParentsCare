package com.example.colliensepodder.parentscare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by colliensepodder on 7/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Medicine_reminder";

    public static final int DATABASE_VERSION = 14;

    //TABLE1
    public static final String TABLE_MEDICINE_DETAILS = "table_medicine_details";

    public static final String MEDICINE_NAME = "medicine_name";
    public static final String MEDICINE_DURATION = "medicine_duration";
    public static final String MEDICINE_TYPE = "medicine_type";
    public static final String MEDICINE_PER_DAY = "medicine_per_day";
    public static final String CREATE_TABLE_MEDICINE_DETAILS_QUERY = "create table if not exists " + TABLE_MEDICINE_DETAILS
            + "(" + MEDICINE_NAME + " text, " + MEDICINE_DURATION + " integer, " + MEDICINE_TYPE + " text, " + MEDICINE_PER_DAY + " integer);";

    //TABLE2
    public static final String TABLE_MEDICINE_DATE_TIME = "table_medicine_date_time";

    public static final String MEDICINE_NAME_TABLE2 = "medicine_name2";
    public static final String MEDICINE_DATE = "medicine_date";
    public static final String MEDICINE_TIME = "medicine_time";
    public static final String MEDICINE_TAKEN_YES_OR_NO = "medicine_taken";
    public static final String CREATE_TABLE_MEDICINE_DATE_TIME_QUERY = "create table if not exists " + TABLE_MEDICINE_DATE_TIME
            + "(" + MEDICINE_NAME_TABLE2 + " text, "
            + MEDICINE_DATE + " text, " + MEDICINE_TIME + " text, " + MEDICINE_TAKEN_YES_OR_NO + " integer);";


    //TABLE3 for EMERGTENCY CONTACT
    public static final String TABLE_EMERGENCY_CONTACT = "table_emergency_contact";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_NUMBER = "contact_address";
    public static final String CONTACT_EMAIL = "email_address";

    public static final String CREATE_TABLE_EMERGENCY_CONTACT_QUERY = "create table if not exists " + TABLE_EMERGENCY_CONTACT
            + "(" + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text, "
            + CONTACT_NUMBER + " text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }
    //TABLE4 FOR DOCTOR
   public static final String DOCTOR_TABLE = "doctor_table";
   public static final String DOCTOR_NAME = "doctor_name";
    public static final String DOCTOR_NUMBER = "doctor_number";
    public static final String DOCTOR_EMAIL = "doctor_email";
    public static final String DOCTOR_SPECIALITY = "doctor_speciality";

   public static final String CREATE_DOCTOR_TABLE_NAME_QUERY = "create table if not exists " + DOCTOR_TABLE
           +"(" + DOCTOR_NAME + " text, "
           + DOCTOR_NUMBER + " text, "
           + DOCTOR_SPECIALITY + " text, "
            + DOCTOR_EMAIL + " text);";

   //TABLE5 FOR DIARY
   public static final String DIARY_TABLE = "table_diary";
   public static final String DIARY_TEXT = "diary_text";
   public static final String CREATE_DIARY_TABLE_NAME_QUERY = "create table if not exists " + DIARY_TABLE
           +"(" + DIARY_TEXT + " text);";

    //TABLE6 FOR APPOINTMENT
    public static final String APPOINTMENT_TABLE = "appointment_table";
    public static final String DOCTOR_APPOINTMENT_TITLE = "doctor_appointment_title";
    public static final String DOCTOR_APPOINTMENT_NAME = "doctor_appointment_name";
    public static final String DOCTOR_APPOINTMENT_LOCATION = "doctor_appointment_location";

    public static final String CREATE_DOCTOR_APPOINTMENT_TABLE_NAME_QUERY = "create table if not exists " + APPOINTMENT_TABLE
            +"(" + DOCTOR_APPOINTMENT_TITLE + " text, "
            + DOCTOR_APPOINTMENT_NAME + " text, "
            + DOCTOR_APPOINTMENT_LOCATION + " text);";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEDICINE_DETAILS_QUERY);
        db.execSQL(CREATE_TABLE_MEDICINE_DATE_TIME_QUERY);
        db.execSQL(CREATE_TABLE_EMERGENCY_CONTACT_QUERY);
        db.execSQL(CREATE_DOCTOR_TABLE_NAME_QUERY);
        db.execSQL(CREATE_DIARY_TABLE_NAME_QUERY);
        db.execSQL(CREATE_DOCTOR_APPOINTMENT_TABLE_NAME_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS " + TABLE_MEDICINE_DETAILS);
        db.execSQL("drop table if EXISTS " + TABLE_MEDICINE_DATE_TIME);
        db.execSQL("drop table if EXISTS " + TABLE_EMERGENCY_CONTACT);
        db.execSQL("drop table if EXISTS " + DOCTOR_TABLE);
        db.execSQL("drop table if EXISTS " + DIARY_TABLE);
        db.execSQL("drop table if EXISTS " + APPOINTMENT_TABLE);
        onCreate(db);
    }
}
