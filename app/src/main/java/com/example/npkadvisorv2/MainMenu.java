package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends Activity {
    ImageView vercultivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        vercultivos = findViewById(R.id.vercultivos);

        vercultivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open();
            }
        });
    }
    public void Open() {  //Lleva a la actividad de Menú después de autenticar las credenciales.
        startActivity(new Intent(MainMenu.this, Charts.class));
    }
}