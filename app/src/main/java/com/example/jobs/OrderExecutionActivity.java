package com.example.jobs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderExecutionActivity extends AppCompatActivity {

    private TextView tvTitleToolbar;
    private TextView tvTitle;
    private TextView tvNameOwner;
    private TextView tvLocation;
    private TextView tvDescription;
    private int id;
    private ImageView imgSave;
    private AppCompatButton btnRunJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_execution);
        initView();

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            int user_id = bundle.getInt("user_id");
            getUserById(user_id);

            id = bundle.getInt("job_id");

            Log.e("JobID", "onCreate: "+id );

            tvTitle.setText(bundle.getString("title"));
            tvNameOwner.setText(bundle.getString("-"));
            tvDescription.setText(bundle.getString("description"));
            tvLocation.setText(bundle.getString("city") + "-" + bundle.getString("district"));

            if (bundle.getBoolean("is_saved") == true) {
                imgSave.setVisibility(View.GONE);
            } else {

                imgSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        saveJob(id);
                    }
                });
            }

            btnRunJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    runJob(id);
                }
            });

        }

    }


    private void initView() {
        tvTitleToolbar = (TextView) findViewById(R.id.tvTitleToolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvNameOwner = (TextView) findViewById(R.id.tv_NameOwner);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        imgSave = (ImageView) findViewById(R.id.imgSave);
        btnRunJob = (AppCompatButton) findViewById(R.id.btnStartJob);
    }

    private void runJob(int job_id) {

        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .jobFinish(job_id)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        Log.e("JobStatus", "onResponse: "+response.body().message );

                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });

    }


    private void getUserById(int user_id) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getUserById(user_id)
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

                        if (response.isSuccessful()) {
                            tvNameOwner.setText(response.body().name);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {

                    }
                });
    }


    private void saveJob(int id) {

        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .SaveJop(id)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {
                        Toast.makeText(OrderExecutionActivity.this, "" + response.body().message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }

}