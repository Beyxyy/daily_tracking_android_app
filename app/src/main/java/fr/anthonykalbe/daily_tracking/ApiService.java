package fr.anthonykalbe.daily_tracking;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ApiService {

    @GET("login")
    Call<String> loginUser(@Body Map<String, Object> body);

    @GET('training')
}
