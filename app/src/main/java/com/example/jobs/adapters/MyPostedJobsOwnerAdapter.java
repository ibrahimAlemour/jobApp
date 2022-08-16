package com.example.jobs.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.MyPostedJobs;

import java.util.ArrayList;

public class MyPostedJobsOwnerAdapter extends RecyclerView.Adapter<MyPostedJobsOwnerAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<MyPostedJobs> myPostedJobs;

    public MyPostedJobsOwnerAdapter(Context context, ArrayList<MyPostedJobs> myPostedJobs) {
        this.context = context;
        this.myPostedJobs = myPostedJobs;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talaby_owner_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final MyPostedJobs myJobs = myPostedJobs.get(position);


        holder.tvTitle.setText(myJobs.title);
        holder.tvState.setText(myJobs.job_status.name);

        Log.e("JopState", "onBindViewHolder: "+myJobs.job_status.name );

        if (myJobs.job_status.name.contains("منتهية")){

            holder.tvState.setBackgroundResource(R.color.m);
        } else if (myJobs.job_status.name.contains("قيد التنفيذ")){
            holder.tvState.setBackgroundResource(R.color.q);

        }else if (myJobs.job_status.name.contains("بانتظار المزود")){
            holder.tvState.setBackgroundResource(R.color.w);
        }


    }

    @Override
    public int getItemCount() {
        return myPostedJobs.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle , tvState ;
        Spinner spState;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            spState = itemView.findViewById(R.id.spState);
            tvState = itemView.findViewById(R.id.tvState);




        }
    }
}
