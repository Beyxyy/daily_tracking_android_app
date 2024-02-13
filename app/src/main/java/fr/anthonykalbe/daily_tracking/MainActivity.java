package fr.anthonykalbe.daily_tracking;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
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

    private final int nav_message = R.id.nav_message;
    private final int nav_home = R.id.nav_home;
    private final int nav_profile = R.id.nav_profile;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity login = new LoginActivity();
        if(!LoginActivity.CheckisLoggedIn(getApplicationContext())){
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

            return;
        }
        setContentView(R.layout.activity_main);
        moveToFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bot_nav);

        bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId(); // Récupère l'ID de l'élément

                if (itemId == R.id.nav_message) {
                    moveToFragment(new HomeFragment());
                } else if (itemId == R.id.nav_home) {
                    moveToFragment(new HomeFragment());
                } else if (itemId == R.id.nav_profile) {
                    moveToFragment(new ProfileFragment());
                }

            return true;
        });

    }


    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment, fragment).commit();
    }
}