package com.example.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.UserProfile;
import com.example.jobs.util.MyPreferences;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    private CircleImageView imageView;
    private EditText etName;
    private EditText etPhoneNum;
    private EditText etEmail;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(v);
        MyPreferences.context = getContext();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getContext(), LoginActivity.class);
                MyPreferences.setStr("access_token",null);
                startActivity(b);

            }
        });

        getProfileUser();

        return v;
    }

    private void initView(View v) {
        imageView = (CircleImageView) v.findViewById(R.id.imageView);
        etName = (EditText) v.findViewById(R.id.etName);
        etPhoneNum = (EditText) v.findViewById(R.id.etPhoneNum);
        etEmail = (EditText) v.findViewById(R.id.etEmail);
        btnLogout = (Button) v.findViewById(R.id.btnLogout);
    }

    private void getProfileUser() {
        // swipeRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getProfile()
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        Log.e("TAG", "onResponse: " + response.body().email);

                        etName.setText(response.body().name);
                        etEmail.setText(response.body().email);
                        etPhoneNum.setText(response.body().phone);
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
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