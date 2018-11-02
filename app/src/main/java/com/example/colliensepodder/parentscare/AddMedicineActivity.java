package com.example.colliensepodder.parentscare;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.parentscare.R;
import com.example.colliensepodder.parentscare.ScheduleRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMedicineActivity extends AppCompatActivity {
    Toolbar toolbar;
    Spinner scheduleSpinner;
    RecyclerView scheduleRV;
    TextView pickDateTV;
    EditText  durationET;
    AutoCompleteTextView medicineNameET;
    Button submitBtn;
    public static ArrayList<TakenTime> takenTime = new ArrayList<>();
    Spinner tabletOrLiquiedSpinner, tabletOrLiquiedQuantitySpinner;
    private int day, month, year, hour, minute;
    ArrayList<String> pickTimeList = new ArrayList<String>();
    private static final String[] medicines = new String[] {

            "Ace Plus®",
            "Ace®",
            "Acetram®",
            "Adryl®",
            "Afun®",
            "Afun® VT",
            "Alacot® DS Eye Drops",
            "Alacot® Eye Drops",
            "Alarid® Eye Drops",
            "Alatrol®",
            "Alice",
            "Almex®",
            "Ambrisan™ 5",
            "Ambrox®",
            "Amistar™ IM/IV Injection",
            "Amodis®",
            "Amodis® 500 IV",
            "Anadol®",
            "Anclog Plus®",
            "Anclog®",
            "Anema™",
            "Anema™",
            "Angilock®",
            "Angilock® plus",
            "Angivent MR®",
            "Anleptic®",
            "Anoxa 10®",
            "Anril 0.5®",
            "Anril® Injection",
            "Anril® spray",
            "Anril® spray",
            "Ansulin® Pen Cartridge",
            "Ansulin® Vial",
            "AnsuPen®",
            "Antazol®",
            "Antazol® Plus",
            "Antista®",
            "Antiva®",
            "Anzitor®",
            "Apsol®",
            "Ariprex™",
            "Asynta™",
            "AvasprayTM",
            "B-50 Forte®",
            "Bactrocin®",
            "Barif®",
            "Beclomin®",
            "Becospray®",
            "Benzapen®",
            "Beovit®",
            "Betameson-N®",
            "Bicozin-I®",
            "Bicozin®",
            "BimatorTM Eye Drops",
            "Bisocor®",
            "Bisocor™ Plus",
            "Bonizol™",
            "Brofex®",
            "Bromolac®",
            "Bufocort™ Cozycap",
            "Burna®",
            "Calbo 500®","Calbo Forte®","Calbo Jr.®","Calbo-C®","Calbo-D Vita","Calbo-D®","Calboplex®","Calboral-D™","Calborate™","Calcitrol®","Camlodin®","Camlodin® plus","Camlopril®","Camlosart™","Camoval®","Canaglif™","Candex®","Carbizol®","Carva®","Cavir®",
            "Ceevit Forte®","Ceevit®","Ceevit™ DS","Cef-3®","CefopenTM","Cefotil®","Cefotil™ Plus","Ceftiben™","Ceftron®","Cerevas™",
            "Cholenak™ IV","Cilosta®","Cinaron®","Cinaron® Plus","Ciprocin®",
            "Ciprocin® Eye/Ear Drops","Ciprocin® 200 IV","Climycin®","Clinface®","Clobam®",
            "Clofenac 100 Suppository®","Clofenac DT®","Clofenac Gel®","Clofenac® Plus Injection","Clofenac®",
            "Clopirox™ Cream","Clotinex™","Colicon®","Colimax™","Comet®",
            "Comprid™","Contifil®","Contilex®","Cotrim®",
            "De-rash®","Defiron™","Deflacort™","Delipid®","Depram®","Deprex®",
            "Dermasol-N®","Dermasol™-S","Dermasol®","Dexonex-C™ Eye/Ear Drops",
            "Dexonex®","Dexonex® Eye/Ear Drops","Dibenol®","Diliner® DR","Diltizem® SR 90",
            "Diprobet®","Dormitol®",
            "Duolax™","Durol®","Dyvon Plus®","Dyvon®"
    };
    private String[] M = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String mType;
    int mPerday;
    String startDate = "null";
    public static ArrayList<String> takenTimeperday = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        scheduleSpinner = findViewById(R.id.ScheduleSpinner);
        scheduleRV = findViewById(R.id.schedueRecyclerView);
        pickDateTV = findViewById(R.id.PickDateTv);
        tabletOrLiquiedSpinner = findViewById(R.id.TabletOrLiquiedSpinner);
        tabletOrLiquiedQuantitySpinner = findViewById(R.id.TabletOrLiquiedQuantitySpinner);
        medicineNameET = findViewById(R.id.MedicineNameET);
        durationET = findViewById(R.id.DurationET);
        submitBtn = findViewById(R.id.SubmitBtn);
        ArrayAdapter<String> medicine = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, medicines);
        medicineNameET.setAdapter(medicine);
        setClickListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setClickListener() {
        final SpinnerAdapter adapter1 = ArrayAdapter.createFromResource(AddMedicineActivity.this, R.array.tablet_arrays, android.R.layout.simple_spinner_dropdown_item);
        final SpinnerAdapter adapter2 = ArrayAdapter.createFromResource(AddMedicineActivity.this, R.array.liquied_arrays, android.R.layout.simple_spinner_dropdown_item);
        tabletOrLiquiedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) tabletOrLiquiedQuantitySpinner.setAdapter(adapter1);
                else tabletOrLiquiedQuantitySpinner.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        tabletOrLiquiedQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPerday = position + 1;
                pickTimeList.clear();
                takenTime.clear();
                for (int i = 1; i <= position + 1; i++) {
                    pickTimeList.add("  Pick time");
                }
                LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(AddMedicineActivity.this, LinearLayoutManager.VERTICAL, false);
                scheduleRV.setLayoutManager(horizontalLayoutManagaer);
                ScheduleRecyclerViewAdapter adapter = new ScheduleRecyclerViewAdapter(AddMedicineActivity.this, pickTimeList);
                scheduleRV.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pickDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddMedicineActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        startDate = i2 + "/" + i1 + "/" + i;
                        pickDateTV.setText(M[i1] + " " + i2 + "," + i);
                        pickDateTV.setTextColor(Color.BLACK);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void clickOnSubmit(View view) {
        String medicineName = medicineNameET.getText().toString();

        int duration = Integer.parseInt(durationET.getText().toString());
        String medicineType = mType;
        int medicinePerday = mPerday;
        String startingDate = startDate;
        ArrayList<String> takeTime = new ArrayList<>();
        for (int i = 0; i < takenTime.size(); i++) {
            takeTime.add(takenTime.get(i).getHour() + ":" + takenTime.get(i).getMinute());
        }
        int takenYesorNo = 0;
        if (medicineName.equals("")) {
            Toast.makeText(this, "Please Enter Medicine Name", Toast.LENGTH_SHORT).show();
        } else if (medicinePerday != takeTime.size()) {
            Toast.makeText(this, "Please Enter Schedule Time", Toast.LENGTH_SHORT).show();
        } else if (startingDate.contains("null")) {
            Toast.makeText(this, "Please Enter date", Toast.LENGTH_SHORT).show();
        } else if (duration == 0) {
            Toast.makeText(this, "Please Enter duration time", Toast.LENGTH_SHORT).show();
        } else {
            Medicine medicine = new Medicine(medicineName, duration, medicineType, medicinePerday, startingDate, takeTime, takenYesorNo);
            MedicineManagementDatabase obj = new MedicineManagementDatabase(this);
            obj.addMedicineDetails(medicine);
            obj.addMedicineDateTime(medicine);
            finish();
            Toast.makeText(this, "Medicine added succesfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void closeAddMedicineActivity(View view) {
        finish();
    }

    public void negativeClick(View view) {
        if (durationET.getText().toString().equals("")) durationET.setText("0");
        int duration = Integer.parseInt(durationET.getText().toString());
        if (duration > 0) {
            duration--;
            durationET.setText("" + duration);
        }
    }

    public void posotiveClick(View view) {
        if (durationET.getText().toString().equals("")) durationET.setText("0");
        int duration = Integer.parseInt(durationET.getText().toString());
        if (duration >= 0) {
            duration++;
            durationET.setText("" + duration);
        }
    }
}
