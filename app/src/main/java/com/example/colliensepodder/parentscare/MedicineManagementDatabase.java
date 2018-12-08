package com.example.colliensepodder.parentscare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by colliensepodder on 7/30/2018.
 */


public class MedicineManagementDatabase {
    DatabaseHelper databaseHelper;
    long insertedRow1;
    long insertedRow2;
    Context context;
    ArrayList<MedicinePerRow> MedicineRow = new ArrayList<>();
    ArrayList<String> MedicineName = new ArrayList<>();

    public MedicineManagementDatabase(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public long addMedicineDetails(Medicine medicine) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEDICINE_NAME, medicine.getMedicineName());
        contentValues.put(DatabaseHelper.MEDICINE_DURATION, medicine.getMedicineDuration());
        contentValues.put(DatabaseHelper.MEDICINE_TYPE, medicine.getMedicineType());
        contentValues.put(DatabaseHelper.MEDICINE_PER_DAY, medicine.getMedicinePerday());
        insertedRow1 = sqLiteDatabase.insert(DatabaseHelper.TABLE_MEDICINE_DETAILS, null, contentValues);

        return 0;
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

    public ArrayList<MedicinePerRow> retriveMedicineByDate(String date, String medicine) {
        MedicineRow.clear();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "Select * from " + DatabaseHelper.TABLE_MEDICINE_DATE_TIME + " where " + databaseHelper.MEDICINE_DATE + " = ? and "
                + databaseHelper.MEDICINE_NAME_TABLE2 + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, new String[]{date, medicine}, null);
        if (cursor.moveToFirst()) {
            do {

                String mName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_NAME_TABLE2));
                String mDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_DATE));
                String mTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TIME));
                int takenYesOrNo = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TAKEN_YES_OR_NO)));
                // int takenYesOrNo = 0 ;
                MedicineRow.add(new MedicinePerRow(mName, mDate, mTime, takenYesOrNo));
            } while (cursor.moveToNext());
        }
        return MedicineRow;
    }

    public ArrayList<String> retriveMedicineNameByDate(String date) {
        MedicineName.clear();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "Select * from " + DatabaseHelper.TABLE_MEDICINE_DATE_TIME + " where " + databaseHelper.MEDICINE_DATE + " = ? ";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, new String[]{date}, null);
        if (cursor.moveToFirst()) {
            do {

                String mName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_NAME_TABLE2));

                //int takenYesOrNo = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TAKEN_YES_OR_NO)));
                // int takenYesOrNo = 0 ;
                MedicineName.add(new String(mName));
            } while (cursor.moveToNext());
        }
        return MedicineName;
    }

    public ArrayList<Contact> retriveAllEmergencyContact() {
        ArrayList<Contact> row = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery1 = "Select * from " + DatabaseHelper.TABLE_EMERGENCY_CONTACT;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery1, null);
        if (cursor.moveToFirst()) {
            do {
                String mContactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME));
                String mContactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NUMBER));
                String mContactEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_EMAIL));

                // int takenYesOrNo = 0 ;
                row.add(new Contact(mContactName, mContactNumber, mContactEmail));
            } while (cursor.moveToNext());
        }
        return row;
    }


    public ArrayList<Diary> retriveAllDiary() {
        ArrayList<Diary> row = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
            String selectQuery1 = "Select * from " + DatabaseHelper.DIARY_TABLE;
            Cursor cursor = sqLiteDatabase.rawQuery(selectQuery1, null);
            if (cursor.moveToFirst()) {
                do {
                    String mDiarytext = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIARY_TEXT));
                    // int takenYesOrNo = 0 ;
                    row.add(new Diary(mDiarytext));
                } while (cursor.moveToNext());

            }

        } catch (Exception e) {

        }
        return row;
    }


    public ArrayList<MedicinePerRow> retriveAllDateTime() {
        ArrayList<MedicinePerRow> row = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "Select * from " + DatabaseHelper.TABLE_MEDICINE_DATE_TIME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                String mName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_NAME_TABLE2));
                String mDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_DATE));
                String mTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TIME));

                int takenYesOrNo = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TAKEN_YES_OR_NO)));
                // int takenYesOrNo = 0 ;
                row.add(new MedicinePerRow(mName, mDate, mTime, takenYesOrNo));
            } while (cursor.moveToNext());
        }
        return row;
    }

    public long update(String mName, String medicineType, String oldMedicineName) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEDICINE_NAME, mName);
        contentValues.put(DatabaseHelper.MEDICINE_TYPE, medicineType);
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.MEDICINE_NAME_TABLE2, mName);
        long update1 = sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DETAILS,
                contentValues, databaseHelper.MEDICINE_NAME + " =? ",
                new String[]{oldMedicineName});
        long update2 = sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DATE_TIME,
                contentValue, databaseHelper.MEDICINE_NAME_TABLE2 + " =? ",
                new String[]{oldMedicineName});
        return update2;
    }

    public long updateAllTime(String newTime, String oldTime, String medicineName) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEDICINE_TIME, newTime);
        // long update1=sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DETAILS,contentValues,databaseHelper.MEDICINE_NAME+" =? " ,new String[]{oldMedicineName});
        long update2 = sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DATE_TIME, contentValues, databaseHelper.MEDICINE_NAME_TABLE2 + " =? and " + databaseHelper.MEDICINE_TIME + " =? ", new String[]{medicineName, oldTime});
        return update2;
    }

    public long updateTime(String newTime, String oldTime, String medicineName, String date) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEDICINE_TIME, newTime);
        // long update1=sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DETAILS,contentValues,databaseHelper.MEDICINE_NAME+" =? " ,new String[]{oldMedicineName});
        long update2 = sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DATE_TIME, contentValues, databaseHelper.MEDICINE_NAME_TABLE2 + " =? and " + databaseHelper.MEDICINE_TIME + " =? and " + databaseHelper.MEDICINE_DATE + " =? ", new String[]{medicineName, oldTime, date});
        return update2;
    }

    public long deleteMedicine(String name) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long delete = sqLiteDatabase.delete(DatabaseHelper.TABLE_MEDICINE_DETAILS, databaseHelper.MEDICINE_NAME + " =? ", new String[]{name});
        long delete2 = sqLiteDatabase.delete(DatabaseHelper.TABLE_MEDICINE_DATE_TIME, databaseHelper.MEDICINE_NAME_TABLE2 + " =? ", new String[]{name});
        return delete2;
    }

    //DELETE EMERGENCY CONTACT
    public long deleteContact(String name) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long delete = sqLiteDatabase.delete(DatabaseHelper.TABLE_EMERGENCY_CONTACT, databaseHelper.CONTACT_NUMBER + " =? ", new String[]{name});

        return delete;
    }


    public ArrayList<Medicine> retriveAllMedicineInfo() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "Select * from " + DatabaseHelper.TABLE_MEDICINE_DETAILS;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String mName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_NAME));
                int mDuration = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_DURATION)));
                String mType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_TYPE));
                int mPerday = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDICINE_PER_DAY)));
                medicines.add(new Medicine(mName, mDuration, mType, mPerday));
            } while (cursor.moveToNext());
        }
        return medicines;
    }

    public long updateTakenOrNotTaken(String mName, String date, String time, int taken) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEDICINE_TAKEN_YES_OR_NO, taken);
        long update1 = sqLiteDatabase.update(DatabaseHelper.TABLE_MEDICINE_DATE_TIME, contentValues, databaseHelper.MEDICINE_NAME_TABLE2 + " =? and " + databaseHelper.MEDICINE_DATE + " =? and " + databaseHelper.MEDICINE_TIME + " =? ", new String[]{mName, date, time});
        return update1;
    }

    public long addMedicineDateTime(Medicine medicine) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String startDate = medicine.getMedicineStartDate();
        Calendar thatDay = Calendar.getInstance();
        int d = getDay(startDate);
        int m = getMonth(startDate);
        int y = getYear(startDate);
        thatDay.set(Calendar.DAY_OF_MONTH, getDay(startDate));
        thatDay.set(Calendar.MONTH, getMonth(startDate));
        thatDay.set(Calendar.YEAR, getYear(startDate));
        thatDay.add(Calendar.DAY_OF_MONTH, -1);

        ArrayList<String> time = medicine.getMedicineTime();
        for (int i = 0; i < medicine.getMedicineDuration(); i++) {
            thatDay.add(Calendar.DAY_OF_MONTH, 1);
            int dd = thatDay.get(Calendar.DAY_OF_MONTH);
            int mm = thatDay.get(Calendar.MONTH);
            int yy = thatDay.get(Calendar.YEAR);
            String thatday = dd + "/" + mm + "/" + yy;
            for (int j = 0; j < time.size(); j++) {
                SQLiteDatabase sqLiteDatabas = databaseHelper.getWritableDatabase();
                ContentValues contentValue = new ContentValues();
                contentValue.put(DatabaseHelper.MEDICINE_NAME_TABLE2, medicine.getMedicineName());
                contentValue.put(DatabaseHelper.MEDICINE_DATE, thatday);
                contentValue.put(DatabaseHelper.MEDICINE_TIME, time.get(j));
                contentValue.put(DatabaseHelper.MEDICINE_TAKEN_YES_OR_NO, medicine.getMedicineTakenYesOrNo());
                insertedRow2 = sqLiteDatabas.insert(DatabaseHelper.TABLE_MEDICINE_DATE_TIME, null, contentValue);
            }
        }
        return 0;
    }

    //ALL EMERGENCY CONTACT DATA
    public long addEmergencyContact(Contact contact) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_NAME, contact.getContactName());
        contentValues.put(DatabaseHelper.CONTACT_NUMBER, contact.getContactNumber());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL, contact.getContactEmail());

        insertedRow1 = sqLiteDatabase.insert(DatabaseHelper.TABLE_EMERGENCY_CONTACT, null, contentValues);

        return insertedRow1;
    }

    public long updateEmergencyContact(Contact newContact, Contact updatedcontact) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_NAME, newContact.getContactName());
        contentValues.put(DatabaseHelper.CONTACT_NUMBER, newContact.getContactNumber());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL, newContact.getContactEmail());
        long update1 = sqLiteDatabase.update(DatabaseHelper.TABLE_EMERGENCY_CONTACT, contentValues,
                databaseHelper.CONTACT_NAME + " =? and "
                        + databaseHelper.CONTACT_NUMBER + " =? ",
                new String[]{updatedcontact.getContactName(),
                        updatedcontact.getContactNumber()});
        return update1;

    }

    //DOCTORS ALL DATA
    public ArrayList<Doctorinfo> retriveAllDoctor() {
        ArrayList<Doctorinfo> row = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
            String selectQuery1 = "Select * from " + DatabaseHelper.DOCTOR_TABLE;
            Cursor cursor = sqLiteDatabase.rawQuery(selectQuery1, null);
            if (cursor.moveToFirst()) {
                do {
                    String mDoctorName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_NAME));
                    String mDoctorNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_NUMBER));
                    String mDoctorEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_EMAIL));
                    String mDoctorSpeciality = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_SPECIALITY));
                    String mDoctorGender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_GENDER));

                    // int takenYesOrNo = 0 ;
                    row.add(new Doctorinfo(mDoctorName, mDoctorNumber, mDoctorEmail, mDoctorSpeciality,mDoctorGender));

                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {

        }
        return row;
    }

    public long addDoctor(Doctorinfo doctorinfos) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DOCTOR_NAME, doctorinfos.getDoctorName());
        contentValues.put(DatabaseHelper.DOCTOR_NUMBER, doctorinfos.getDoctorNumber());
        contentValues.put(DatabaseHelper.DOCTOR_EMAIL, doctorinfos.getDoctorEmail());
        contentValues.put(DatabaseHelper.DOCTOR_SPECIALITY, doctorinfos.getSpeciality());
        contentValues.put(DatabaseHelper.DOCTOR_GENDER, doctorinfos.getAvatar());

        long inserted = sqLiteDatabase.insert(DatabaseHelper.DOCTOR_TABLE, null, contentValues);

        return inserted;
    }

    public long updateDoctor(Doctorinfo newdoctorinfo, Doctorinfo updateddoctor) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DOCTOR_NAME, newdoctorinfo.getDoctorName());
        contentValues.put(DatabaseHelper.DOCTOR_NUMBER, newdoctorinfo.getDoctorNumber());
        contentValues.put(DatabaseHelper.DOCTOR_EMAIL, newdoctorinfo.getDoctorEmail());
        contentValues.put(DatabaseHelper.DOCTOR_SPECIALITY, newdoctorinfo.getSpeciality());


        long update1 = sqLiteDatabase.update(DatabaseHelper.DOCTOR_TABLE, contentValues,
                databaseHelper.DOCTOR_NAME + " =? and "
                        + databaseHelper.DOCTOR_NUMBER + " =? and "
                        + databaseHelper.DOCTOR_EMAIL + " =? and "
                        + databaseHelper.DOCTOR_SPECIALITY + " =? ",
                new String[]{updateddoctor.getDoctorName(),
                        updateddoctor.getDoctorNumber(),
                        updateddoctor.getDoctorEmail(),
                        updateddoctor.getSpeciality()});
        return update1;

    }

    //DELETE DOCTOR
    public long deleteDoctor(String name) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long delete = sqLiteDatabase.delete(DatabaseHelper.DOCTOR_TABLE, databaseHelper.DOCTOR_NUMBER + " =? ", new String[]{name});

        return delete;
    }


    //ALL DIARY DATA
    public long addDiary(Diary diary) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DIARY_TEXT, diary.getDiaryText());

        insertedRow1 = sqLiteDatabase.insert(DatabaseHelper.DIARY_TABLE, null, contentValues);

        return insertedRow1;
    }

    public long updateDiary(Diary newdiaries, Diary updateddiaries) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DIARY_TEXT, newdiaries.getDiaryText());

        long update1 = sqLiteDatabase.update(DatabaseHelper.DIARY_TABLE,
                contentValues, databaseHelper.DIARY_TEXT + " =? ",
                //+ databaseHelper.DIARY_TEXT + " =? ",
                new String[]{updateddiaries.getDiaryText()});
        return update1;

    }

    //DELETE DIARY
    public long deleteDiary(String name) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long delete = sqLiteDatabase.delete(DatabaseHelper.DIARY_TABLE, databaseHelper.DIARY_TEXT + " =? ", new String[]{name});

        return delete;
    }


    //DOCTORS APPOINTMENT ALL DATA
    public ArrayList<AppointmentInfo> retriveAllDoctorAppointment() {
        ArrayList<AppointmentInfo> row = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
            String selectQuery1 = "Select * from " + DatabaseHelper.APPOINTMENT_TABLE;
            Cursor cursor = sqLiteDatabase.rawQuery(selectQuery1, null);
            if (cursor.moveToFirst()) {
                do {
                    String mDoctorAppointmentTitle= cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_APPOINTMENT_TITLE));
                    String mDoctorAppointmentName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_APPOINTMENT_NAME));
                    String mDoctorAppointmentLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_APPOINTMENT_LOCATION));

                    // int takenYesOrNo = 0 ;
                    row.add(new AppointmentInfo(mDoctorAppointmentTitle, mDoctorAppointmentName, mDoctorAppointmentLocation));

                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {

        }
        return row;
    }

    public long addDoctorAppointment(AppointmentInfo appointmentInfo) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_TITLE, appointmentInfo.getDoctorAppointmentTitle());
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_NAME, appointmentInfo.getDoctorAppointmentName());
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_LOCATION, appointmentInfo.getDoctorAppointmentLocation());

        long inserted = sqLiteDatabase.insert(DatabaseHelper.APPOINTMENT_TABLE, null, contentValues);

        return inserted;
    }

    public long updateDoctorAppointment(AppointmentInfo newappointmentinfo, AppointmentInfo updateddoctorappointment) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_TITLE, newappointmentinfo.getDoctorAppointmentTitle());
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_NAME, newappointmentinfo.getDoctorAppointmentName());
        contentValues.put(DatabaseHelper.DOCTOR_APPOINTMENT_LOCATION, newappointmentinfo.getDoctorAppointmentLocation());

        long update1 = sqLiteDatabase.update(DatabaseHelper.APPOINTMENT_TABLE, contentValues,
                databaseHelper.DOCTOR_APPOINTMENT_TITLE + " =? and "
                        + databaseHelper.DOCTOR_APPOINTMENT_NAME + " =? and "
                        + databaseHelper.DOCTOR_APPOINTMENT_LOCATION + " =? ",
                new String[]{updateddoctorappointment.getDoctorAppointmentTitle(),
                        updateddoctorappointment.getDoctorAppointmentName(),
                        updateddoctorappointment.getDoctorAppointmentLocation()});
        return update1;

    }

    //DELETE DOCTORAPPOINTMENT
    public long deleteDoctorAppointment(String name) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long delete = sqLiteDatabase.delete(DatabaseHelper.APPOINTMENT_TABLE, databaseHelper.DOCTOR_APPOINTMENT_TITLE + " =? ", new String[]{name});

        return delete;
    }

}
