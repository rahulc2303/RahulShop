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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class signUp extends AppCompatActivity
{
    TextInputLayout firstName, lastName,email,password;
        Button signUP;
    SignInButton btn;
    ProgressBar progressBar;
    TextView signin,close;


    private final  static int RC_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();



        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.shopdescription);
        email = findViewById(R.id.GSTno);
        password = findViewById(R.id.shopType);
        signUP = findViewById(R.id.gopopup);
        progressBar = findViewById(R.id.progress_circular);
        signin = findViewById(R.id.signup);

        btn = (SignInButton) findViewById(R.id.googleSign);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processlogin();
            }
        });



        createRequest();

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
                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(signUp.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signUp.this, SignInActivity.class));
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

    private void createRequest()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void processlogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {

                Toast.makeText(getApplicationContext(),"error in getting google information",Toast.LENGTH_SHORT).show();


            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),user_selection.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"problem found in firebase login",Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

}