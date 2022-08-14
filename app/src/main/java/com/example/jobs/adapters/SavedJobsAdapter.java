package com.example.jobs.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.OrderExecutionActivity;
import com.example.jobs.R;
import com.example.jobs.model.JobsOpen;
import com.example.jobs.model.SavedJobs;

import java.util.ArrayList;

public class SavedJobsAdapter extends RecyclerView.Adapter<SavedJobsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<SavedJobs> savedJobs ;

    public SavedJobsAdapter(Context context, ArrayList<SavedJobs> savedJobs) {
        this.context = context;
        this.savedJobs = savedJobs;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.notify_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final SavedJobs svJobs = savedJobs.get(position);


        holder.name_tv.setText(svJobs.job.title);
        holder.text_tv.setText(svJobs.job.description);

        Log.e("JopOpen", "onBindViewHolder: "+svJobs.job.title );

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
        return savedJobs.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv , text_tv ;
        CardView container;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.notify_name);
            text_tv = itemView.findViewById(R.id.notify_text);
            container = itemView.findViewById(R.id.container);




        }
    }
}
