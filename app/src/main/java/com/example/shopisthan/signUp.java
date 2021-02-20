package com.example.shopisthan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity
{
    TextInputLayout firstName, lastName,email,password;
        Button signUP;
    SignInButton btn;
    ProgressBar progressBar;
    TextView signin,close;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        firebaseAuth = FirebaseAuth.getInstance();


        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.shopdescription);
        email = findViewById(R.id.GSTno);
        password = findViewById(R.id.shopType);
        signUP = findViewById(R.id.gopopup);
        progressBar = findViewById(R.id.progress_circular);
        signin = findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             Intent intent = new Intent(signUp.this,SignInActivity.class);
             startActivity(intent);
            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String Email = email.getEditText().getText().toString();
                String Password = password.getEditText().getText().toString();
                String FirstName = firstName.getEditText().getText().toString();
                String LastName = lastName.getEditText().getText().toString();


                if (TextUtils.isEmpty(FirstName))
                {
                    firstName.setError("Enter the first name");


                }

                 else if (TextUtils.isEmpty(LastName))
                {
                    email.setError("Enter the last name");


                }

                else if (TextUtils.isEmpty(Email))
                {
                    email.setError("Enter the email");


                }
                else if(TextUtils.isEmpty(Password))
                {
                    password.setError("Password is Required");


                }
                else if  (Password.length()<6)
                {
                    password.setError("Password must be more than 6 characters");


                }

                else {




                    signUP.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(signUp.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signUp.this, signUp.class));
                            }
                            else
                            {
                                signUP.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(signUp.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }





            }
        });

    }
}