package fr.anthonykalbe.daily_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                    ApiManager apiManager = new ApiManager();
                    JSONObject result = apiManager.login(id_content,pwd_content);
                    if(result == null){
                        System.out.print("Erreur pendant la connection");
                    }
                    else{
                        AddPreferencesLoginInfo(result);
                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }

                }

            }
        });
    }


    private void AddPreferencesLoginInfo(JSONObject result){
        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", result.toString());
        editor.apply();
        return;

    }
    public static boolean CheckisLoggedIn(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("NomDeTesPreferences", Context.MODE_PRIVATE);
        String valeur = sharedPreferences.getString("isLoggedIn", "false");
         if(!valeur.equals("false")){
             return true;
         }
        return false;

    }
}