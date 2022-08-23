package com.example.jobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Notifications;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseUserActivity extends AppCompatActivity {

    ArrayList<Notifications> listNotifications = new ArrayList<>();
    static View badge;
    static TextView bNotification;
    TextView s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_user);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        s = findViewById(R.id.tvTitleToolbar);
        s.setText("الرئيسية");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigationvieww);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentuser_container,
                    new HomeUserFragment()).addToBackStack("").commit();
        }

        // Show Badges
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNav.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        badge = LayoutInflater.from(this).inflate(R.layout.notification_badge, itemView, true);
        bNotification = badge.findViewById(R.id.notifications);

        getMyNotifications();

    }

    private void getMyNotifications() {
        RetrofitClient.getRetrofitInstance()
                .create(ApiInterface.class)
                .getMyNotifications()
                .enqueue(new Callback<ArrayList<Notifications>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Notifications>> call, Response<ArrayList<Notifications>> response) {

                        List<String> noty = new ArrayList<>();
                        if (response.isSuccessful()) {
                            listNotifications = response.body();
                            Log.e("countNoty", "onResponse: "+listNotifications.size() );
                            for (int i = 0; i < listNotifications.size(); i++) {

                                noty.add(listNotifications.get(i).subject);

                                if (noty.size() == 0){
                                    bNotification.setVisibility(View.GONE);
                                }else {
                                    bNotification.setText(noty.size()+"");
                                    bNotification.setVisibility(View.VISIBLE);
                                }

                            }

                            if (listNotifications.size() == 0){
                                // Toast.makeText(getContext(), "لا يوجد اشعارات حتى الان ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Notifications>> call, Throwable t) {

                    }
                });
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.homeuserFragment:
                            s.setText("الرئيسية");
                            selectedFragment = new HomeUserFragment();

                            break;

                        case R.id.notyFragment:
                            s.setText("الإشعارات");
                            selectedFragment = new NotificationOwnerFragment();

                            break;

                        case R.id.addWork:
                            selectedFragment = new AddWorkUserFragment();
                            s.setText("إضافة عمل");

                            break;


                        case R.id.talabatyuser:
                            selectedFragment = new TalabatyUserFragment();
                            s.setText("طلباتي");

                            break;


                        case R.id.profile_user_Fragment:
                            selectedFragment = new ProfileUserFragment();
                            s.setText("الملف الشخصي");

                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentuser_container,
                            selectedFragment).addToBackStack("").commit();

                    return true;
                }
            };
}