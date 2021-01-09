package com.example.npkadvisorv2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("AddPersona/")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);
}
