package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class user_selection extends AppCompatActivity {

    CardView shopcreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        shopcreate = findViewById(R.id.sellersform);
        shopcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_selection.this,Shop_register.class);
                startActivity(intent);
            }
        });
    }
}