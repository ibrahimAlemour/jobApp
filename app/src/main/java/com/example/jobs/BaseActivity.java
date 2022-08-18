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
import android.widget.Toast;

import com.example.jobs.adapters.NotificationsAdapter;
import com.example.jobs.api.ApiInterface;
import com.example.jobs.api.RetrofitClient;
import com.example.jobs.model.Notifications;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {
    ArrayList<Notifications> listNotifications = new ArrayList<>();
    TextView v;
    static View badge;
    static TextView bNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        v= findViewById(R.id.tvTitleToolbar);
        v.setText("الرئيسية");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigationview);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }


        //  How to Show Badges
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
                        case R.id.homeFragment:
                            v.setText("الرئيسية");
                            selectedFragment = new HomeFragment();

                            break;
                        case R.id.findUserFragment:
                            selectedFragment = new ArchiveFragment();
                            v.setText("المحفوظات");

                            break;
                        case R.id.order:
                            selectedFragment = new TalabatyFragment();
                            v.setText("طلباتي");

                            break;

                        case R.id.notification:
                            selectedFragment = new NotificationFragment();
                            v.setText("الإشعارات");

                            break;

                        case R.id.listingsFragment:
                            selectedFragment = new ProfileFragment();
                            v.setText("الملف الشخصي");

                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };






}