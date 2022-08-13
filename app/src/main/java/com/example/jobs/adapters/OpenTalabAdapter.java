package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.Archive;
import com.example.jobs.model.OpenTalab;

import java.util.ArrayList;

public class OpenTalabAdapter extends RecyclerView.Adapter<OpenTalabAdapter.OpenTalabsViewHolder> {

    Context context ;
    ArrayList<OpenTalab> openTalabs ;

    public OpenTalabAdapter(Context context, ArrayList<OpenTalab> openTalabs) {
        this.context = context;
        this.openTalabs = openTalabs;
    }

    @NonNull
    @Override
    public OpenTalabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.opentalab_item , parent , false);
        return new OpenTalabsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenTalabsViewHolder holder, int position) {

        final  OpenTalab  ss = openTalabs.get(position);


        holder.name_tv.setText(ss.getName());
        holder.text_tv.setText(ss.getText());

    }

    @Override
    public int getItemCount() {
        return openTalabs.size();
    }

    class OpenTalabsViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv , text_tv ;

        public OpenTalabsViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.opentalab_name);
            text_tv = itemView.findViewById(R.id.opentalab_text);




        }
    }
}
