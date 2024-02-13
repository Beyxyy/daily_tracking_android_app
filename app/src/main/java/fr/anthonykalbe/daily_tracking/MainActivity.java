package fr.anthonykalbe.daily_tracking;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Button buttonClick;

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity login = new LoginActivity();
        if(!login.CheckisLoggedIn()){
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }

        bottomNavigationView = findViewById(R.id.bot_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_message :
                    moveToFragment(new HomeFragment());
                    break;

                case R.id.nav_home:
                    moveToFragment(new HomeFragment());
                    break;

                case R.id.nav_profile:
                    moveToFragment(new ProfileFragment());
                    break;
            }

            MenuItem menuitem = findViewById(R.id.nav_home);
            return true;
        });

    }


    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment, fragment);
    }
}