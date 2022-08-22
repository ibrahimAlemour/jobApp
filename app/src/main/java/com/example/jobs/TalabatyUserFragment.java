package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobs.adapters.MyPostedJobsAdapter;
import com.example.jobs.adapters.MyPostedJobsOwnerAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.ProfeeionalType;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TalabatyUserFragment extends Fragment {

    ArrayList<MyPostedJobs> listJobs = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    MyPostedJobsOwnerAdapter myPostedJobsAdapter;
    RecyclerView rvJop;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_talabaty_user, container, false);

        rvJop = v.findViewById(R.id.rvJop);
        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyPostedJobs();
            }
        });



        return  v ;
    }

    @Override
    public void onStart() {
        super.onStart();

        getMyPostedJobs();
    }

    private void getMyPostedJobs() {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getMyPostedJobs()
                .enqueue(new Callback<ArrayList<MyPostedJobs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MyPostedJobs>> call, Response<ArrayList<MyPostedJobs>> response) {

                        if (response.isSuccessful()) {

                            if (response.body() == null){
                                swipRefresh.setRefreshing(false);
                            }

                            listJobs = response.body();

                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
                                Toast.makeText(getContext(), "لا يوجد طلبات حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < listJobs.size(); i++) {

                                myPostedJobsAdapter = new MyPostedJobsOwnerAdapter(getContext(), listJobs);
                                rvJop.setAdapter(myPostedJobsAdapter);
                                swipRefresh.setRefreshing(false);


                            }
                        }else {
                            swipRefresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MyPostedJobs>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);
                        Log.e("MyPostedJobs", "onFailure: "+t.getMessage() );

                    }
                });
    }
}