package com.example.npkadvisorv2;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    //Añadir un nuevo usuario
    @NonNull
    @POST("persona/AddPersona/")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    //Settings Login Authentication
    @NonNull
    @POST("persona/login/")
    Call<UserResponse>SignIn(@Body UserRequest userRequest);

    //añadir un cultivo
    @NonNull
    @POST("cultivo")
    Call<CropResponse>saveCrop(@Body CropResponse2 cropRequest);

    //consultar todos los cultivos
    @NonNull
    @GET("cultivo")
    Call<CropResponse>findAllC();

    //Consultar variables Humedad, Npk, Temperatura
    @NonNull
    @GET("index")
    Call<IndexResponse>findIndex();

    //Consultar variables Humedad, Npk, Temperatura
    @NonNull
    @GET("index")
    Call<IndexResponse>findIndex1();
}
