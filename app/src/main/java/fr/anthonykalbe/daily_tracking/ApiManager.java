package fr.anthonykalbe.daily_tracking;


import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;


public class ApiManager {
    String url = "10.0.2.2";
    String token;
    String content;


    public JSONObject login(String id, String pwd){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObjectRequest request = retrofit.create(JSONObjectRequest.class);
        return null;
    }

}
