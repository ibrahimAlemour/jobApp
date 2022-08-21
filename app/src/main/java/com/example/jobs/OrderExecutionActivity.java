package com.example.jobs;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.MsSaveJob;
import com.example.jobs.model.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private EditText etProposal;
    private EditText etPrice;
    ProgressDialog pd;
    private static final int REQUEST_CALL = 1;
    private TextView tvPhone;

    boolean isPhoneCall = false;
    ActivityResultLauncher<String[]> mPLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_execution);
        initView();

        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("جاري الإرسال ...");

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            int user_id = bundle.getInt("user_id");
            getUserById(user_id);
            id = bundle.getInt("job_id");

            Log.e("JobID", "onCreate: " + id);

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

                    String proposal = etProposal.getText().toString().toString();
                    int price = Integer.parseInt(etPrice.getText().toString().toString());
                    runJob(proposal, price, id);

                }
            });

        }


        //PermissionCallPhone
        callPhone();

    }


    private void initView() {
        tvTitleToolbar = (TextView) findViewById(R.id.tvTitleToolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvNameOwner = (TextView) findViewById(R.id.tv_NameOwner);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        imgSave = (ImageView) findViewById(R.id.imgSave);
        btnRunJob = (AppCompatButton) findViewById(R.id.btnStartJob);
        etProposal = (EditText) findViewById(R.id.etProposal);
        etPrice = (EditText) findViewById(R.id.etPrice);
        tvPhone = (TextView) findViewById(R.id.tv_Phone);
    }

    private void runJob(String des, int price, int job_id) {

        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .ApplicationJob(job_id, des, price)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        Log.e("JobApplication", "onResponse: " + response.body().message);
                        if (response.isSuccessful()) {

                            Toast.makeText(OrderExecutionActivity.this, "تم إرسال العرض ", Toast.LENGTH_SHORT).show();
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
                            tvPhone.setText(response.body().phone);
                            Log.e("PhoneOwner", "onResponse: " + response.body().phone);


                            tvNameOwner.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Toast.makeText(OrderExecutionActivity.this, "جاري الإتصال", Toast.LENGTH_SHORT).show();

                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    callIntent.setData(Uri.parse("tel:" + response.body().phone));
                                    startActivity(callIntent);

                                }
                            });

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

    private void callPhone(){


        mPLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

                if (result.get(Manifest.permission.CALL_PHONE)!= null){

                    isPhoneCall = result.get(Manifest.permission.CALL_PHONE);
                }

            }
        });

        requestPermissions();
    }


    private void requestPermissions(){
        isPhoneCall = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE)==   PackageManager.PERMISSION_GRANTED;

        List<String> pRequest = new ArrayList<String>();

        if (!isPhoneCall){
            pRequest.add(Manifest.permission.CALL_PHONE);
        }

        if (!pRequest.isEmpty()){

            mPLauncher.launch(pRequest.toArray(new String[0]));
        }

    }

}