package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Shop_register extends AppCompatActivity
{
    TextInputLayout Shopname,Email,Phonenumber,Bussinessname,Address,State,Pincode,Industry;
    Button Regshop;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    private DatabaseReference StoreRef;
    private String sID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);

        firebaseAuth = FirebaseAuth.getInstance();
        sID =firebaseAuth.getCurrentUser().getUid();
        StoreRef = FirebaseDatabase.getInstance().getReference().child("Store").child(sID);


        Shopname = findViewById(R.id.Shopname);
        Email = findViewById(R.id.Email);
        Phonenumber = findViewById(R.id.Phonenumber);
        Bussinessname = findViewById(R.id.Bussinessname);
        Address = findViewById(R.id.Address);
        State = findViewById(R.id.State);
        Pincode = findViewById(R.id.Pincode);
        Industry = findViewById(R.id.Industry);
        Regshop = findViewById(R.id.Regshop);

        Regshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });








    }
}