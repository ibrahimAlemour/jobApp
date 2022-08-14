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

import com.example.jobs.adapters.ArchiveAdapter;
import com.example.jobs.adapters.MyPostedJobsAdapter;
import com.example.jobs.adapters.OpenJopsAdapter;
import com.example.jobs.adapters.SavedJobsAdapter;
import com.example.jobs.adapters.TalabAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Archive;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.SavedJobs;
import com.example.jobs.model.Talab;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TalabatyFragment extends Fragment {

    ArrayList<MyPostedJobs> listJobs = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    MyPostedJobsAdapter myPostedJobsAdapter;
    RecyclerView rvJop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_talabaty, container, false);

        rvJop = v.findViewById(R.id.rvJop);
        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyPostedJobs();
            }
        });

        getMyPostedJobs();


        return  v ;
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
                            for (int i = 0; i < listJobs.size(); i++) {

                                myPostedJobsAdapter = new MyPostedJobsAdapter(getContext(), listJobs);
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