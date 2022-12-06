package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import com.pi.tracosefios.databinding.ActivityEditBinding;
import com.pi.tracosefios.models.Service;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();

        if (this.service.getId() == 0) {
            Toast.makeText(this, "AppError: Serviço selecionado inválido", Toast.LENGTH_SHORT).show();
        }

        binding.titleName.setText(this.service.getTitle());
        binding.description.setText(this.service.getDescription());



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


}