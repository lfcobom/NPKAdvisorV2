package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficPie extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_pie);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pieChart = findViewById(R.id.pieChart);
        Datapie();
    }

    public void Datapie() {
        ArrayList<PieEntry> data = new ArrayList<>();
        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        data.add(new PieEntry(i, Float.parseFloat(indexResponse.get(i).getK().toString())));
                    }

                    PieDataSet pieDataSet = new PieDataSet(data,"K %");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    pieDataSet.setValueTextColor(Color.BLACK);
                    pieDataSet.setValueTextSize(16f);

                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.animate();
                    pieChart.invalidate();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficPie.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}