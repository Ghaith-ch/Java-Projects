package com.example.Labb4_JSON_Movies;

import org.json.JSONObject;

public interface VolleyCallback <T>{
    public void onSucess(JSONObject object);
    public void onFailure(Exception e);
}
