package com.example.jobs;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.MsSaveJob;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoActivity extends AppCompatActivity {

    private ConstraintLayout linear;
    private CircleImageView imgPerson;
    private TextView tvName;
    private TextView tvJob;
    private TextView tv1ui;
    private TextView tvAboutMe;
    private TextView tv2ui;
    private RatingBar ratingBar;
    private Button btnInventions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

           tvName.setText(bundle.getString("name"));
           tvAboutMe.setText(bundle.getString("me"));


        btnInventions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postInvention(bundle.getInt("idEmp"),bundle.getInt("idJob"));
            }
        });

        }




    }

    private void postInvention(int idEmp , int idJop) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .JobInvention(idJop,idEmp)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()){

                            Toast.makeText(UserInfoActivity.this, "تم ارسال الدعوة", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });
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
}