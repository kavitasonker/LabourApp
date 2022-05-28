package com.example.labourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText userName, pass, passConf;
    Button registerBTN, LoginBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.editTextRegisterEmailAddress);
        pass = findViewById(R.id.editPasswordTextPassword);
        passConf = findViewById(R.id.editTextTextPassword3);

        registerBTN = findViewById(R.id.ButtonRegisterR);
        LoginBtn = findViewById(R.id.ButtonLoginR);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), UserData.class));
            finish();
        }

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userName.getText().toString();
                String password = pass.getText().toString();
                String ConPass = passConf.getText().toString();

                if (!password.equals(ConPass)) {
                    passConf.setError("Password Not Matching");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    userName.setError("Cannot be Empty");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Cannot be Empty");
                    return;
                }
                if (TextUtils.isEmpty(ConPass)) {
                    passConf.setError("Cannot be Empty");
                    return;
                }
                if (password.length() < 6) {
                    pass.setError("Length should be Greater Than 6");
                }

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserData.class));
                        } else {
                            Toast.makeText(Register.this, "Some Error Occurred, Try Again !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}