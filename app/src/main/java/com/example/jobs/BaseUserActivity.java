package com.example.jobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseUserActivity extends AppCompatActivity {

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