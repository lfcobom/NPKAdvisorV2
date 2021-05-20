package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficBar extends AppCompatActivity {

     BarChart bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_bar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bar = findViewById(R.id.barChart);
        Databar();
    }

    public void Databar() {
        ArrayList<BarEntry> data = new ArrayList<>();
        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        data.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getK().toString())));
                    }

                    BarDataSet barDataSet = new BarDataSet(data,"K %");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16f);

                    BarData barData = new BarData(barDataSet);
                    bar.setFitBars(true);
                    bar.setData(barData);
                    bar.getDescription().setText("Bar Chart Example");
                    bar.animateY(2000);
                    bar.invalidate();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficBar.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}