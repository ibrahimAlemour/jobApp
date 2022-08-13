package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jobs.adapters.NotifyAdapter;
import com.example.jobs.adapters.TlabatyAdapter;
import com.example.jobs.model.Notify;
import com.example.jobs.model.Talbaty;

import java.util.ArrayList;

public class TlabatyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlabaty);


        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView v= findViewById(R.id.tvTitleToolbar);
        v.setText("طلباتي");



        RecyclerView rvt = findViewById(R.id.rvt);

        ArrayList<Talbaty> talbaties = new ArrayList<>();

        talbaties.add(new Talbaty("متاح" , "تفصيل طقم نوم حديث" ));
        talbaties.add(new Talbaty("متاح" , "تفصيل طقم نوم حديث" ));
        talbaties.add(new Talbaty("متاح" , "تفصيل طقم نوم حديث" ));
        talbaties.add(new Talbaty("متاح" , "تفصيل طقم نوم حديث" ));






        LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(TlabatyActivity.this , LinearLayoutManager.VERTICAL , false);
        rvt.setLayoutManager(linearLayoutManagerr);
        rvt.setHasFixedSize(true);
        TlabatyAdapter mAdapterr = new TlabatyAdapter(TlabatyActivity.this  ,talbaties );
        rvt.setAdapter(mAdapterr);
    }
}