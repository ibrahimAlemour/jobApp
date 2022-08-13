package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CompleteInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_info);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


      TextView v= findViewById(R.id.tvTitleToolbar);
        v.setText("أكمل ملفك الشخصي");

        Spinner x = findViewById(R.id.geographical);
        Spinner y = findViewById(R.id.availablee);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.area, R.layout.spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        x.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(this,
                R.array.available, R.layout.spinner_item);
        adapterr.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        y.setAdapter(adapterr);
    }
}