package com.example.jobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.User;
import com.example.jobs.model.UserProfile;
import com.example.jobs.util.MyPreferences;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUserFragment extends Fragment {


    private CircleImageView imageView;
    private EditText etName;
    private EditText etPhoneNum;
    private TextView etEmail;
    private Button btnLogout,btnUpdate;
    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_user, container, false);
        initView(v);
        MyPreferences.context = getContext();

        //Dialog
        pd = new ProgressDialog(getContext());
        pd.setMessage("جاري تحديث البيانات ...");


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getContext(), LoginActivity.class);
                MyPreferences.setStr("access_token",null);
                startActivity(b);

            }
        });

        getProfileUser();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phone = etPhoneNum.getText().toString().trim();
                updateProfile(name,phone,"PUT");
            }
        });

        return v;
    }

    private void initView(View v) {
        imageView = (CircleImageView) v.findViewById(R.id.imageView);
        etName = (EditText) v.findViewById(R.id.etName);
        etPhoneNum = (EditText) v.findViewById(R.id.etPhoneNum);
        etEmail = (TextView) v.findViewById(R.id.etEmail);
        btnLogout = (Button) v.findViewById(R.id.btnLogout);
        btnUpdate = (Button) v.findViewById(R.id.btnUpdate);
    }


    private void updateProfile(String name, String phone, String method) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .updateProfileUser(name, phone, method)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "تم تحديث بياناتك ", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
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
}