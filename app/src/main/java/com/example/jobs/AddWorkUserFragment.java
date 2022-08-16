package com.example.jobs;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class AddWorkUserFragment extends Fragment {
    Spinner spinerCity,spDistrict;
    ArrayList<City> list = new ArrayList<>();
    ArrayList<District> districtList = new ArrayList<>();
    AdapterCity adapter;
    AdapterDistrict adapterDistrict;
    int id_city;
    int id_dis;
    Button btnAddJob;
    EditText etDes,etTitle;
    ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_work_user, container, false);

        spinerCity = v.findViewById(R.id.area);
        spDistrict = v.findViewById(R.id.spDistrict);
        btnAddJob = v.findViewById(R.id.btnAddJob);
        etDes = v.findViewById(R.id.etDes);
        etTitle = v.findViewById(R.id.etTitle);

        //Dialog
        pd = new ProgressDialog(getContext());
        pd.setMessage("جاري نشر الطلب ...");

        getCity();


        spinerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String des = etDes.getText().toString().trim();


                if (title.isEmpty() || des.isEmpty()){
                    Toast.makeText(getContext(), "الرجاء تعبئة جميع البيانات !", Toast.LENGTH_SHORT).show();
                }else {

                    addJob(title,des,id_city,2);

                }

            }
        });





        return v;
    }

    private void addJob(String title , String des , int city, int dis) {
        pd.show();
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .AddJob(title,des,city,dis,1)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()){

                            Toast.makeText(getContext(), "تم نشر طلبك", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {
                        Toast.makeText(getContext(), "هناك خطأ ما ", Toast.LENGTH_SHORT).show();
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

                                adapter = new AdapterCity(getActivity(), list);
                                spinerCity.setAdapter(adapter);


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

                                adapterDistrict = new AdapterDistrict(getActivity(), districtList);
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