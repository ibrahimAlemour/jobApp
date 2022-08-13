package com.example.jobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {
    TextView v;

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