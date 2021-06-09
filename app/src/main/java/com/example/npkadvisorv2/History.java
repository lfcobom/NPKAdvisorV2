package com.example.npkadvisorv2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

//https://www.youtube.com/watch?v=y3exATaC0kA
public class History extends AppCompatActivity {
    ImageButton ChooseDate;
    ImageButton ChooseDateHasta;
    Button Saveb;
    TextView ChooseT1;
    TextView ChooseT;
    TableRow fila;
    TextView Humedad;
    TextView N;
    TextView P;
    TextView K;
    TextView Ph;
    TextView Temp;
    TextView Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ChooseDate = findViewById(R.id.choosedate);
        ChooseDateHasta = findViewById(R.id.choosedate1);
        ChooseT = findViewById(R.id.chooseText);
        ChooseT1 = findViewById(R.id.choosedateT1);
        Saveb = findViewById(R.id.saveDate);

        Saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Save();
            }
        });
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
    public void Save(){
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layouthumedad = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutN = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutK = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutPh = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutTemp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutDate = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    TableLayout lista = findViewById(R.id.table);
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                        for (int i = 0; i < IndexReponses.size(); i++) {
                            Log.d(TAG, "onResponse: \n " +
                                    "Cultivo " + IndexReponses.get(i).getHumedad());
                            //  String[] items = new String[]{IndexReponses.get(i).getPh().toString()};
                            fila = new TableRow(History.this);
                            fila.setLayoutParams(layoutFila);
                            if (i == 0) {
                                Date = new TextView(History.this);
                                Date.setText("FECHA");
                                Date.setGravity(Gravity.CENTER);
                                Date.setBackgroundColor(Color.BLACK);
                                Date.setTextColor(Color.WHITE);
                                Date.setPadding(10, 10, 10, 10);
                                Date.setLayoutParams(layoutDate);
                                fila.addView(Date);

                                Humedad = new TextView(History.this);
                                Humedad.setText("HUMEDAD");
                                Humedad.setGravity(Gravity.CENTER);
                                Humedad.setBackgroundColor(Color.BLACK);
                                Humedad.setTextColor(Color.WHITE);
                                Humedad.setPadding(10, 10, 10, 10);
                                Humedad.setLayoutParams(layouthumedad);
                                fila.addView(Humedad);

                                N = new TextView(History.this);
                                N.setText("N");
                                N.setGravity(Gravity.CENTER);
                                N.setBackgroundColor(Color.BLACK);
                                N.setTextColor(Color.WHITE);
                                N.setPadding(10, 10, 10, 10);
                                N.setLayoutParams(layoutN);
                                fila.addView(N);

                                P = new TextView(History.this);
                                P.setText("P");
                                P.setGravity(Gravity.CENTER);
                                P.setBackgroundColor(Color.BLACK);
                                P.setTextColor(Color.WHITE);
                                P.setPadding(10, 10, 10, 10);
                                P.setLayoutParams(layoutP);
                                fila.addView(P);

                                K = new TextView(History.this);
                                K.setText("K");
                                K.setGravity(Gravity.CENTER);
                                K.setBackgroundColor(Color.BLACK);
                                K.setTextColor(Color.WHITE);
                                K.setPadding(10, 10, 10, 10);
                                K.setLayoutParams(layoutK);
                                fila.addView(K);

                                Ph = new TextView(History.this);
                                Ph.setText("Ph");
                                Ph.setGravity(Gravity.CENTER);
                                Ph.setBackgroundColor(Color.BLACK);
                                Ph.setTextColor(Color.WHITE);
                                Ph.setPadding(10, 10, 10, 10);
                                Ph.setLayoutParams(layoutPh);
                                fila.addView(Ph);

                                Temp = new TextView(History.this);
                                Temp.setText("T°");
                                Temp.setGravity(Gravity.CENTER);
                                Temp.setBackgroundColor(Color.BLACK);
                                Temp.setTextColor(Color.WHITE);
                                Temp.setPadding(10, 10, 10, 10);
                                Temp.setLayoutParams(layoutTemp);
                                fila.addView(Temp);
                                lista.addView(fila);
                            } else {
                               Date = new TextView(History.this);
                               Date.setText(IndexReponses.get(i).getCreateAt());
                               Date.setPadding(10, 10, 10, 10);
                               Date.setGravity(Gravity.CENTER);
                               Date.setBackgroundColor(Color.BLUE);
                               Date.setLayoutParams(layoutDate);
                               fila.addView(Date);

                                Humedad = new TextView(History.this);
                                Humedad.setText(IndexReponses.get(i).getHumedad().toString());
                                Humedad.setPadding(10, 10, 10, 10);
                                Humedad.setGravity(Gravity.CENTER);
                                Humedad.setBackgroundColor(Color.YELLOW);
                                Humedad.setLayoutParams(layouthumedad);
                                fila.addView(Humedad);

                                N = new TextView(History.this);
                                N.setText(IndexReponses.get(i).getN().toString());
                                N.setPadding(10, 10, 10, 10);
                                N.setGravity(Gravity.CENTER);
                                N.setBackgroundColor(Color.RED);
                                N.setLayoutParams(layoutN);
                                fila.addView(N);

                                P = new TextView(History.this);
                                P.setText(IndexReponses.get(i).getP().toString());
                                P.setPadding(10, 10, 10, 10);
                                P.setGravity(Gravity.CENTER);
                                P.setBackgroundColor(Color.GRAY);
                                P.setLayoutParams(layoutP);
                                fila.addView(P);

                                K = new TextView(History.this);
                                K.setText(IndexReponses.get(i).getK().toString());
                                K.setPadding(10, 10, 10, 10);
                                K.setGravity(Gravity.CENTER);
                                K.setBackgroundColor(Color.GREEN);
                                K.setLayoutParams(layoutK);
                                fila.addView(K);

                                Ph = new TextView(History.this);
                                Ph.setText(IndexReponses.get(i).getPh().toString());
                                Ph.setPadding(10, 10, 10, 10);
                                Ph.setGravity(Gravity.CENTER);
                                Ph.setBackgroundColor(Color.MAGENTA);
                                Ph.setLayoutParams(layoutPh);
                                fila.addView(Ph);

                                Temp = new TextView(History.this);
                                Temp.setText(IndexReponses.get(i).getTemp().toString());
                                Temp.setPadding(10, 10, 10, 10);
                                Temp.setGravity(Gravity.CENTER);
                                Temp.setBackgroundColor(Color.WHITE);
                                Temp.setLayoutParams(layoutTemp);
                                fila.addView(Temp);
                                lista.addView(fila);
                            }
                        }
                } else {
                    Toast.makeText(History.this, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(History.this, "Request Failed", Toast.LENGTH_LONG).show();

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
