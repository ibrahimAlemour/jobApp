package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jobs.adapters.NotificationsAdapter;
import com.example.jobs.adapters.NotificationsOwnerAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Notifications;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationOwnerFragment extends Fragment {

    ArrayList<Notifications> listNotifications = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    NotificationsOwnerAdapter notificationsAdapter;
    RecyclerView rvJop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification_owner, container, false);

        rvJop = v.findViewById(R.id.rvJop);
        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyNotifications();
            }
        });

        getMyNotifications();


        return v;

    }

    private void getMyNotifications() {
        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getMyNotifications()
                .enqueue(new Callback<ArrayList<Notifications>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Notifications>> call, Response<ArrayList<Notifications>> response) {

                        if (response.isSuccessful()) {
                            listNotifications = response.body();
                            for (int i = 0; i < listNotifications.size(); i++) {

                                notificationsAdapter = new NotificationsOwnerAdapter(getContext(), listNotifications);
                                rvJop.setAdapter(notificationsAdapter);
                                swipRefresh.setRefreshing(false);


                            }

                            if (listNotifications.size() == 0){
                                swipRefresh.setRefreshing(false);
                                Toast.makeText(getContext(), "لا يوجد اشعارات حتى الان ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Notifications>> call, Throwable t) {
                        swipRefresh.setRefreshing(false);
                    }
                });
    }
}