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

import java.util.ArrayList;

public class OpenJopsAdapter extends RecyclerView.Adapter<OpenJopsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<JobsOpen> jopsOpens ;

    public OpenJopsAdapter(Context context, ArrayList<JobsOpen> jopsOpens) {
        this.context = context;
        this.jopsOpens = jopsOpens;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.notify_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final JobsOpen ss = jopsOpens.get(position);


        holder.name_tv.setText(ss.title);
        holder.text_tv.setText(ss.description);
        Log.e("JopOpenID", "onBindViewHolder: "+ss.id );

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OrderExecutionActivity.class);
                intent.putExtra("job_id",ss.id);
                intent.putExtra("user_id",ss.user_id);
                intent.putExtra("title",ss.title);
                intent.putExtra("description",ss.description);
                intent.putExtra("city",ss.city.name);
                intent.putExtra("district",ss.district.name);
                intent.putExtra("is_saved",ss.is_saved);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return jopsOpens.size();
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
