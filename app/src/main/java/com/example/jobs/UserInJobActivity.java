package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.jobs.adapters.CategoryAdapter;
import com.example.jobs.adapters.UserInJobAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Category;
import com.example.jobs.model.UserInJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInJobActivity extends AppCompatActivity {

    ArrayList<UserInJob> list = new ArrayList<>();
    private SwipeRefreshLayout swipRefresh;
    UserInJobAdapter userInJobAdapter;
    RecyclerView rvc;
    int id ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_in_job);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        swipRefresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
        rvc = findViewById(R.id.rvc);

        Bundle bundle = getIntent().getExtras();

       // if (bundle.getInt("id").isEmpty()){
            id = bundle.getInt("id");
            Log.e("UserInJob", "onCreate: "+id );



        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUser(id);
            }
        });

        getUser(id);
    }

    private void getUser(int id) {

        swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getUserInJob(id)
                .enqueue(new Callback<ArrayList<UserInJob>>() {
                    @Override
                    public void onResponse(Call<ArrayList<UserInJob>> call, Response<ArrayList<UserInJob>> response) {

                        if (response.isSuccessful()) {
                            list = response.body();
                            for (int i = 0; i < list.size(); i++) {

                                userInJobAdapter = new UserInJobAdapter(UserInJobActivity.this, list);
                                rvc.setAdapter(userInJobAdapter);
                                swipRefresh.setRefreshing(false);

                                //Toast.makeText(getContext(), ""+listCategories.get(i).getName(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UserInJob>> call, Throwable t) {

                        swipRefresh.setRefreshing(false);

                    }
                });

    }
}