package com.example.jobs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jobs.adapters.AdapterCity;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.City;
import com.example.jobs.model.MsSaveJob;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoActivity extends AppCompatActivity {

    ArrayList<City> listJobTitle = new ArrayList<>();
    private ConstraintLayout linear;
    private CircleImageView imgPerson;
    private TextView tvName;
    private TextView tvJob;
    private TextView tv1ui;
    private TextView tvAboutMe;
    private TextView tv2ui;
    private RatingBar ratingBar;
    private Button btnInventions;
    private int idJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            tvName.setText(bundle.getString("name"));
            tvAboutMe.setText(bundle.getString("me"));
            idJob = bundle.getInt("idJob");
            double rate = bundle.getDouble("rate");
            Log.e("RateEmployee", "onCreate: "+rate );
            ratingBar.setRating((float) rate);


            btnInventions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    postInvention(bundle.getInt("idEmp"), bundle.getInt("idJob"));
                }
            });

            getJobTitle();

        }

    }


    private void initView() {
        linear = (ConstraintLayout) findViewById(R.id.linear);
        imgPerson = (CircleImageView) findViewById(R.id.img_person);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvJob = (TextView) findViewById(R.id.tv_job);
        tv1ui = (TextView) findViewById(R.id.tv_1ui);
        tvAboutMe = (TextView) findViewById(R.id.tvAboutMe);
        tv2ui = (TextView) findViewById(R.id.tv_2ui);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnInventions = (Button) findViewById(R.id.btnInventions);
    }



    private void postInvention(int idEmp, int idJop) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .JobInvention(idJop, idEmp)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {

                            Toast.makeText(UserInfoActivity.this, "تم ارسال الدعوة", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
    }


    private void getJobTitle() {

        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getJopsTitle()
                .enqueue(new Callback<ArrayList<City>>() {
                    @Override
                    public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {

                        if (response.isSuccessful()) {
                            listJobTitle = response.body();
                            for (int i = 0; i < listJobTitle.size(); i++) {

                                if (listJobTitle.get(i).getId() == idJob){
                                    tvJob.setText(listJobTitle.get(i).getName());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

                    }
                });

    }

}