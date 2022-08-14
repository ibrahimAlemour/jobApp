package com.example.jobs.api;


import com.example.jobs.model.Category;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.Notifications;
import com.example.jobs.model.SavedJobs;
import com.example.jobs.model.User;
import com.example.jobs.model.UserProfile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


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

    @FormUrlEncoded
    @POST("saved-job")
    Call<MsSaveJob> SaveJop(@Field("job_id") int job_id);

    @GET("user/profile")
    Call<UserProfile> getProfile();

    @GET("job-title")
    Call<ArrayList<Category>> getJops();

    @GET("job/open-jobs")
    Call<ArrayList<JobsOpen>> getOpenJops();

    @GET("saved-job/my-saved-jobs")
    Call<ArrayList<SavedJobs>> getSavedJobs();

    @GET("job/my-posted-jobs")
    Call<ArrayList<MyPostedJobs>> getMyPostedJobs();

    @GET("notification/my-notifications")
    Call<ArrayList<Notifications>> getMyNotifications();


    @GET("user/{id}")
    Call<UserProfile> getUserById(@Path("id") int id_User);


}
