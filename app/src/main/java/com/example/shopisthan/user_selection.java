package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class user_selection extends AppCompatActivity {

    ImageView shopcreate,buysform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);



        shopcreate = findViewById(R.id.usersform);
        buysform = findViewById(R.id.buysform);

        buysform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_selection.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        shopcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_selection.this,Shop_register.class);
                startActivity(intent);
            }
        });
    }
}