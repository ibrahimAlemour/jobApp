package com.example.jobs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobs.adapters.AdapterCity;
import com.example.jobs.adapters.AdapterDistrict;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.City;
import com.example.jobs.model.District;
import com.example.jobs.model.MsSaveJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendInvitationActivity extends AppCompatActivity {

    ArrayList<City> list = new ArrayList<>();
    ArrayList<District> districtList = new ArrayList<>();
    AdapterCity adapter;
    AdapterDistrict adapterDistrict;
    int id_city;
    int id_dis;
    ProgressDialog pd;
    private TextView tv1;
    private EditText etTitle;
    private TextView tv2;
    private EditText etDes;
    private TextView tv3;
    private TextView available;
    private Spinner area;
    private Spinner spDistrict;
    private Button btnAddJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_invitation);
        initView();

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("جاري الإرسال ...");

        getCity();




        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                id_city = list.get(i).getId();
                Log.e("city_id", id_city + "");
                getDistrict(id_city);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                id_dis = districtList.get(i).getId();
                Log.e("city_id", id_city + "");


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int empId = bundle.getInt("EmpId");
            btnAddJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title = etTitle.getText().toString().trim();
                    String des = etDes.getText().toString().trim();


                    if (title.isEmpty() || des.isEmpty()) {
                        Toast.makeText(SendInvitationActivity.this, "الرجاء تعبئة جميع البيانات !", Toast.LENGTH_SHORT).show();
                    } else {

                        addJob(title, des, id_city, id_dis,empId);

                    }

                }
            });

        }
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv_1);
        etTitle = (EditText) findViewById(R.id.etTitle);
        tv2 = (TextView) findViewById(R.id.tv_2);
        etDes = (EditText) findViewById(R.id.etDes);
        tv3 = (TextView) findViewById(R.id.tv_3);
        available = (TextView) findViewById(R.id.available);
        area = (Spinner) findViewById(R.id.area);
        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        btnAddJob = (Button) findViewById(R.id.btnAddJob);
    }

    private void addJob(String title , String des , int city, int dis,int emp_user_id) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .AddJobInvitation(title,des,city,dis,1,emp_user_id)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()){

                            Toast.makeText(SendInvitationActivity.this, "تم ارسال الدعوة", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {
                        Toast.makeText(SendInvitationActivity.this, "هناك خطأ ما ", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
    }


    private void getCity() {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getCity()
                .enqueue(new Callback<ArrayList<City>>() {
                    @Override
                    public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {

                        if (response.isSuccessful()) {

                            list = response.body();

                            for (int i = 0; i < list.size(); i++) {

                                Log.e("TAG", "City: " + response.body().get(i).getName());

                                adapter = new AdapterCity(SendInvitationActivity.this, list);
                                area.setAdapter(adapter);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

                    }
                });
    }

    private void getDistrict(int id) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getDistrict(id)
                .enqueue(new Callback<ArrayList<District>>() {
                    @Override
                    public void onResponse(Call<ArrayList<District>> call, Response<ArrayList<District>> response) {

                        if (response.isSuccessful()) {

                            districtList = response.body();

                            for (int i = 0; i < districtList.size(); i++) {

                                Log.e("TAG", "City: " + response.body().get(i).getName());

                                adapterDistrict = new AdapterDistrict(SendInvitationActivity.this, districtList);
                                spDistrict.setAdapter(adapterDistrict);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<District>> call, Throwable t) {

                    }
                });
    }
}