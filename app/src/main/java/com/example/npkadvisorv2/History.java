package com.example.npkadvisorv2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
//https://www.youtube.com/watch?v=y3exATaC0kA
public class History extends AppCompatActivity {
    ImageButton ChooseDate;
    ImageButton ChooseDateHasta;
    TextView ChooseT1;
    TextView ChooseT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ChooseDate = findViewById(R.id.choosedate);
        ChooseDateHasta = findViewById(R.id.choosedate1);
        ChooseT = findViewById(R.id.chooseText);
        ChooseT1 = findViewById(R.id.choosedateT1);
        ChooseDateHasta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate1();
            }
        });
        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ChooseDate(){
        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH);
        int year = rightNow.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ChooseT.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);

            }
        }
        ,day,month,year);
        datePickerDialog.show();
    }

    public void ChooseDate1(){
        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int day1 = rightNow.get(Calendar.DAY_OF_MONTH);
        int month1 = rightNow.get(Calendar.MONTH);
        int year1 = rightNow.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ChooseT1.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);
            }
        }
                ,day1,month1,year1);
        datePickerDialog.show();
    }
}
