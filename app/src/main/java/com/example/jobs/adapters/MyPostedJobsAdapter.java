package com.example.jobs.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.ArchiveFragment;
import com.example.jobs.EditJobActivity;
import com.example.jobs.R;
import com.example.jobs.TalabatyFragment;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.City;
import com.example.jobs.model.EmpSendTalab;
import com.example.jobs.model.MsSaveJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostedJobsAdapter extends RecyclerView.Adapter<MyPostedJobsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<EmpSendTalab> list;

    public MyPostedJobsAdapter(Context context, ArrayList<EmpSendTalab> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talaby_emp_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final EmpSendTalab myJobs = list.get(position);

        getJopStatus(holder,myJobs.job.job_status_id);
        holder.tvTitle.setText(myJobs.job.title);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeJobs(myJobs.job_id);
            }
        });

        holder.btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog(myJobs.description,myJobs.price,myJobs.id);
            }
        });


        Log.e("JopState", "onBindViewHolder: "+myJobs.job.job_status_id );



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle , tvState ;
        Spinner spState;
        ImageView imgDelete;
        AppCompatButton btnDialog;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            spState = itemView.findViewById(R.id.spState);
            tvState = itemView.findViewById(R.id.tvState);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            btnDialog = itemView.findViewById(R.id.btnDialog);




        }
    }


    private void getJopStatus(OpenTalabsViewHolder holder , int idStatus) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getJobStatus()
                .enqueue(new Callback<ArrayList<City>>() {
                    @Override
                    public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {
                        ArrayList<City> list = new ArrayList<>();
                        if (response.isSuccessful()){
                            list = response.body();

                            for (int i = 0; i < list.size(); i++) {

                                if (list.get(i).getId() == idStatus ){
                                    holder.tvState.setText(list.get(i).getName());
                                    if (list.get(i).getName().contains("قيد التنفيذ") || list.get(i).getName().contains("منتهية")
                                            || list.get(i).getName().contains("ملغيه")
                                    ){
                                        holder.btnDialog.setVisibility(View.GONE);
                                    }
                                }
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

                    }
                });
    }

    private void removeJobs(int id) {
        TalabatyFragment.swipRefresh.setRefreshing(true);
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .DeleteJop(id)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful() && response != null) {

                            TalabatyFragment.listJobs.clear();
                            TalabatyFragment.getMyPostedJobs2();
                            TalabatyFragment.swipRefresh.setRefreshing(false);
                            TalabatyFragment.myPostedJobsAdapter.notifyDataSetChanged();


                            Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                            Log.e("Delete", "onResponse: "+response.body().message );

                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {
                        TalabatyFragment.swipRefresh.setRefreshing(false);
                        Log.e("Delete", "onResponse: "+t.getMessage() );
                    }
                });
    }


    void showCustomDialog(String description , int price , int idJob) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.edit_job_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final EditText etDescription = dialog.findViewById(R.id.etTitle);
        final EditText etPrice = dialog.findViewById(R.id.etPrice);
        AppCompatButton btnSave = dialog.findViewById(R.id.btnSave);

        etDescription.setText(description);
        etPrice.setText(price+"");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String descriptionLast = etDescription.getText().toString().trim();
                int priceLast = Integer.parseInt(etPrice.getText().toString());

                editJob(idJob,descriptionLast,priceLast);
                dialog.dismiss();

                //Toast.makeText(context, "تم تعديل العرض ", Toast.LENGTH_SHORT).show();


            }
        });


        dialog.show();
    }


    private void editJob(int idJob, String description, int price) {

        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .editJobEmp(idJob,description,price)
                .enqueue(new Callback<MsSaveJob>() {
                    @Override
                    public void onResponse(Call<MsSaveJob> call, Response<MsSaveJob> response) {

                        if (response.isSuccessful()) {

                            TalabatyFragment.getMyPostedJobs2();
                            TalabatyFragment.myPostedJobsAdapter.notifyDataSetChanged();

                            Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MsSaveJob> call, Throwable t) {

                        Log.e("EditJobEmp", "onFailure: "+t.getMessage() );
                    }
                });
    }




}
