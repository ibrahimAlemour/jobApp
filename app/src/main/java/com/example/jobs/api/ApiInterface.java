package com.example.jobs.api;


import com.example.jobs.model.Category;
import com.example.jobs.model.City;
import com.example.jobs.model.District;
import com.example.jobs.model.EmpSendTalab;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.Notifications;
import com.example.jobs.model.OwnerReceiveTalab;
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

    @Multipart
    @POST("user/update-profile")
    Call<User> updateProfile(@Part("name") String name,
                             @Part("phone") String phone,
                             @Part("_method") String _method,
                             @Part("about_me") String about_me,
                             @Part("job_type_id") int job_type_id,
                             @Part("job_title_id") int job_title_id,
                             @Part("city_id") int city_id,
                             @Part("district_id") int district_id,
                             @Part("is_available") int is_available
    );

    @Multipart
    @POST("user/update-profile")
    Call<User> updateProfileUser(@Part("name") String name,
                                 @Part("phone") String phone,
                                 @Part("_method") String _method
    );


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

    @FormUrlEncoded
    @POST("job_invitation")
    Call<MsSaveJob> AddJobInvitation(@Field("title") String title,
                           @Field("description") String description,
                           @Field("city_id") int city_id,
                           @Field("district_id") int district_id,
                           @Field("job_type_id") int job_type_id,
                           @Field("emp_user_id") int emp_user_id
    );

    @Multipart
    @POST("job-application")
    Call<MsSaveJob> ApplicationJob(@Part("job_id") int job_id,
                                   @Part("description") String description,
                                   @Part("price") int price
    );

    @Multipart
    @POST("rating")
    Call<MsSaveJob> setRating(@Part("job_id") int job_id,
                              @Part("emp_user_id") int emp_user_id,
                              @Part("rating") double rating
    );

    @GET("job/{id}/applications")
    Call<ArrayList<OwnerReceiveTalab>> getReceiveTalab(@Path("id") int id);

    @GET("user/profile")
    Call<UserProfile> getProfile();

    @GET("job-title")
    Call<ArrayList<Category>> getJops();

    @GET("job-title")
    Call<ArrayList<City>> getJopsTitle();

    @GET("job-type")
    Call<ArrayList<City>> getJopsType();


    @GET("job-title/{id}/user")
    Call<ArrayList<UserInJob>> getUserInJob(@Path("id") int id_JobTitle);


    @GET("job/open-jobs")
    Call<ArrayList<JobsOpen>> getOpenJops();

    @GET("saved-job/my-saved-jobs")
    Call<ArrayList<SavedJobs>> getSavedJobs();

    @GET("job/my-posted-jobs")
    Call<ArrayList<MyPostedJobs>> getMyPostedJobs();

    @GET("job-application/my-applied-jobs")
    Call<ArrayList<EmpSendTalab>> getMyJobsEmp();

    @GET("notification/my-notifications")
    Call<ArrayList<Notifications>> getMyNotifications();


    @GET("user/{id}")
    Call<UserProfile> getUserById(@Path("id") int id_User);

    @GET("job/{id}")
    Call<JobsOpen> getJobById(@Path("id") int id);

    @GET("city/{id}/district")
    Call<ArrayList<District>> getDistrict(@Path("id") int id);

    @GET("city")
    Call<ArrayList<City>> getCity();

    @GET("job-status")
    Call<ArrayList<City>> getJobStatus();


    @FormUrlEncoded
    @PUT("job/{id}/update-job-status")
    Call<MsSaveJob> jobStatus(@Path("id") int id,
                              @Field("job_status_id") int job_status_id);


    @PUT("job/{id}/finish-job")
    Call<MsSaveJob> jobFinish(@Path("id") int id);

    @FormUrlEncoded
    @PUT("job/{id}/assign-job-to-employee")
    Call<MsSaveJob> SelectEmp(@Path("id") int id,
                              @Field("emp_user_id") int emp_user_id
    );


}
