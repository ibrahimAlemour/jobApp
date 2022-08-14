package com.example.jobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.User;
import com.example.jobs.util.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ProgressDialog pd;
    private ImageView imageView;
    private TextView creataccount;
    private EditText etName;
    private EditText etPhoneNumber;
    private EditText etEmail;
    private EditText etPassword;
    private EditText confirmpassword;
    private AppCompatButton confirmBtn;
    private TextView existingg;
    private TextView tvvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        MyPreferences.context = this;

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Dialog
        pd = new ProgressDialog(this);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateUser();
            }
        });


    }


    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        creataccount = (TextView) findViewById(R.id.creataccount);
        etName = (EditText) findViewById(R.id.name);
        etPhoneNumber = (EditText) findViewById(R.id.number);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        confirmBtn = (AppCompatButton) findViewById(R.id.confirmBtn);
        existingg = (TextView) findViewById(R.id.existingg);
        tvvv = (TextView) findViewById(R.id.tvvv);
    }

    private void CreateUser() {

        pd.setMessage("جاري انشاء حساب");
        pd.show();

        String name = etName.getText().toString().trim();
        String phone = etPhoneNumber.getText().toString().trim();
        String password1 = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password2 = confirmpassword.getText().toString().trim();

        if (email.isEmpty() || password1.isEmpty() || name.isEmpty() || password2.isEmpty() || phone.isEmpty()) {

            etEmail.setError("Email");
            etPassword.setError("Password");
            confirmpassword.setError("Confirm Password");
            etName.setError("name");
            etPhoneNumber.setError("phone");

            pd.dismiss();

        } else {

            RetrofitClient.getRetrofitInstance()
                    .create(ApiInterface.class)
                    .createUser(name,password1,password2,MyPreferences.getStr("typeUser"),email,phone)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            Log.e("createUser", "onResponse: "+response.toString() );
                            if (response.isSuccessful() && response != null) {

                                String email = response.body().user.email;
                                String access_token = response.body().token;
                                String name = response.body().user.name;
                                String phone = response.body().user.phone;
                                String userType = response.body().user.user_type;
                                MyPreferences.setStr("access_token", access_token);

                                Toast.makeText(SignUpActivity.this, "تم انشاء حساب جديد ", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(SignUpActivity.this, "Welcome : " + name + "||" + userType, Toast.LENGTH_SHORT).show();


                                if (userType.contains("work_owner")) {
                                    startActivity(new Intent(SignUpActivity.this, BaseUserActivity.class));
                                } else {
                                    startActivity(new Intent(SignUpActivity.this, BaseActivity.class));

                                }

                                pd.dismiss();
                                finish();

                            } else {

                                Toast.makeText(SignUpActivity.this, "المستخدم مسجل مسبقا", Toast.LENGTH_SHORT).show();

                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                            Log.e("TAG", "onFailure: " + t.getMessage());
                        }
                    });
        }


    }
}