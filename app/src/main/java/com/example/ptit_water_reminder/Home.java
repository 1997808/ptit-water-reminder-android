package com.example.ptit_water_reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        openFragment(HomeFragment.newInstance("",""));
                        return true;
                    case R.id.navigation_explore:
                        openFragment(ChartFragment.newInstance("",""));
                        return true;
                    case R.id.navigation_subscriptions:
                        openFragment(HistoryFragment.newInstance("",""));
                        return true;
                    case R.id.navigation_library:
                        openFragment(AlarmFragment.newInstance("", ""));
                        return true;
                }
                return false;
            }

            private void openFragment(Fragment fragment) {
//                Log.d(TAG, "openFragment: ");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //this is a helper class that replaces the container with the fragment. You can replace or add fragments.
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null); //if you add fragments it will be added to the backStack. If you replace the fragment it will add only the last fragment
                transaction.commit(); // commit() performs the action
            }
        });



    }


}