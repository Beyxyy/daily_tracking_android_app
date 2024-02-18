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
                    ApiManager apiManager = new ApiManager;
                    Map<String, Object> requestBody = new HashMap<>();
                    requestBody.put("identifiant", "ton_identifiant");
                    requestBody.put("password", "ton_mot_de_passe");
                    apiManager.loginUser(requestBody, new ApiManager.ApiCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            AddPreferencesLoginInfo();
                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);
                            apiManager.
                            finish();
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            // Gère les erreurs (par exemple, affiche un message d'erreur à l'utilisateur)
                            // Note : Ceci est exécuté dans le thread principal, assure-toi de ne pas bloquer l'interface utilisateur.
                        }
                    });
                }

            }
        });
    }


    private void AddPreferencesLoginInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("isLoggedIn", "true");
        editor.apply();
        return;

    }
    public static boolean CheckisLoggedIn(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("NomDeTesPreferences", Context.MODE_PRIVATE);
        String valeur = sharedPreferences.getString("isLoggedIn", "valeurParDefaut");
         if(valeur.equals("true")){
             return true;
         }
        return false;

    }
}