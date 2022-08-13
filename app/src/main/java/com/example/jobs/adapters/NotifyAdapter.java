package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.Notify;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder> {

    Context context ;
    ArrayList<Notify> notifies ;

    public NotifyAdapter(Context context, ArrayList<Notify> notifies) {
        this.context = context;
        this.notifies = notifies;
    }

    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.notify_item , parent , false);
        return new NotifyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {

        final  Notify  ss = notifies.get(position);


        holder.name_tv.setText(ss.getName());
        holder.text_tv.setText(ss.getText());

    }

    @Override
    public int getItemCount() {
        return notifies.size();
    }

    class NotifyViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv , text_tv ;

        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.notify_name);
            text_tv = itemView.findViewById(R.id.notify_text);




        }
    }
}
