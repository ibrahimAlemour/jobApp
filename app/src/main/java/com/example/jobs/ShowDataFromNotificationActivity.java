package com.example.jobs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDataFromNotificationActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvNameOwner;
    private TextView tvLocation;
    private EditText etProposal;
    private TextView tv5;
    private EditText etPrice;
    private AppCompatButton btnStartJob;
    ProgressDialog pd;
    ProgressDialog pd2;
    private String jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_from_notification);
        initView();

        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("جاري الإرسال ...");

        pd2 = new ProgressDialog(this);
        pd2.setMessage("انتظر لحظة ...");

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

             jobId = bundle.getString("jobId");

             getJobById(Integer.parseInt(jobId));

            btnStartJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String proposal =etProposal.getText().toString().toString();
                    int price = Integer.parseInt(etPrice.getText().toString().toString());
                    runJob(proposal,price,Integer.parseInt(jobId));

                }
            });

        }
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvNameOwner = (TextView) findViewById(R.id.tv_NameOwner);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        etProposal = (EditText) findViewById(R.id.etProposal);
        tv5 = (TextView) findViewById(R.id.tv_5);
        etPrice = (EditText) findViewById(R.id.etPrice);
        btnStartJob = (AppCompatButton) findViewById(R.id.btnStartJob);
    }



    private void getJobById(int id) {
        pd2.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getJobById(id)
                .enqueue(new Callback<JobsOpen>() {
                    @Override
                    public void onResponse(Call<JobsOpen> call, Response<JobsOpen> response) {

                        if (response.isSuccessful()){
                            Log.e("JobById", "onResponse: "+response.body().title );

                            tvTitle.setText(response.body().title);
                            getUserById(response.body().user_id);
                            tvDescription.setText(response.body().description);
                            tvLocation.setText(response.body().city.name + "-" + response.body().district.name);

                            pd2.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<JobsOpen> call, Throwable t) {
                        Log.e("JobById", "onResponse: "+t.getMessage() );

                    }
                });
    }


    private void runJob(String des , int price ,int job_id) {

        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .ApplicationJob(job_id,des,price)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        Log.e("JobApplication", "onResponse: " + response.body().message);
                        if (response.isSuccessful()){

                            Toast.makeText(ShowDataFromNotificationActivity.this, "تم إرسال العرض ", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }

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

}