package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenCrops extends Activity {
    EditText cropname;
    //EditText croparea;
    ImageView btn_add;
    ImageView bnt_delete;
    ImageView bnt_ver;
    Spinner crops;
    Spinner crops2;
    ArrayList<String> listacultivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_crops);
        cropname = findViewById(R.id.cropname);
        //croparea = findViewById(R.id.croparea);
        btn_add = findViewById(R.id.btn_cropadd);
        bnt_delete = findViewById(R.id.btn_cropdelete);
        bnt_ver = findViewById(R.id.btn_supervisar);
        crops = findViewById(R.id.spinnerDelete);
        crops2 = findViewById(R.id.spinnerver);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCrop(createRequest());
               // showCrop();

            }

            public CropRequest createRequest() {
                CropRequest cropRequest = new CropRequest();
                cropRequest.setCNombre(cropname.getText().toString());
                //cropRequest.setCArea(Float.parseFloat(croparea.getText().toString()));
                return cropRequest;
            }
            public void saveCrop(CropRequest cropRequest) {
                Call<CropResponse> cropResponseCall = ApiClient.getUserService().saveCrop(cropRequest);
                cropResponseCall.enqueue(new Callback<CropResponse>() {
                    @Override
                    public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ScreenCrops.this, "Registro exitoso", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<CropResponse> call, Throwable t) {
                        Toast.makeText(ScreenCrops.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}