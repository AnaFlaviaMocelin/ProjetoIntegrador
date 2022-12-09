package com.pi.tracosefios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pi.tracosefios.databinding.ActivityLoginBinding;
import com.pi.tracosefios.services.LoginService;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginService loginService = new LoginService();
    private String email;
    private String password;
    private FirebaseUser user;
    private String messageError;
    private FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient);
        setContentView(binding.getRoot());

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(this, ServicesActivity.class));
            finish();
        }

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        binding.buttonRegister.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        binding.buttonLogin.setOnClickListener(view -> {
            getValues();

           if (validFields()) {
                user = loginService.login(email, password);
                messageError = "";
                messageError = loginService.getErrors();
                if (messageError.isEmpty() && user != null) {
                    binding.emailInputText.setText("");
                    binding.passwordInputText.setText("");
                    finish();
                    startActivity(new Intent(this, ServicesActivity.class));
                }
                if (!messageError.isEmpty()){
                    Toast.makeText(this, messageError, Toast.LENGTH_LONG).show();
                }
           }
        });
    }

    private Boolean validFields() {
        if (email.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de e-mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de senha", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void getValues() {
        email = binding.emailInputText.getText().toString();
        password = binding.passwordInputText.getText().toString();

    }

}