package com.example.jobs.api;


import com.example.jobs.model.Category;
import com.example.jobs.model.City;
import com.example.jobs.model.District;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.Notifications;
import com.example.jobs.model.SavedJobs;
import com.example.jobs.model.User;
import com.example.jobs.model.UserInJob;
import com.example.jobs.model.UserProfile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @FormUrlEncoded
    @POST("job_invitation")
    Call<MsSaveJob> JobInvention(@Field("job_id") int job_id,
                                 @Field("emp_user_id") int emp_user_id);

    @FormUrlEncoded
    @POST("job")
    Call<MsSaveJob> AddJob(@Field("title") String title,
                           @Field("description") String description,
                           @Field("city_id") int city_id,
                           @Field("district_id") int district_id,
                           @Field("job_type_id") int job_type_id
                           );

    @GET("user/profile")
    Call<UserProfile> getProfile();

    @GET("job-title")
    Call<ArrayList<Category>> getJops();


    @GET("job-title/{id}/user")
    Call<ArrayList<UserInJob>> getUserInJob(@Path("id") int id_JobTitle);


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

    @GET("city/{id}/district")
    Call<ArrayList<District>> getDistrict(@Path("id") int id);

    @GET("city")
    Call<ArrayList<City>> getCity();

    @FormUrlEncoded
    @PUT("job/{job_status_id}/update-job-status")
    Call<MsSaveJob> jobStatus(@Field("job_status_id") int job_status_id);


}
