package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class user_selection extends AppCompatActivity {

    CardView shopCreate;
    CardView user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        shopCreate = findViewById(R.id.sellersform);
        user = findViewById(R.id.buysform);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_selection.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        shopCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_selection.this,ShopSlideActivity.class);
                startActivity(intent);
            }
        });
    }
}