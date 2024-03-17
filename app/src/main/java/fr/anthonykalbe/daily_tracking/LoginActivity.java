package fr.anthonykalbe.daily_tracking;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    String token;
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
                    String result = login(id_content, pwd_content);
                       System.out.print(result);
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

    public String login(String id, String pwd){
        ApiManager apiManager = new ApiManager(getApplicationContext());
        String url = "http://10.0.2.2/login";
        Map<String, String> params = new HashMap<>();
        params.put("password", pwd);
        params.put("email", id);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters,
                response -> {
                    try {
                        if (response.getString("status").equals("success")) {
                            token = response.getString("data");
                            return;
                        }
                        this.token = response.getString("data").toString();
                    } catch (JSONException e) {
                        System.out.println(e);
                    }
                },
                error -> {
                    makeText(getApplicationContext(), "Erreur lors de la connexion", LENGTH_LONG).show();
                }
        );
        apiManager.queue.add(jsonObjectRequest);
        return token;
    }


    private void AddPreferencesLoginInfo(String result)  {
        JSONObject jsonresult = null;
        try {
            jsonresult = new JSONObject(result);
            result = jsonresult.getString("token");
        }
        catch (JSONException e){
            System.out.println(e);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", result);
        editor.apply();
        return;

    }
    public static boolean CheckisLoggedIn(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String valeur = sharedPreferences.getString("token", "false");
         if(!valeur.equals("false")){
             return true;
         }
        return false;

    }

    public static void logout(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        makeText(context, "Vous avez été déconnecté", LENGTH_LONG).show();
    }
}