package fr.anthonykalbe.daily_tracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    JSONObject data= new JSONObject();

    String actualExercice="";

    String actualSession="";

    JSONObject set = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!LoginActivity.CheckisLoggedIn(getApplicationContext())){
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();
            return;
        }
        createData();
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


    public JSONObject createData(){
        this.data = new JSONObject();
        return this.data;
    }
    public void setData(String key, String value) throws JSONException {
        try{
            this.data.put(key, value);
        }
        catch(JSONException e){
            System.out.println("Erreur lors de l'ajout de la donnée au JSON");
        }

    }

    public String getActualExercice(){ return this.actualExercice; }

    public void setActualExercice(String exercice) {
        this.actualExercice = exercice;
        try {

            if(this.data.has(this.getActualSession())){
                JSONArray actualSessionData = (JSONArray) this.data.getJSONArray(this.getActualSession());
                JSONObject newObject = new JSONObject().put(exercice, new JSONArray());
                //actualSessionData.put(newObject);
                boolean already_enregistred = false;
                Integer length = actualSessionData.length();
                System.out.print(length);
                if(length == 0){
                    actualSessionData.put(newObject);
                }
                else{
                    for (int i = 0; i<actualSessionData.length(); i++){
                        if(actualSessionData.getJSONObject(i).has(exercice)){
                            already_enregistred = true;
                        }
                    }
                    if(!already_enregistred) {
                        actualSessionData.put(newObject);
                        System.out.print(this.data);
                    }
                    else{
                        System.out.print("already enregistred");

                    }
                }

            }
        } catch (JSONException e) {
            System.out.println("Error while adding data to JSON");
        }
    }

    public String getActualSession(){ return this.actualSession; }

    public void setActualSession(String session) {
        this.actualSession = session;
        try{
            //check if session exists un this.data
            if(!this.data.has(session)){
                this.data.put(session, new JSONArray());
            }
        }
        catch(JSONException e){
            System.out.println("Erreur lors de l'ajout de la donnée au JSON");
        }

    }

    public JSONObject getData(){
        return this.data;
    }


    public void setSet(String reps, String weight) throws JSONException {
        //JSONArray actualSessionData = this.data.getJSONArray(this.getActualSession()).getJSONObject(0).getJSONArray(this.getActualExercice());
        JSONArray actualSessionData = this.data.getJSONArray(this.getActualSession());
        System.out.print(actualSessionData);
                //.getJSONArray(this.getActualExercice());
        System.out.print(actualSessionData.length());
        System.out.print(actualSessionData);
        for(int i = 0; i<actualSessionData.length(); i++){
            JSONObject a = actualSessionData.getJSONObject(i);
            if(a.has(this.getActualExercice())){
                actualSessionData = actualSessionData.getJSONObject(i).getJSONArray(this.getActualExercice());
                JSONObject newExercice = new JSONObject().put("weight", weight).put("reps", reps);
                actualSessionData.put(newExercice);
            }
        }
    }

}