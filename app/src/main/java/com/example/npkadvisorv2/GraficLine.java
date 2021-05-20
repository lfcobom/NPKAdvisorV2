package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficLine extends AppCompatActivity {

    RadarChart radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_line);
        radarChart = findViewById(R.id.radarChart);
        Dataradar();
    }

    public void Dataradar() {
        ArrayList<RadarEntry> data = new ArrayList<>();
        ArrayList<RadarEntry> datan = new ArrayList<>();
        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        data.add(new RadarEntry(i, Float.parseFloat(indexResponse.get(i).getK().toString())));
                        datan.add(new RadarEntry(i, Float.parseFloat(indexResponse.get(i).getHumedad().toString())));
                    }

                    RadarDataSet radarDataSet = new RadarDataSet(data,"K %");
                    radarDataSet.setColor(Color.RED);
                    radarDataSet.setLineWidth(2f);
                    radarDataSet.setValueTextColor(Color.RED);
                    radarDataSet.setValueTextSize(14f);

                    RadarDataSet radarDataSet2 = new RadarDataSet(datan,"N %");
                    radarDataSet2.setColor(Color.BLUE);
                    radarDataSet2.setLineWidth(2f);
                    radarDataSet2.setValueTextColor(Color.BLUE);
                    radarDataSet2.setValueTextSize(14f);

                    RadarData radarData = new RadarData();
                    radarData.addDataSet(radarDataSet);
                    radarData.addDataSet(radarDataSet2);


                    String[] labels = {"2014","2015","2016","2017", "2018", "2019","2020"};
                    XAxis xAxis = radarChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

                    radarChart.getDescription().setText("Radar Chart Example");
                    radarChart.setData(radarData);
                    radarChart.invalidate();

                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficLine.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}