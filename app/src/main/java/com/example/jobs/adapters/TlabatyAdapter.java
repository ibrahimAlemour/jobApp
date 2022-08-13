package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.Category;
import com.example.jobs.model.Talbaty;

import java.util.ArrayList;

public class TlabatyAdapter extends RecyclerView.Adapter<TlabatyAdapter.CatecoryViewHolder> {

    Context context ;
    ArrayList<Talbaty> talbaties ;

    public TlabatyAdapter(Context context, ArrayList<Talbaty> talbaties) {
        this.context = context;
        this.talbaties = talbaties;
    }

    @NonNull
    @Override
    public CatecoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talabaty_item , parent , false);
        return new CatecoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatecoryViewHolder holder, int position) {

        final  Talbaty  ss = talbaties.get(position);


        holder.status_tv.setText(ss.getStatus());
        holder.jobTitle_tv.setText(ss.getJobTitle());

    }

    @Override
    public int getItemCount() {
        return talbaties.size();
    }

    class CatecoryViewHolder extends RecyclerView.ViewHolder {
        TextView   jobTitle_tv , status_tv ;



        public CatecoryViewHolder(@NonNull View itemView) {
            super(itemView);

            status_tv =  itemView.findViewById(R.id.status);
            jobTitle_tv = itemView.findViewById(R.id.talabjob);




        }
    }
}
