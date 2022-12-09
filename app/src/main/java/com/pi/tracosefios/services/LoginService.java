package com.pi.tracosefios.services;


import androidx.activity.result.contract.ActivityResultContracts;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginService {

    private FirebaseAuth nAuth;
    private FirebaseUser user;
    private String messageError = "";

    public FirebaseUser login(String email, String password) {
        firebaseLogin(email,  password);
        return user;
    }


    protected void firebaseLogin(String email, String password) {

        nAuth = FirebaseAuth.getInstance();

        nAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
           task.addOnFailureListener(error -> {
               System.out.println("Erro ao logar usuario: " + error.getMessage());
               this.messageError = error.getMessage();
           });

            if(task.isSuccessful()) {
                this.user = nAuth.getCurrentUser();
                System.out.println("Usuário logado com sucesso!");
           }
        });
    }

    public String getErrors() {
        return this.messageError;
    }
}
