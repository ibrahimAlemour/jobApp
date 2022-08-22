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

public class EditJobActivity extends AppCompatActivity {

    ArrayList<City> list = new ArrayList<>();
    ArrayList<District> districtList = new ArrayList<>();
    AdapterCity adapter;
    AdapterDistrict adapterDistrict;
    int id_city;
    int id_dis;
    ProgressDialog pd;

    private EditText etTitle;
    private EditText etDes;
    private TextView available;
    private Spinner spCity;
    private Spinner spDistrict;
    private Button btnAddJob;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        initView();


        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("انتظر لحظة ...");

        getCity();


        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            int idJob = bundle.getInt("idJob");
            etTitle.setText(bundle.getString("titleJob"));
            etDes.setText(bundle.getString("desJob"));

            btnAddJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    editJob(idJob, etTitle.getText().toString(),
                            etDes.getText().toString(),
                            id_city, id_dis);
                }
            });


            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cancelJob(idJob);
                }
            });


        }


    }



    private void cancelJob(int idJob) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .jobCancel(idJob)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(EditJobActivity.this, "تم الغاء الوظيفة", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                    }
                });

    }

    private void editJob(int idJob, String title, String des, int id_city, int id_dis) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .editJob(idJob, title, des, id_city, id_dis, 1)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(EditJobActivity.this, "تم تعديل الوظيفة", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

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

                                adapter = new AdapterCity(EditJobActivity.this, list);
                                spCity.setAdapter(adapter);


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

                                adapterDistrict = new AdapterDistrict(EditJobActivity.this, districtList);
                                spDistrict.setAdapter(adapterDistrict);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<District>> call, Throwable t) {

                    }
                });
    }


    private void initView() {
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDes = (EditText) findViewById(R.id.etDes);
        available = (TextView) findViewById(R.id.available);
        spCity = (Spinner) findViewById(R.id.area);
        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        btnAddJob = (Button) findViewById(R.id.btnAddJob);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

}