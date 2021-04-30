package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class RegInfo extends AppCompatActivity {
    Button pruebaGet;
    TextView Humedad;
    TextView N;
    TextView P;
    TextView K;
    TextView Ph;
    TextView Temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_info);
        pruebaGet = findViewById(R.id.pruebaget);
        Humedad = findViewById(R.id.pruebagetT);
        N = findViewById(R.id.n);
        P = findViewById(R.id.p);
        K = findViewById(R.id.k);
        Ph = findViewById(R.id.ph);
        Temp = findViewById(R.id.temp);
        pruebaGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Index();
            }
        });
    }

    public void Index() {
            Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex();
            indexResponseCall.enqueue(new Callback<IndexResponse>() {
                @Override
                public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                        for (int i = 0; i < IndexReponses.size(); i++) {
                            Humedad.setText((IndexReponses.get(i).getHumedad()).toString());
                            N.setText(IndexReponses.get(i).getN().toString());
                            P.setText((IndexReponses.get(i).getP()).toString());
                            K.setText((IndexReponses.get(i).getK()).toString());
                            Ph.setText((IndexReponses.get(i).getPh()).toString());
                            Temp.setText((IndexReponses.get(i).getTemp()).toString());
                        }

                    } else {
                        Toast.makeText(RegInfo.this, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<IndexResponse> call, Throwable t) {
                    Toast.makeText(RegInfo.this, "Request Failed", Toast.LENGTH_LONG).show();

                }
            });
        }
    }