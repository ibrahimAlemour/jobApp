package com.example.jobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.User;
import com.example.jobs.util.MyPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pd;

    private ImageView imageView;
    private TextView textlogin;
    private EditText etEmail;
    private EditText editpass;
    private Button loginBtn;
    private TextView tvCreateAccount;
    private TextView tvvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        MyPreferences.context = this;

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.signing));


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });



    }


    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        textlogin = (TextView) findViewById(R.id.textlogin);
        etEmail = (EditText) findViewById(R.id.et_email);
        editpass = (EditText) findViewById(R.id.editpass);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);
        tvvv = (TextView) findViewById(R.id.tvvv);
    }



    private void Login() {

        pd.show();

        String email = etEmail.getText().toString().trim();
        String password = editpass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {

            if (email.isEmpty()) {
                etEmail.setError("Email");
            }
            if (password.isEmpty()) {
                editpass.setError("Password");
            }
            pd.dismiss();

        } else {

            RetrofitClient.getRetrofitInstance()
                    .create(ApiInterface.class)
                    .UserSignIn(email, password)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            if (response.isSuccessful() || response.body() != null) {
                                if (response.body().token != null || !response.body().token.isEmpty()) {

                                    Log.e("tokenState", "Token : " + response.body().token);

                                    String name = response.body().user.name;
                                    String access_token = response.body().token;
                                    String typeUser = response.body().user.user_type;
//                                    String first_name = response.body().getData().user.first_name;
//                                    String last_name = response.body().getData().user.last_name;
//                                    String phone = response.body().getData().user.phone;
//                                    Log.e("TAG", "access_token: "+access_token );
//                                    // RetrofitClient.token = access_token;
                                    MyPreferences.setStr("access_token", access_token);


                                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                                    Toast.makeText(LoginActivity.this, "Welcome" + name+"||"+typeUser, Toast.LENGTH_SHORT).show();

                                    pd.dismiss();

                                } else {


                                    Toast.makeText(LoginActivity.this, "خطأ في كلمة المرور أو الايميل", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();

                                }


                            } else {
                                Toast.makeText(LoginActivity.this, "Error Data is null", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("loginError", "onLogin: " + t.getMessage());

                        }
                    });

        }


    }
}