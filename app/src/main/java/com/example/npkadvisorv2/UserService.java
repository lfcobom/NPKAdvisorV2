package com.example.npkadvisorv2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    //Añadir un nuevo usuario
    @POST("persona/AddPersona/")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    //Settings Login Authentication
    @POST("persona/login/")
    Call<UserResponse>SignIn(@Body UserRequest userRequest);

    //añadir un cultivo
    @POST("cultivo")
    Call<CropResponse>saveCrop(@Body CropRequest cropRequest);

    //consultar todos los cultivos
    @GET("cultivo/findAllCultivo")
        Call<CropResponse> findAllC();
}
