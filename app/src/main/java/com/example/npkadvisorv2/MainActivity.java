package com.example.npkadvisorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText username1;
    EditText password1;
    FloatingActionButton start_user;

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
                saveUser(createRequest());
                saveUser(createRequest1());
            }
        });
    }
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setNombre(username1.getText().toString());
        return userRequest;
    }
    public UserRequest createRequest1(){
        UserRequest userRequest = new UserRequest();
        userRequest.setNombre(password1.getText().toString());
        return userRequest;
    }
    public void Open(View view){
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }
    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "exito",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "fallo",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Rquest Failed",Toast.LENGTH_LONG).show();

            }
        });
    }
}