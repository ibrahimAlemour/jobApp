package com.example.jobs.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.EmpInformationActivity;
import com.example.jobs.R;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.City;
import com.example.jobs.model.EmpSendTalab;
import com.example.jobs.model.OwnerReceiveTalab;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReceiveJobsAdapter extends RecyclerView.Adapter<MyReceiveJobsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<OwnerReceiveTalab> list;

    public MyReceiveJobsAdapter(Context context, ArrayList<OwnerReceiveTalab> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talaby_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final OwnerReceiveTalab myJobs = list.get(position);

       // getJopStatus(holder,myJobs.job.job_status_id);
        holder.tvTitle.setText(myJobs.user.name);
        holder.tvState.setText(myJobs.description);



        Log.e("JopState", "onBindViewHolder: "+myJobs.job.job_status_id );

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EmpInformationActivity.class);
                intent.putExtra("name",myJobs.user.name);
                intent.putExtra("emp_user_id",myJobs.emp_user_id);
                intent.putExtra("job_id",myJobs.job_id);
                intent.putExtra("job_status_id",myJobs.job.job_status_id);
                intent.putExtra("description",myJobs.description);
                intent.putExtra("price",myJobs.price);
                intent.putExtra("about_meEmp",myJobs.user.about_me);
                intent.putExtra("rate",myJobs.user.rating);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle , tvState ;
        Spinner spState;
        LinearLayout container;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            spState = itemView.findViewById(R.id.spState);
            tvState = itemView.findViewById(R.id.tvState);
            container = itemView.findViewById(R.id.container);




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
