package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.model.Category;
import com.example.jobs.model.Talab;

import java.util.ArrayList;

public class TalabAdapter extends RecyclerView.Adapter<TalabAdapter.TalabViewHolder> {

    Context context ;
    ArrayList<Talab> talabs ;

    public TalabAdapter(Context context, ArrayList<Talab> talabs) {
        this.context = context;
        this.talabs = talabs;
    }

    @NonNull
    @Override
    public TalabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.talaby_item , parent , false);
        return new TalabViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TalabViewHolder holder, int position) {

        final  Talab  ss = talabs.get(position);


        //holder.Status_tv.getSelectedItem();


//       holder.Status_tv.setSelection(ss.getStatus());
//
//
//        holder.name_tv.setText(ss.getName());

    }

    @Override
    public int getItemCount() {
        return talabs.size();
    }

    class TalabViewHolder extends RecyclerView.ViewHolder {
        TextView   name_tv ;

        Spinner Status_tv ;

        public TalabViewHolder(@NonNull View itemView) {
            super(itemView);
//
//            name_tv =  itemView.findViewById(R.id.talabb);
//            Status_tv = itemView.findViewById(R.id.talabss);




        }
    }
}
