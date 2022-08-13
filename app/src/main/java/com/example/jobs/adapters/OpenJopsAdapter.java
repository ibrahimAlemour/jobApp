package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.JopsOpen;
import com.example.jobs.model.OpenTalab;

import java.util.ArrayList;

public class OpenJopsAdapter extends RecyclerView.Adapter<OpenJopsAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<JopsOpen> jopsOpens ;

    public OpenJopsAdapter(Context context, ArrayList<JopsOpen> jopsOpens) {
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

        final  JopsOpen  ss = jopsOpens.get(position);


        holder.name_tv.setText(ss.title);
        holder.text_tv.setText(ss.description);

    }

    @Override
    public int getItemCount() {
        return jopsOpens.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv , text_tv ;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.notify_name);
            text_tv = itemView.findViewById(R.id.notify_text);




        }
    }
}
