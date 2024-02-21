package fr.anthonykalbe.daily_tracking;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;



public class ApiManager {
    String url = "https://dailytracking.api.anthony-kalbe.fr/";
    String token;
    String content;
    OkHttpClient client = new OkHttpClient();

    public void ApiManager(){

    }

    public JSONObject login(String id, String pwd){
        content = "{\"Username\":\"" + id + "\",\"Pw\":\"" + pwd + "\"}";
        JSONObject result = executeRequest("/login", content);
        return result;
    }


    public JSONObject executeRequest(String endpoint, String content) {
        try {
            Request request = new Request.Builder()
                    .url(url + endpoint)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "")
                    .post(RequestBody.create(MediaType.parse("application/json"), content))
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                throw new RuntimeException("Error while connecting to the API");
            }
            assert response.body() != null;
            String responseBody = response.body().string();
            return new JSONObject(responseBody);
        }
        catch(RuntimeException | IOException | JSONException e )
        {
            System.out.print(e);
            return null;
        }
    }
}
