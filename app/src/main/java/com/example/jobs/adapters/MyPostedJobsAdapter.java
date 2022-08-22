package com.example.jobs.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.ArchiveFragment;
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

        //ArrayList<MyPostedJobs.JobStatusDTO> jobStatusDTOS;

        Log.e("JopState", "onBindViewHolder: "+myJobs.job.job_status_id );

//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, OrderExecutionActivity.class);
//                intent.putExtra("id",ss.id);
//                intent.putExtra("user_id",ss.user_id);
//                intent.putExtra("title",ss.title);
//                intent.putExtra("description",ss.description);
//                intent.putExtra("city",ss.city.name);
//                intent.putExtra("district",ss.district.name);
//                intent.putExtra("is_saved",ss.is_saved);
//                context.startActivity(intent);
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle , tvState ;
        Spinner spState;
        ImageView imgDelete;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            spState = itemView.findViewById(R.id.spState);
            tvState = itemView.findViewById(R.id.tvState);
            imgDelete = itemView.findViewById(R.id.imgDelete);




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

}
