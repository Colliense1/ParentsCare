package com.example.colliensepodder.parentscare;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;


public class BmiActivity extends AppCompatActivity {

    EditText editTextHeightFeet;
    EditText editTextHeightInches;
    EditText editTextWeightKg;
    TextView textViewBmiCalculate;
    TextView textViewBmiHeight;
    TextView textViewBmiWeight;
    android.widget.LinearLayout lLayoutCalculateBodymi;
    Button buttonCalculateBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
        editTextHeightInches = findViewById(R.id.editTextHeightInches);
        editTextWeightKg = findViewById(R.id.editTextWeightKg);
        textViewBmiCalculate = findViewById(R.id.textViewBmiCalculate);
        textViewBmiHeight = findViewById(R.id.textViewBmiHeight);
        textViewBmiWeight = findViewById(R.id.textViewBmiWeight);
        lLayoutCalculateBodymi = findViewById(R.id.lLayoutCalculateBodymi);


        editTextHeightFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] charSequenceArr = new CharSequence[]{AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8"};
                new AlertDialog.Builder(BmiActivity.this).setTitle((CharSequence) "Select height in feet").setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BmiActivity.this.editTextHeightFeet.setText(charSequenceArr[i]);
                    }
                }).create().show();

            }
        });

        editTextHeightInches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] charSequenceArr = new CharSequence[]{AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
                new AlertDialog.Builder(BmiActivity.this).setTitle((CharSequence) "Select height in inches").setItems(charSequenceArr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BmiActivity.this.editTextHeightInches.setText(charSequenceArr[i]);
                    }
                }).create().show();
            }
        });

        lLayoutCalculateBodymi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextWeightKg.getText().toString().equals("")) {
                    editTextWeightKg.setError("This field can't be empty");
                    return;
                } else {
                    editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
                    BMICalculator.Bmi bmi = BMICalculator.getBmi(BmiActivity.this.editTextHeightFeet.
                            getText().toString().trim(), BmiActivity.this.editTextHeightInches.
                            getText().toString().trim(), BmiActivity.this.editTextWeightKg.
                            getText().toString().trim());
                    View inflate = getLayoutInflater().inflate(R.layout.dialog_bmi_calculation_results, null);
                    final AlertDialog create = new AlertDialog.Builder(BmiActivity.this).setView(inflate).create();
                    ((TextView) inflate.findViewById(R.id.textViewBmiIndex)).setText(bmi.bmiIndex);
                    ((TextView) inflate.findViewById(R.id.textViewBmiStatus)).setText(bmi.healthStatusText);
                    TextView textView = (TextView) inflate.findViewById(R.id.textViewBmiIndex);
                    int i = bmi.healthStatusIndex;
                    int i2 = R.color.redLight;
                    textView.setTextColor(ContextCompat.getColor(BmiActivity.this, i == 1 ?
                            R.color.green :
                            R.color.redLight));
                    textView = (TextView) inflate.findViewById(R.id.textViewBmiStatus);
                    if (bmi.healthStatusIndex == 1) {
                        i2 = R.color.green;
                    }
                    textView.setTextColor(ContextCompat.getColor(BmiActivity.this, i2));
                    inflate.findViewById(R.id.textViewOkay).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            create.dismiss();
                        }
                    });
                    create.show();
                }
            }
        });
    }

    public static class BMICalculator {

        public static String[] HEALTH_STATUS = new String[]{"Underweight", "Healthy Weight", "Overweight"};

        public static class Bmi {
            public String bmiIndex;
            public int healthStatusIndex;
            public String healthStatusText;
        }

        public static Bmi getBmi(String height, String feet, String kg) {

            double height_feet = Double.parseDouble(height);
            double height_inch = Double.parseDouble(feet);
            double weight_kg = Double.parseDouble(kg);
            double height_total_inch = (height_feet * 12) + height_inch;
            double height_in_cm = (height_total_inch * 2.54);
            double bmi;
            bmi = ((weight_kg / (height_in_cm * height_in_cm)) * 10000);

            Bmi str23 = new Bmi();
            str23.bmiIndex = String.format("%.1f", new Object[]{Double.valueOf(bmi)});

            if (bmi < 18.5) {
                str23.healthStatusIndex = 0;
            } else if (bmi > 24.9) {
                str23.healthStatusIndex = 2;
            } else {
                str23.healthStatusIndex = 1;
            }
            str23.healthStatusText = HEALTH_STATUS[str23.healthStatusIndex];
            return str23;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }

}
