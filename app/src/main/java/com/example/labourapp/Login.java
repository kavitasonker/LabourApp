package com.example.labourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailTV, passwordTV;
    Button login, register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTV = findViewById(R.id.editTextTextEmailAddress);
        passwordTV = findViewById(R.id.editTextTextPassword);

        login = findViewById(R.id.submitLogin);
        register = findViewById(R.id.registerBtn);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), UserData.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTV.getText().toString();
                String pass = passwordTV.getText().toString();
                if (TextUtils.isEmpty(email)){
                    emailTV.setError("Cannot be Empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    passwordTV.setError("Cannot be Empty");
                    return;
                }
                auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), UserData.class));
                        finish();
                    }
                });
            }
        });
    }
}