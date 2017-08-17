package com.example.sammy.eqibook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
//import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener mAuthListner;
    String email;
    String password;
    TextView newAccount;
    EditText emailField;
    EditText passwordField;
    ProgressBar progressBar;
   Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        newAccount=(TextView)findViewById(R.id.newuser);
        emailField=(EditText)findViewById(R.id.email);
        passwordField=(EditText)findViewById(R.id.password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        mAuthListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=firebaseAuth.getCurrentUser();
                if (firebaseUser==null){
                    Log.d("User :"," Signed In");

                }
                else {
                    Log.d("User :","Not Signed In");
                }
            }
        };
        login=(Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=emailField.getText().toString().trim();
                password=passwordField.getText().toString().trim();
                if(email.contentEquals("") || password.contentEquals("")){
                    Toast.makeText(getApplicationContext(),"Empty fields not allowed",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    login(email, password);
                }
            }
        });
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListner);
    }

    void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login Task :", "Sucessful" + task.isSuccessful());
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Log.w("Login task:", "failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Login Failed.Check net and try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Logging in : ",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            finish();
                            startActivity(intent);
                        }

                    }
                });
    }
}
