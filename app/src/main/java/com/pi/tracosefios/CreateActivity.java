package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.pi.tracosefios.databinding.ActivityCreateBinding;
import com.pi.tracosefios.models.Service;
import com.pi.tracosefios.services.CreateService;

import org.json.JSONException;

import java.io.IOException;

public class CreateActivity extends AppCompatActivity {

    private ActivityCreateBinding binding;
    private String title, description;
    private CreateService createService = new CreateService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#a9d1af"));
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        binding.saveButton.setOnClickListener(view -> {
            getValues();
            if (validFields()) {
                try {
                    String result = createService.create(new Service(0, this.title, this.description));
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, ServicesActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.backButton.setOnClickListener(view -> {
            startActivity(new Intent(this, ServicesActivity.class));
        });

    }


    private Boolean validFields() {
        if (this.title.isEmpty()) {
            Toast.makeText(this, "Preencha o campo título", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (this.description.isEmpty()) {
            Toast.makeText(this, "Preencha o campo descrição", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void getValues() {
        this.title = binding.titleName.getText().toString();
        this.description = binding.description.getText().toString();
    }
}