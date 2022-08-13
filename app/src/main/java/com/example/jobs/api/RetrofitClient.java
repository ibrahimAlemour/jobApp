package com.example.jobs.api;

import android.content.Context;
import android.util.Log;

import com.example.jobs.util.MyPreferences;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://saidabdulla.com/mehan/public/api/";
    public static Context context = null;
    public static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


    static OkHttpClient okHttpClient() {

        Log.e("TAG", "okHttpClient:access_token "+ MyPreferences.getStr("access_token") );

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Authorization", "Bearer " + MyPreferences.getStr("access_token"));

                    Request request = builder.build();
                    return chain.proceed(request);
                }).build();
    }

    static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


}
