package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.pi.tracosefios.databinding.ActivityEditBinding;
import com.pi.tracosefios.models.Service;
import com.pi.tracosefios.services.EditService;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private Service service;
    private EditService editService = new EditService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#a9d1af"));
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getData();

        if (this.service.getId() == 0) {
            Toast.makeText(this, "AppError: Serviço selecionado inválido", Toast.LENGTH_SHORT).show();
        }

        binding.titleName.setText(this.service.getTitle());
        binding.description.setText(this.service.getDescription());

        binding.saveButton.setOnClickListener(view -> {
            getData();
            if (validFields() && hasEdition()) {
                this.service.setTitle(binding.titleName.getText().toString());
                this.service.setDescription(binding.description.getText().toString());
                try {
                    String result = editService.editValues(service);
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

    private Service getData() {
        Intent comingData = getIntent();
        service = new Service(
                comingData.getIntExtra("id", 0),
                comingData.getStringExtra("title"),
                comingData.getStringExtra("description")
        );
        return service;
    }

    private Boolean validFields() {
        if (binding.titleName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha o campo Título", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (binding.description.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha o campo Descrição", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private Boolean hasEdition() {
        if (binding.titleName.getText().toString() == this.service.getTitle()) return false;
        if (binding.description.getText().toString() == this.service.getDescription()) return false;
        return true;
    }


}