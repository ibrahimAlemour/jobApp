package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jobs.adapters.ProfeeionalAdapter;
import com.example.jobs.adapters.TlabatyAdapter;
import com.example.jobs.model.ProfeeionalType;
import com.example.jobs.model.Talbaty;

import java.util.ArrayList;

public class ProfeeionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profeeional);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);





        RecyclerView rvp = findViewById(R.id.rvp);

        ArrayList<ProfeeionalType> profeeionalTypes = new ArrayList<>();

        profeeionalTypes.add(new ProfeeionalType(R.drawable.man,"حسين" , "خانيونس - المعسكر" , "4", "متاح"  ));
        profeeionalTypes.add(new ProfeeionalType(R.drawable.man,"حسين" , "خانيونس - المعسكر" , "4", "متاح"  ));
        profeeionalTypes.add(new ProfeeionalType(R.drawable.man,"حسين" , "خانيونس - المعسكر" , "4", "متاح"  ));
        profeeionalTypes.add(new ProfeeionalType(R.drawable.man,"حسين" , "خانيونس - المعسكر" , "5", "متاح"  ));







        LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(ProfeeionalActivity.this , LinearLayoutManager.VERTICAL , false);
        rvp.setLayoutManager(linearLayoutManagerr);
        rvp.setHasFixedSize(true);
        ProfeeionalAdapter mAdapterr = new ProfeeionalAdapter(ProfeeionalActivity.this  ,profeeionalTypes );
        rvp.setAdapter(mAdapterr);
    }
}