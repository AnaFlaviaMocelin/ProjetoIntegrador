package com.pi.tracosefios.services;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestService {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();


    public String fetch(String url) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Request request = new Request.Builder()
                .url(url)
                .build();

            try(Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
    }

    public Response send(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()) {
            return response;
        }
    }





}
