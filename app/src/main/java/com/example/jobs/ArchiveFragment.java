package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobs.adapters.OpenJopsAdapter;
import com.example.jobs.adapters.SavedJobsAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.SavedJobs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArchiveFragment extends Fragment {

    public static ArrayList<SavedJobs> listJobs = new ArrayList<>();
    public static SwipeRefreshLayout swipRefresh;
    public static SavedJobsAdapter savedJobsAdapter;
    public static RecyclerView rvJop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_archive, container, false);

        rvJop = v.findViewById(R.id.rvJop);
        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSavedJobs();
            }
        });

        getSavedJobs();


        return v ;
    }

    private void getSavedJobs() {
        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getSavedJobs()
                .enqueue(new Callback<ArrayList<SavedJobs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SavedJobs>> call, Response<ArrayList<SavedJobs>> response) {

                        if (response.isSuccessful()) {
                            listJobs = response.body();
                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
                                Toast.makeText(getContext(), "لا يوجد محفوظات حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < listJobs.size(); i++) {

                                savedJobsAdapter = new SavedJobsAdapter(getContext(), listJobs);
                                rvJop.setAdapter(savedJobsAdapter);
                                swipRefresh.setRefreshing(false);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SavedJobs>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);

                    }
                });
    }


    public static void getSavedJobs2() {
        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getSavedJobs()
                .enqueue(new Callback<ArrayList<SavedJobs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SavedJobs>> call, Response<ArrayList<SavedJobs>> response) {

                        if (response.isSuccessful()) {
                            listJobs = response.body();
                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
                                //Toast.makeText(getContext(), "لا يوجد محفوظات حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < listJobs.size(); i++) {

                               // savedJobsAdapter = new SavedJobsAdapter(getContext(), listJobs);
                                rvJop.setAdapter(savedJobsAdapter);
                                swipRefresh.setRefreshing(false);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SavedJobs>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);

                    }
                });
    }
}