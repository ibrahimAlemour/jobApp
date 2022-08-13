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

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatecoryViewHolder> {

    Context context ;
    ArrayList<Category> categories ;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CatecoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.category_item , parent , false);
        return new CatecoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatecoryViewHolder holder, int position) {

        final  Category  category = categories.get(position);


        //holder.img_tv.setImageResource(ss.getImg());
        holder.jobTitle_tv.setText(category.getName());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CatecoryViewHolder extends RecyclerView.ViewHolder {
        TextView   jobTitle_tv ;
        ImageView img_tv ;

        public CatecoryViewHolder(@NonNull View itemView) {
            super(itemView);

            img_tv =  itemView.findViewById(R.id.imgv);
            jobTitle_tv = itemView.findViewById(R.id.jobTitle);




        }
    }
}
