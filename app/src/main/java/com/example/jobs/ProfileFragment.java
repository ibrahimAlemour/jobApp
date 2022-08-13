package com.example.jobs;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.User;
import com.example.jobs.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_profile, container, false);

        Button c =  (Button) v.findViewById(R.id.logoutBtn);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getContext() ,  CategoriesActivity.class);
                startActivity(b);
            }
        });

        getj();
        //getProfile();

        return v ;
    }

    private void getj() {
        // swipeRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getProfile()
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        Log.e("TAG", "onResponse: "+response.body().email );
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }



//    private void getProfile() {
//       // swipeRefresh.setRefreshing(true);
//        RetrofitClient.getRetrofitInstance()
//                .create(ApiInterface.class)
//                .getProfile()
//                .enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//
//
//                        Log.e("profileError", response.isSuccessful()+ "");
//
////                        etFirstName.setText(response.body().data.first_name);
////                        etLastName.setText(response.body().data.last_name);
////                        etEmail.setText(response.body().data.email);
////                        etPhoneNumber.setText(response.body().data.phone);
////
////
////                        etBio.setText(response.body().data.about_doctor);
////                        etProfession.setText(response.body().data.profession);
////                        etScientificLevel.setText(response.body().data.degree);
////                        etNameClinic.setText(response.body().data.clinic_work);
//
////                        Glide.with(UserProfileActivity.this)
////                                .load(response.body().data.image_path)
////                                .fitCenter()
////                                .into(profileImage);
//
//
//
//                      //  swipeRefresh.setRefreshing(false);
//
////                        Log.e("prfile", "" + response.body().data.image_path);
////                        imagePathUrl = response.body().data.image_path;
//                    }
//
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                        //swipeRefresh.setRefreshing(false);
//                        Log.e("profileError", t.getMessage());
//                    }
//                });
//
//    }
}