package com.example.jobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Notifications;
import com.example.jobs.model.Notify;
import com.example.jobs.model.UserProfile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotifyViewHolder> {

    Context context;
    ArrayList<Notifications> notifications;

    public NotificationsAdapter(Context context, ArrayList<Notifications> notifies) {
        this.context = context;
        this.notifications = notifies;
    }

    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.notify_item, parent, false);
        return new NotifyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {

        final Notifications notification = notifications.get(position);


        // holder.name_tv.setText(notification.subject);
        getUserById(holder, notification.user_id);
        holder.text_tv.setText(notification.subject);

        holder.text_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + notification.created_at, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class NotifyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_tv, text_tv;

        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.notify_name);
            text_tv = itemView.findViewById(R.id.notify_text);


        }
    }

    private void getUserById(NotifyViewHolder holder, int user_id) {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getUserById(user_id)
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

                        if (response.isSuccessful()) {
                            holder.name_tv.setText(response.body().name);

                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {

                    }
                });
    }
}
