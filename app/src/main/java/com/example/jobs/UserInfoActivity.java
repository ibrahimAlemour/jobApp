package com.example.jobs;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends AppCompatActivity {

    private ConstraintLayout linear;
    private CircleImageView imgPerson;
    private TextView tvName;
    private TextView tvJob;
    private TextView tv1ui;
    private TextView tvAboutMe;
    private TextView tv2ui;
    private RatingBar ratingBar;
    private Button bt2;

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
        bt2 = (Button) findViewById(R.id.bt_2);
    }
}