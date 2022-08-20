package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    TextView v ;
    EditText nameOfOwner , phoneOfOwner ;
    Spinner location ;
    Button contact_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        v= findViewById(R.id.tvTitleToolbar);
        v.setText("معلومات التواصل مع صاحب العمل");

        nameOfOwner = findViewById(R.id.owner_name_work);
        phoneOfOwner = findViewById(R.id.owner_phone_no);
        location = findViewById(R.id.splocation);
        contact_btn = findViewById(R.id.contactBtn);
    }
}