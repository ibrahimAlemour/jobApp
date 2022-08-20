package com.example.jobs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.MsSaveJob;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpInformationActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAboutMe;
    private TextView tvProposal;
    private TextView tvPrice;
    private RatingBar ratingBar;
    private Button btnInventions;
    ProgressDialog pd;
    private Button btnFinish;
    private Button btnRate;
    private TextView tv2ui;
    private RatingBar ratingBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_information);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("انتظر لحظة ..");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String nameEmp = bundle.getString("name");
            String description = bundle.getString("description");
            String about_meEmp = bundle.getString("about_meEmp");
            int emp_user_id = bundle.getInt("emp_user_id");
            int job_id = bundle.getInt("job_id");
            int job_status_id = bundle.getInt("job_status_id");
            int price = bundle.getInt("price");
            double rate = bundle.getDouble("rate");

            tvName.setText(nameEmp);
            tvAboutMe.setText(about_meEmp);
            tvProposal.setText(description);
            tvPrice.setText(price + "");

            ratingBar.setRating((float) rate);

            if (job_status_id == 1) {

                btnInventions.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.GONE);
                btnRate.setVisibility(View.GONE);
            } else if (job_status_id == 2) {

                btnInventions.setVisibility(View.GONE);
                btnFinish.setVisibility(View.VISIBLE);
                btnRate.setVisibility(View.GONE);
            } else if (job_status_id == 5) {

                btnInventions.setVisibility(View.GONE);
                btnFinish.setVisibility(View.GONE);
                btnRate.setVisibility(View.VISIBLE);
                ratingBar2.setVisibility(View.VISIBLE);
                ratingBar.setVisibility(View.GONE);


                btnRate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        setRateEmp(job_id, emp_user_id, (double) ratingBar2.getRating());

                    }
                });


            }


            btnInventions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SelectEmp(emp_user_id, job_id);
                   // postInvention(emp_user_id, job_id);

                }
            });

            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finishJob(job_id);
                }
            });


        }

    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.tv_name);
        tvAboutMe = (TextView) findViewById(R.id.tvAboutMe);
        tvProposal = (TextView) findViewById(R.id.tvProposal);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnInventions = (Button) findViewById(R.id.btnInventions);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnRate = (Button) findViewById(R.id.btnRate);
        tv2ui = (TextView) findViewById(R.id.tv_2ui);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
    }

    private void setRateEmp(int idJop, int idEmp, double rate) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .setRating(idJop, idEmp, rate)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {
                            Log.e("RateJobEmp", "onResponse: " + response.body().message);
                            Toast.makeText(EmpInformationActivity.this, "تم تقييم المهني", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }

    private void finishJob(int idJop) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .jobFinish(idJop)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {
                            Log.e("finishJobEmp", "onResponse: " + response.body().message);
                            Toast.makeText(EmpInformationActivity.this, "تم الإنتهاء من الطلب", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }


    private void postInvention(int idEmp, int idJop) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .JobInvention(idJop, idEmp)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {

                            // Toast.makeText(EmpInformationActivity.this, "تم ارسال الدعوة", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }

    private void SelectEmp(int idEmp, int idJop) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .SelectEmp(idJop, idEmp)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {

                            Log.e("SelectEmp", "onResponse: " + response.body().message);

                            Toast.makeText(EmpInformationActivity.this, "جاري التنفيذ", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }

}