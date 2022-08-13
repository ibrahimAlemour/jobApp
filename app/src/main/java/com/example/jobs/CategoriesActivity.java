package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.jobs.adapters.NotifyAdapter;
import com.example.jobs.adapters.categoryAdapter;
import com.example.jobs.model.Category;
import com.example.jobs.model.Notify;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

//        RecyclerView rvc = findViewById(R.id.rvc);
//
//
//        ArrayList<Category> categories = new ArrayList<>();
//
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
//
//
//        rvc.setLayoutManager(new GridLayoutManager(this, 2));
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoriesActivity.this  , 2);
//
//        //LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(CategoriesActivity.this , LinearLayoutManager.VERTICAL , false);
//        rvc.setLayoutManager(gridLayoutManager);
//        rvc.setHasFixedSize(true);
//        categoryAdapter mAdapterr = new categoryAdapter(CategoriesActivity.this ,categories );
//        rvc.setAdapter(mAdapterr);
    }
}