package com.example.jobs.api;



import com.example.jobs.model.Category;
import com.example.jobs.model.User;
import com.example.jobs.model.UserProfile;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<User> UserSignIn(@Field("email") String email,
                           @Field("password") String password);
    @Multipart
    @POST("register")
    Call<User> createUser(@Part("name") String name,
                          @Part("password") String password,
                          @Part("password_confirmation") String password_confirmation,
                          @Part("user_type") String user_type,
                          @Part("email") String email,
                          @Part("phone") String phone);

    @GET("user/profile")
    Call<UserProfile> getProfile();

    @GET("job-title")
    Call<ArrayList<Category>> getJops();




}
