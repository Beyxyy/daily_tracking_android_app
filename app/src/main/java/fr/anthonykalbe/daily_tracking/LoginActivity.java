package fr.anthonykalbe.daily_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button submit = (Button) findViewById(R.id.submit_connexion);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView id = (TextView) findViewById(R.id.identifiant);
                TextView pwd = (TextView) findViewById(R.id.pwd);
                String id_content = id.getText().toString();
                String pwd_content = pwd.getText().toString();
                if(!id_content.isEmpty() && !pwd_content.isEmpty()){
                    System.out.println("Identifiant : " + id_content);
                    System.out.println("Mot de passe : " + pwd_content);
                    User user = new User(id_content, pwd_content);
                    if (user.checkExists()){
                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                }

            }
        });
    }


    private boolean AddPreferencesLoginInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("NomDeTesPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("isLoggedIn", "true");
        editor.apply();
        return true;

    }
    public boolean CheckisLoggedIn(){
        SharedPreferences sharedPreferences = getSharedPreferences("NomDeTesPreferences", Context.MODE_PRIVATE);
        String valeur = sharedPreferences.getString("isLoggedIn", "valeurParDefaut");
         if(valeur.equals("true")){
             return true;
         }
        return false;

    }
}