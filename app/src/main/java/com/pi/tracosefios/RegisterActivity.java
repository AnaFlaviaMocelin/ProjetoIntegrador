package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pi.tracosefios.databinding.ActivityRegisterBinding;
import com.pi.tracosefios.services.RegisterService;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterService registerService = new RegisterService();

    private String name;
    private String email;
    private String password;
    private String phone;
    private String emailCheck;
    private String passCheck;
    private String messageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient);
        setContentView(binding.getRoot());


        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        binding.Voltar.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });


        binding.registerButton.setOnClickListener(view -> {

            getValues();

            if (validFields()) {
                registerService.register(this.name, this.email, this.password, this.phone);
                messageError = registerService.getErrors();

                if (!messageError.isEmpty()) {
                    Toast.makeText(this, messageError, Toast.LENGTH_LONG).show();
                }else {
                    startActivity(new Intent(this, ServicesActivity.class));
                }
            }
        });

    }

    protected Boolean validFields () {

        if (this.name.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de nome!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (this.email.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de e-mail!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (this.password.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de senha!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!compareFields(this.email, this.emailCheck)) {
            Toast.makeText(this, "Os campos de email não estão iguais!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!compareFields(this.password, this.passCheck)) {
            Toast.makeText(this, "Os campos de senha não estão iguais!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(this.email).matches()) {
            Toast.makeText(this, "Endereço de e-mail invalido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (this.password.length() < 6) {
            Toast.makeText(this, "A senha precisa ter mais que 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    protected Boolean compareFields(String field_one, String field_two) {
        return field_one.equals(field_two);
    }


    private void getValues () {
        name = binding.editNome.getText().toString();
        email = binding.editEmail.getText().toString();
        password = binding.editSenha.getText().toString();
        phone = binding.editTelefone.getText().toString();
        emailCheck = binding.editConfirmaEmail.getText().toString();
        passCheck = binding.editConfirmaSenha.getText().toString();
    }
}