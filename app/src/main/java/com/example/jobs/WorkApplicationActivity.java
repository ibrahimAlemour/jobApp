package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobs.adapters.MyPostedJobsAdapter;
import com.example.jobs.adapters.MyReceiveJobsAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.EmpSendTalab;
import com.example.jobs.model.OwnerReceiveTalab;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkApplicationActivity extends AppCompatActivity {

    ArrayList<OwnerReceiveTalab> listJobs = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    MyReceiveJobsAdapter myReceiveJobsAdapter;
    RecyclerView rvJop;
    TextView v ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_application);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        v= findViewById(R.id.tvTitleToolbar);
        v.setText("المهنيون المتقدمون");


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {

            int idJob = bundle.getInt("idJob");

            rvJop = findViewById(R.id.rvJop);
            swipRefresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);

            swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getMyReceiveJobs(idJob);
                }
            });

            getMyReceiveJobs(idJob);






        }
    }

    private void getMyReceiveJobs(int id) {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getReceiveTalab(id)
                .enqueue(new Callback<ArrayList<OwnerReceiveTalab>>() {
                    @Override
                    public void onResponse(Call<ArrayList<OwnerReceiveTalab>> call, Response<ArrayList<OwnerReceiveTalab>> response) {

                        if (response.isSuccessful()) {

                            if (response.body() == null){
                                swipRefresh.setRefreshing(false);
                            }

                            listJobs = response.body();
                            for (int i = 0; i < listJobs.size(); i++) {

                                myReceiveJobsAdapter = new MyReceiveJobsAdapter(WorkApplicationActivity.this, listJobs);
                                rvJop.setAdapter(myReceiveJobsAdapter);
                                swipRefresh.setRefreshing(false);


                            }

                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
                                Toast.makeText(WorkApplicationActivity.this, "لا يوجد متقدمين حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<OwnerReceiveTalab>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);
                        Log.e("MyPostedJobs", "onFailure: "+t.getMessage() );

                    }
                });
    }
}