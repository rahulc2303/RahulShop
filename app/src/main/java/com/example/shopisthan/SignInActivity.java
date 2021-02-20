package com.example.shopisthan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity
{

    TextView textView99;
    TextInputLayout userEmail,userPassword;
    Button SignInBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mAuth = FirebaseAuth.getInstance();


        textView99 = findViewById(R.id.textView99);
        userEmail = findViewById(R.id.Email);
        userPassword = findViewById(R.id.Password);
        SignInBtn = findViewById(R.id.gopopup);

          SignInBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v)
              {
                  loginSeller();
              }
          });




        textView99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignInActivity.this,signUp.class);
                startActivity(intent);
            }
        });
    }

    private void loginSeller()
    {
        final String email = userEmail.getEditText().getText().toString();
        final String password = userPassword.getEditText().getText().toString();


        if (TextUtils.isEmpty(email))
        {
            userEmail.setError("Enter the  Email");


        }

        else if (TextUtils.isEmpty(password))
        {
            userPassword.setError("Enter the Password");


        }

        else
        {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {


                                Toast.makeText(SignInActivity.this, "You are Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this, user_selection.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {

                                Toast.makeText(SignInActivity.this, "Please check all the details", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }








    }
}