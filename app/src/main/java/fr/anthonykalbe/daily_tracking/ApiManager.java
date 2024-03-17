package fr.anthonykalbe.daily_tracking;


import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.*;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;

import java.util.HashMap;
import java.util.Map;


public class ApiManager {
    String url = "10.0.2.2";

    String content;
    private Context context;

    public RequestQueue queue;


    public ApiManager(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }



}
