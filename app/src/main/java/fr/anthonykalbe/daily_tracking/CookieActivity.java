package fr.anthonykalbe.daily_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CookieActivity extends AppCompatActivity {


    private Button nm_point;
    private TextView points;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);
        this.nm_point = findViewById(R.id.gain_point);
        this.points = findViewById(R.id.result_point);
        nm_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                points.setText("Points : "+ counter);
                System.out.print(counter);
            }
        });
    }
}