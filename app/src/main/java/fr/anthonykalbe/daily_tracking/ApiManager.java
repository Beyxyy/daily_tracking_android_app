package fr.anthonykalbe.daily_tracking;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Map;

public class ApiManager {

    private final ApiService apiService;

    public ApiManager() {
        // Crée un objet Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://example.com/") // Remplace avec ton URL de base
                .addConverterFactory(GsonConverterFactory.create()) // Utilise Gson comme convertisseur de JSON
                .build();

        // Crée une instance de l'interface ApiService
        apiService = retrofit.create(ApiService.class);
    }

    public void loginUser(Map<String, Object> body, final ApiCallback<String> callback) {
        // Effectue l'appel à l'API
        Call<String> call = apiService.loginUser(body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new IOException("Erreur lors de la requête : " + response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }


    public void RequestDataTraining(Map<String, Object> body, final ApiCallback<String> callback){
        Call<String> call = apiService.TrainingData(body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new IOException("Erreur lors de la requête : " + response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
    // Interface pour définir des callbacks
    public interface ApiCallback<T> {
        void onSuccess(T result);

        void onFailure(Throwable t);
    }
}
