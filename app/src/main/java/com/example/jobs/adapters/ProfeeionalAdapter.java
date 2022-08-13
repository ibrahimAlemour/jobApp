package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.Category;
import com.example.jobs.model.ProfeeionalType;

import java.util.ArrayList;

public class ProfeeionalAdapter extends RecyclerView.Adapter<ProfeeionalAdapter.profeeionalTypes> {

    Context context ;
    ArrayList<ProfeeionalType> profeeionalTypes ;

    public ProfeeionalAdapter(Context context, ArrayList<ProfeeionalType> profeeionalTypes) {
        this.context = context;
        this.profeeionalTypes = profeeionalTypes;
    }

    @NonNull
    @Override
    public profeeionalTypes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.show_item , parent , false);
        return new profeeionalTypes(v);
    }

    @Override
    public void onBindViewHolder(@NonNull profeeionalTypes holder, int position) {

        final  ProfeeionalType  ss = profeeionalTypes.get(position);


        holder.img_tv.setImageResource(ss.getImg());

        holder.name_tv.setText(ss.getName());
        holder.place_tv.setText(ss.getPlace());
        holder.avaliable_tv.setText(ss.getAvaliable());
        holder.rate.setRating(Float.parseFloat(ss.getRate()));




    }

    @Override
    public int getItemCount() {
        return profeeionalTypes.size();
    }

    class profeeionalTypes extends RecyclerView.ViewHolder {
        TextView   name_tv , place_tv , avaliable_tv ;
        RatingBar rate ;


        ImageView img_tv ;

        public profeeionalTypes(@NonNull View itemView) {
            super(itemView);

            img_tv =  itemView.findViewById(R.id.imageView);
            rate =  itemView.findViewById(R.id.rating2);
            name_tv = itemView.findViewById(R.id.namee);
            place_tv = itemView.findViewById(R.id.placee);
            avaliable_tv = itemView.findViewById(R.id.avaliables);




        }
    }
}
