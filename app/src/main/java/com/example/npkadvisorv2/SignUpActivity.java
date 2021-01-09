package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText name;
    EditText username;
    EditText password;
    EditText passwordc;
    FloatingActionButton btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordc = findViewById(R.id.passwordc);
        btn_registro = findViewById(R.id.btn_registro);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(createRequest());
            }
        });
    }
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setNombre(name.getText().toString());
        userRequest.setUsername(username.getText().toString());
        userRequest.setPassword(password.getText().toString());
        userRequest.setPasswordC(passwordc.getText().toString());
        return userRequest;
    }
    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "exito",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(SignUpActivity.this, "fallo",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Rquest Failed",Toast.LENGTH_LONG).show();

            }
        });
    }
}