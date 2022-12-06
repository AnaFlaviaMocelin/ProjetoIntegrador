package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pi.tracosefios.adapters.JsonConverter;
import com.pi.tracosefios.adapters.ServiceListAdapter;
import com.pi.tracosefios.databinding.ActivityServicesBinding;
import com.pi.tracosefios.models.Service;
import com.pi.tracosefios.services.RequestService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    private RequestService requestService = new RequestService();
    private JsonConverter jsonConverter = new JsonConverter();
    private String response;
    private ActivityServicesBinding binding;
    private RecyclerView recycler;
    private ArrayList<Service> services;
    private ServiceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recycler = binding.recycler;

        try {
            response = requestService.fetch("http://192.168.100.196:3333/services/getAllServices");
            System.out.println("Response: " + response);
            services = jsonConverter.arrayToJsonObjects(response);


            if (services != null) {
                adapter = new ServiceListAdapter(this, services);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(adapter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
}