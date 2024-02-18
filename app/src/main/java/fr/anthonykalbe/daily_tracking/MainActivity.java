package fr.anthonykalbe.daily_tracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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