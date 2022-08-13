package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.jobs.util.MyPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        MyPreferences.context = this;


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

        if (MyPreferences.getStr("access_token")==null  || MyPreferences.getStr("user_type_login") == null) {
            startActivity(new Intent(SplashActivity.this, PreregisterActivity.class));
            finish();

        }else {

            if (MyPreferences.getStr("user_type_login").contains("employee")) {

                startActivity(new Intent(SplashActivity.this, BaseActivity.class));
                finish();

            } else if (MyPreferences.getStr("user_type_login").contains("work_owner")) {

                startActivity(new Intent(SplashActivity.this, BaseUserActivity.class));
                finish();
            }

        }





            }
        }, 3000);
    }
}