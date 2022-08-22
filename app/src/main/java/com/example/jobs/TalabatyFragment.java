package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobs.adapters.MyPostedJobsAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.EmpSendTalab;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TalabatyFragment extends Fragment {

    public static ArrayList<EmpSendTalab> listJobs = new ArrayList<>();
    public static SwipeRefreshLayout swipRefresh;
    public static  MyPostedJobsAdapter myPostedJobsAdapter;
    public static RecyclerView rvJop;

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
                .getMyJobsEmp()
                .enqueue(new Callback<ArrayList<EmpSendTalab>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EmpSendTalab>> call, Response<ArrayList<EmpSendTalab>> response) {

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

                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
                                Toast.makeText(getContext(), "لا يوجد طلبات حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EmpSendTalab>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);
                        Log.e("MyPostedJobs", "onFailure: "+t.getMessage() );

                    }
                });
    }



    public static  void getMyPostedJobs2() {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getMyJobsEmp()
                .enqueue(new Callback<ArrayList<EmpSendTalab>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EmpSendTalab>> call, Response<ArrayList<EmpSendTalab>> response) {

                        if (response.isSuccessful()) {

                            if (response.body() == null){
                                swipRefresh.setRefreshing(false);
                            }

                            listJobs = response.body();
                            for (int i = 0; i < listJobs.size(); i++) {

                               // myPostedJobsAdapter = new MyPostedJobsAdapter(getContext(), listJobs);
                                rvJop.setAdapter(myPostedJobsAdapter);
                                swipRefresh.setRefreshing(false);


                            }

                            if (listJobs.size() == 0){
                                swipRefresh.setRefreshing(false);
//                                Toast.makeText(getContext(), "لا يوجد طلبات حتى الان ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EmpSendTalab>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);
                        Log.e("MyPostedJobs", "onFailure: "+t.getMessage() );

                    }
                });
    }
}