package com.example.jobs.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.MyPostedJobs;
import com.example.jobs.model.SavedJobs;

import java.util.ArrayList;

public class MyPostedJobsAdapter extends RecyclerView.Adapter<MyPostedJobsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<MyPostedJobs> myPostedJobs;

    public MyPostedJobsAdapter(Context context, ArrayList<MyPostedJobs> myPostedJobs) {
        this.context = context;
        this.myPostedJobs = myPostedJobs;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talaby_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final MyPostedJobs myJobs = myPostedJobs.get(position);


        holder.tvTitle.setText(myJobs.title);
        holder.tvState.setText(myJobs.job_status.name);

        //ArrayList<MyPostedJobs.JobStatusDTO> jobStatusDTOS;

        Log.e("JopState", "onBindViewHolder: "+myJobs.job_status.name );

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
