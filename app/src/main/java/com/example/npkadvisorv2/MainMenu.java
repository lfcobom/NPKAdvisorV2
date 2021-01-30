package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends Activity {
    ImageView charts;
    ImageView miscultivos;
    ImageView gis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        charts = findViewById(R.id.vercultivos);
        miscultivos = findViewById(R.id.imageView15);
        gis = findViewById(R.id.imageView18);

        gis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open2();
            }
        });

        miscultivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open1();
            }
        });

        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open();
            }
        });
    }
    public void Open() {  //Lleva a la actividad de Menú después de autenticar las credenciales.

        startActivity(new Intent(MainMenu.this, Charts.class));
    }
    public void Open1() {  //Lleva a la actividad de Menú después de autenticar las credenciales.

        startActivity(new Intent(MainMenu.this, ScreenCrops.class));
    }
    public void Open2() {  //Lleva a la actividad de Menú después de autenticar las credenciales.

        startActivity(new Intent(MainMenu.this, SignUpActivity.class));
    }
}
