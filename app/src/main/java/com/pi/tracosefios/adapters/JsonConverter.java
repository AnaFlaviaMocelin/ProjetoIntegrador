package com.pi.tracosefios.adapters;

import com.pi.tracosefios.models.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonConverter {
    private String json;
    private ArrayList<Service> services = new ArrayList<Service>();


    public ArrayList<Service> arrayToJsonObjects(String json) throws JSONException {


        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject  jsonObject = jsonArray.getJSONObject(i);
                Integer id = Integer.valueOf(jsonObject.optString("id"));
                String name = jsonObject.optString("name");
                String description = jsonObject.optString("description");
                String value = jsonObject.optString("value");
                services.add(new Service(id, name, description));

            }
            return services;

        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
