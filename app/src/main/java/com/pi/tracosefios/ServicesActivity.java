package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pi.tracosefios.adapters.JsonConverter;
import com.pi.tracosefios.adapters.ServiceListAdapter;
import com.pi.tracosefios.databinding.ActivityServicesBinding;
import com.pi.tracosefios.models.Service;
import com.pi.tracosefios.services.RequestService;

import org.json.JSONException;

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
    private FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        binding = ActivityServicesBinding.inflate(getLayoutInflater());
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#a9d1af"));
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recycler = binding.recycler;

        try {
            response = requestService.fetch("http://192.168.100.200:3333/services/getAllServices");
            if (response.equals("[]")) {
                binding.emptyServices.setVisibility(View.VISIBLE);
            }
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

        binding.addButton.setOnClickListener(view -> {
            startActivity(new Intent(this, CreateActivity.class));
        });

        binding.logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });




    }
}