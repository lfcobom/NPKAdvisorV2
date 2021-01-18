package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    EditText username1;
    EditText password1;
    ImageView start_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        start_user = findViewById(R.id.btn_inicio);

        start_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open();
                if (validate()) {
                    SignIn(Credenciales());
                }
            }
        });
    }

    public UserRequest Credenciales() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username1.getText().toString());
        userRequest.setPassword(password1.getText().toString());
        return userRequest;
    }

    public void Open() {  //Lleva a la actividad de Menú después de autenticar las credenciales.
        startActivity(new Intent(MainActivity.this, MainMenu.class));
    }

    private Boolean validate() { //VALIDAR QUE LOS CAMPOS DEL LOGIN NO ESTEN VACIOS
        Boolean result = false;
        String name = username1.getText().toString();
        String password = password1.getText().toString();
        if (name.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese sus credenciales", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    public void SignIn(UserRequest userRequest) {
        if (validate()) {
            Call<UserResponse> userResponseCall = ApiClient.getUserService().SignIn(userRequest);
            userResponseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Iniciando Sesión...", Toast.LENGTH_LONG).show();
                        Open();
                    } else {
                        Toast.makeText(MainActivity.this, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Rquest Failed", Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}