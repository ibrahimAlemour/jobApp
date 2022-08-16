package com.example.jobs.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.UserInfoActivity;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Category;
import com.example.jobs.model.City;
import com.example.jobs.model.UserInJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInJobAdapter extends RecyclerView.Adapter<UserInJobAdapter.CatecoryViewHolder> {

    Context context;
    ArrayList<UserInJob> userInJobs;
    ArrayList<City> cityArrayList;

    public UserInJobAdapter(Context context, ArrayList<UserInJob> userInJobs) {
        this.context = context;
        this.userInJobs = userInJobs;
    }

    @NonNull
    @Override
    public CatecoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.show_item, parent, false);
        return new CatecoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatecoryViewHolder holder, int position) {

        final UserInJob user = userInJobs.get(position);


        getCity(holder, user.getCity_id());
        holder.tv_name.setText(user.getName());
        //holder.tvPlace.setText(user.get());
        if (user.getIs_available() == 1) {
            holder.tvAvaliables.setText("متاح");
        } else {
            holder.tvAvaliables.setText("مشغول");
        }


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("me",user.getAbout_me());
                intent.putExtra("idJob",user.getJob_title_id());
                intent.putExtra("idEmp",user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userInJobs.size();
    }

    class CatecoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tvAvaliables, tvPlace;
        CardView container;

        public CatecoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tvAvaliables = itemView.findViewById(R.id.tvAvaliables);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            container = itemView.findViewById(R.id.container);


        }
    }

    private void getCity(CatecoryViewHolder holder, int id) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getCity()
                .enqueue(new Callback<ArrayList<City>>() {
                    @Override
                    public void onResponse(Call<ArrayList<City>> call, Response<ArrayList<City>> response) {

                        if (response.isSuccessful()) {
                            for (int i = 0; i < response.body().size(); i++) {

                                if (response.body().get(i).getId() == id) {
                                    Log.e("TAG", "City: " + response.body().get(i).getName());

                                    holder.tvPlace.setText(response.body().get(i).getName());

                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<City>> call, Throwable t) {

                    }
                });
    }


}
