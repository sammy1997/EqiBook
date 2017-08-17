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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener mAuthListner;
    String email;
    String username;
    String password;
    TextView oldAccount;
    EditText emailField;
    EditText userField;
    EditText passwordField;
    ProgressBar progressBar;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        userField=(EditText)findViewById(R.id.sign_user);
        emailField=(EditText)findViewById(R.id.sign_mail);
        passwordField=(EditText)findViewById(R.id.sign_password);


        oldAccount=(TextView)findViewById(R.id.login_sign);
        oldAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });


        progressBar=(ProgressBar)findViewById(R.id.progressBar7);
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

        signUp=(Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=userField.getText().toString().trim();
                password=passwordField.getText().toString().trim();
                email=emailField.getText().toString().trim();

                if(email.contentEquals("") || password.contentEquals("") ||
                        username.contentEquals("")){

                    Toast.makeText(getApplicationContext(),"Empty fields not allowed",
                            Toast.LENGTH_SHORT).show();

                }
                else {

                    progressBar.setVisibility(View.VISIBLE);
                    signUp(email, password);

                }
            }
        });

    }
    void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(" Sign Up:", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Successful",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
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
}
