package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.jobs.util.MyPreferences;

public class PreregisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button user, professional;

    int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preregister);

        MyPreferences.context = this;

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        user = (Button) findViewById(R.id.userBtn);
        user.setOnClickListener(this);
        professional = (Button) findViewById(R.id.powermanBtn);
        professional.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.userBtn: {
                // do something for button 1 click

                MyPreferences.setStr("typeUser", "work_owner");
                Intent intent = new Intent(PreregisterActivity.this, LoginActivity.class);
               // intent.putExtra("typeUser", typeUser);
                startActivity(intent);


                break;
            }

            case R.id.powermanBtn: {
                // do something for button 2 click

                MyPreferences.setStr("typeUser", "employee");
                Intent intent = new Intent(PreregisterActivity.this, LoginActivity.class);
               // intent.putExtra("typeUser", typeUser);
                startActivity(intent);
                break;
            }

        }

    }
}