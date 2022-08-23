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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobs.adapters.AdapterCity;
import com.example.jobs.adapters.AdapterDistrict;
import com.example.jobs.adapters.CategoryAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.City;
import com.example.jobs.model.District;
import com.example.jobs.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompleteInfoActivity extends AppCompatActivity {

    ArrayList<City> list = new ArrayList<>();
    ArrayList<City> listJobTitle = new ArrayList<>();
    ArrayList<City> listJobType = new ArrayList<>();
    ArrayList<District> districtList = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    AdapterCity adapter;
    AdapterDistrict adapterDistrict;
    int id_city;
    int id_dis;
    int id_jobTitle;
    int id_jobType;
    ProgressDialog pd;
    private CircleImageView imgProfile;
    private CircleImageView editProfilePictureButton;
    private Spinner spJobTitle;
    private EditText etDescription;
    private Spinner spJopType;
    private Spinner spCity;
    private Spinner spDistrict;
    private Button confirmButton;
    private EditText etName;
    private EditText etPhoneNum;

    private EditText etPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_info);
        initView();

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("جاري تحديث البيانات ...");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String email = bundle.getString("email");
            String phone = bundle.getString("phone");
            String aboutMe = bundle.getString("aboutMe");


            etDescription.setText(aboutMe);
            etName.setText(name);
            etPhoneNum.setText(phone);


            getJobType();
            spJopType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    id_jobType = listJobType.get(i).getId();
                    Log.e("id_jobType", id_jobType + "");


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            getJobTitle();
            spJobTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    id_jobTitle = listJobTitle.get(i).getId();
                    Log.e("id_jobTitle", id_jobTitle + "");


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

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


            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String aboutMe = etDescription.getText().toString().trim();
                    String nameEmp = etName.getText().toString().trim();
                    String phoneEmp = etPhoneNum.getText().toString().trim();
                    if (aboutMe.isEmpty()) {
                        Toast.makeText(CompleteInfoActivity.this, "الرجاء تعبئة الوصف !", Toast.LENGTH_SHORT).show();
                    } else {

                        updateProfile(nameEmp, phoneEmp, "PUT", aboutMe, id_jobType, id_jobTitle, id_city, id_dis, 1);
                    }
                }
            });


        }




    }

    private void updateProfile(String name, String phone, String method, String aboutMe, int job_type_id, int job_title_id, int city_id, int district_id, int is_available) {

        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .updateProfileEmpNormal(name, phone, method, aboutMe, job_type_id, job_title_id, city_id, district_id, is_available)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(CompleteInfoActivity.this, "تم تحديث بياناتك ", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });



    }

    private void getJobType() {

        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getJopsType()
                .enqueue(new Callback<ArrayList<City>>() {
                    @Override
                    public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {

                        if (response.isSuccessful()) {
                            listJobType = response.body();
                            for (int i = 0; i < listJobType.size(); i++) {

                                Log.e("JobsType", "onResponse: " + listJobType.get(i).getName());
                                adapter = new AdapterCity(CompleteInfoActivity.this, listJobType);
                                spJopType.setAdapter(adapter);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

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

                                adapter = new AdapterCity(CompleteInfoActivity.this, listJobTitle);
                                spJobTitle.setAdapter(adapter);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

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

                                adapter = new AdapterCity(CompleteInfoActivity.this, list);
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

                                adapterDistrict = new AdapterDistrict(CompleteInfoActivity.this, districtList);
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
        imgProfile = (CircleImageView) findViewById(R.id.imgProfile);
        editProfilePictureButton = (CircleImageView) findViewById(R.id.edit_profile_picture_button);
        spJobTitle = (Spinner) findViewById(R.id.spJobTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        spJopType = (Spinner) findViewById(R.id.spJopType);
        spCity = (Spinner) findViewById(R.id.spCity);
        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        etName = (EditText) findViewById(R.id.etName);
        etPhoneNum = (EditText) findViewById(R.id.etPhoneNum);
        etPath = (EditText) findViewById(R.id.etPath);
    }

}