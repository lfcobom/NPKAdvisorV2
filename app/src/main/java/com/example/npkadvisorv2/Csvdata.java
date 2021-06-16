package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Csvdata extends AppCompatActivity {

    Button csvExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvdata);
        RequestPermissions();
        datalist();
        csvExport = findViewById(R.id.csv);
        csvExport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datalist();
                humedadlist();
            }
        });

    }

    public void RequestPermissions(){

        if(ContextCompat.checkSelfPermission(Csvdata.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Csvdata.this, new String []{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }
    public void datalist(){
        File file = new File(Environment.getExternalStorageDirectory() + "/CSV");
        String archivo = file.toString()+"/"+"NPK.csv";

        boolean isCreate = false;
        if(!file.exists()){
           isCreate = file.mkdir();
        }
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
        ArrayList<String> data = new ArrayList<>();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                    try {
                        FileWriter fileWriter = new FileWriter(archivo);
                        fileWriter.write("N");
                        fileWriter.write(",");
                        fileWriter.write("P");
                        fileWriter.write(",");
                        fileWriter.write("K");
                        fileWriter.write("\n");

                        for (int i = 0; i < IndexReponses.size(); i++) {
                            fileWriter.write(IndexReponses.get(i).getN().toString());
                            fileWriter.write(",");
                            fileWriter.write(IndexReponses.get(i).getP().toString());
                            fileWriter.write(",");
                            fileWriter.write(IndexReponses.get(i).getK().toString());
                            fileWriter.write(",");
                            fileWriter.write("\n");
                        }
                        fileWriter.flush();
                        fileWriter.close();
                        Toast.makeText(Csvdata.this,"Se creo existosamente" ,Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(Csvdata.this, "Request Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void humedadlist(){
        File file = new File(Environment.getExternalStorageDirectory() + "/CSV");
        String archivo = file.toString()+"/"+"Humedad.csv";

        boolean isCreate = false;
        if(!file.exists()){
            isCreate = file.mkdir();
        }
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
        ArrayList<String> data = new ArrayList<>();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                    try {
                        FileWriter fileWriter = new FileWriter(archivo);
                        int count = 0;
                        for (int i = 0; i < IndexReponses.size(); i++) {
                            count++;
                            fileWriter.write(IndexReponses.get(i).getHumedad().toString());
                            fileWriter.write(",");
                            if (count == 5){
                                fileWriter.write("\n");
                                count = 0;
                            }
                        }
                        fileWriter.flush();
                        fileWriter.close();
                        Toast.makeText(Csvdata.this,"Se creo existosamente" ,Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(Csvdata.this, "Request Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}