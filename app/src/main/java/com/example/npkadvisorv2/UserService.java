package com.example.npkadvisorv2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("AddPersona/")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    @GET("findPersona/id/")
    Call<UserResponse> listRepos(@Path("id") String id);
}
