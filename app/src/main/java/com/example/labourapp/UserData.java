package com.example.labourapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserData extends AppCompatActivity {

    EditText editTextTextPersonName, editTextTextPersonFatherName, editTextTextPersonQualification, editTextPersonPostalAddress,editTextPhone,editTextBirthDate;
    EditText editTextTextPersonPost, editTextTextPersonJobRole, editTextDatePlacedOn;
    Button SubmitDataBtn;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonFatherName = findViewById(R.id.editTextTextPersonFatherName);
        editTextTextPersonQualification = findViewById(R.id.editTextTextPersonQualification);
        editTextPersonPostalAddress = findViewById(R.id.editTextPersonPostalAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextTextPersonPost = findViewById(R.id.editTextTextPersonPost);
        editTextTextPersonJobRole = findViewById(R.id.editTextTextPersonJobRole);
        editTextDatePlacedOn = findViewById(R.id.editTextDatePlacedOn);

        SubmitDataBtn = findViewById(R.id.SubmitDataBtn);

        db = FirebaseFirestore.getInstance();

        SubmitDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserData.this, "Adding Data", Toast.LENGTH_SHORT).show();

                String name = editTextTextPersonName.getText().toString();
                String fName = editTextTextPersonFatherName.getText().toString();
                String qualification = editTextTextPersonQualification.getText().toString();
                String address = editTextPersonPostalAddress.getText().toString();
                String phone = editTextPhone.getText().toString();
                String birthDate = editTextBirthDate.getText().toString();
                String post = editTextTextPersonPost.getText().toString();
                String role = editTextTextPersonJobRole.getText().toString();
                String placedOn = editTextDatePlacedOn.getText().toString();


                Map<String, Object> user = new HashMap<>();
                user.put("Name", name);
                user.put("FName", fName);
                user.put("qualification", qualification);
                user.put("address", address);
                user.put("phone", phone);
                user.put("DOB", birthDate);
                user.put("post", post);
                user.put("role", role);
                user.put("placed on", placedOn);

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.w(TAG,"Document Added with ID : " + documentReference.getId());
                                Toast.makeText(UserData.this, "Added !!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), User.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserData.this, "Error", Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "Error adding Document", e);
                            }
                        });

            }
        });

    }
}