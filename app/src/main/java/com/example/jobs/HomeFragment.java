package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs.adapters.CategoryAdapter;
import com.example.jobs.adapters.OpenJopsAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Category;
import com.example.jobs.model.JopsOpen;
import com.example.jobs.model.OpenTalab;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ArrayList<JopsOpen> listJop = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    OpenJopsAdapter openJopsAdapter;
    RecyclerView rvJop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v =  inflater.inflate(R.layout.fragment_home, container, false);

        rvJop = v.findViewById(R.id.rvJop);
        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOpenJop();
            }
        });

        getOpenJop();

        return v ;
    }

    private void getOpenJop() {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getOpenJops()
                .enqueue(new Callback<ArrayList<JopsOpen>>() {
                    @Override
                    public void onResponse(Call<ArrayList<JopsOpen>> call, Response<ArrayList<JopsOpen>> response) {

                        if (response.isSuccessful()) {
                            listJop = response.body();
                            for (int i = 0; i < listJop.size(); i++) {

                                openJopsAdapter = new OpenJopsAdapter(getContext(), listJop);
                                rvJop.setAdapter(openJopsAdapter);
                                swipRefresh.setRefreshing(false);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<JopsOpen>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);

                    }
                });

    }
}