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

import com.example.jobs.adapters.CategoryAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeUserFragment extends Fragment {

    ArrayList<Category> listCategories = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    CategoryAdapter categoryAdapter;
    RecyclerView rvc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home_user, container, false);

        swipRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swip_refresh);
        rvc = v.findViewById(R.id.rvc);



        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCategories();
            }
        });

        getCategories();




        return v ;
    }

    private void getCategories() {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getJops()
                .enqueue(new Callback<ArrayList<Category>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {

                        if (response.isSuccessful()) {
                            listCategories = response.body();
                            for (int i = 0; i < listCategories.size(); i++) {

                                categoryAdapter = new CategoryAdapter(getContext(), listCategories);
                                rvc.setAdapter(categoryAdapter);
                                swipRefresh.setRefreshing(false);

                                //Toast.makeText(getContext(), ""+listCategories.get(i).getName(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);

                    }
                });

    }
}