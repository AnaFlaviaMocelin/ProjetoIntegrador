package com.pi.tracosefios.services;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.pi.tracosefios.RegisterActivity;


public class RegisterService {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String messageError = "";


    public void register(String name, String email, String password, String phone) {
        createUser(email, password);

        if (user != null) {
            mDatabase.child("users").child(user.getUid()).child("phone").setValue(phone);
        }

    }

    public void createUser(String email, String password) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        task.addOnFailureListener(error -> {
                            System.out.println("Erro ao cadastrar usuário" + error.getMessage());
                            messageError = error.getMessage();
                        });

                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            System.out.println("Usuário criado com sucesso");
                        }
                    }
                });
    }

    public String getErrors() {
        return messageError;
    }
}
