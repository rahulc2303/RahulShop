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

public class SignInActivity extends AppCompatActivity
{

    TextView textView99;
    TextInputLayout userEmail,userPassword;
    Button SignInBtn;
    FirebaseAuth mAuth;
    SignInButton btn;
    ProgressBar progressBar;

    private final  static int RC_SIGN_IN =123;

    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mAuth = FirebaseAuth.getInstance();


        textView99 = findViewById(R.id.textView99);
        userEmail = findViewById(R.id.Email);
        userPassword = findViewById(R.id.Password);
        SignInBtn = findViewById(R.id.gopopup);
        btn = findViewById(R.id.googleSignBtn);
        progressBar = findViewById(R.id.progress_circular);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processlogin();
            }
        });



        createRequest();




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
        final String email = userEmail.getEditText().getText().toString().trim();
        final String password = userPassword.getEditText().getText().toString().trim();


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
            SignInBtn.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

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
                                SignInBtn.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
//                                Toast.makeText(SignInActivity.this, "Please check all the details", Toast.LENGTH_SHORT).show();
                                Toast.makeText(SignInActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }








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