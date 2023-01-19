package com.example.wellbeingv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class activity_createaccount extends AppCompatActivity {

    EditText emailEditText, passwordEditText, confirmPasswordEditText;
    Button createAccountbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.repassword);
        createAccountbutton = findViewById(R.id.create_account_button);


        createAccountbutton.setOnClickListener(v-> createAccount());
        //createAccountbutton.setOnClickListener((v)-> startActivity(new Intent(activity_createaccount.this, activity_log_in.class)));
        //Utilities.toaster(activity_createaccount.this,"Account Created!");

    }

    void createAccount(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = confirmPasswordEditText.getText().toString();
        boolean isValid = validateData(email,password,repassword);
        if(!isValid){
            return;
        }
        createAccountFirebase(email, password);

    }

    void createAccountFirebase(String email, String password){
        //create account in DB
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity_createaccount.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Utilities.toaster(activity_createaccount.this, "Account Created, please check your email to verify ");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();

                        }else{
                            Utilities.toaster(activity_createaccount.this, task.getException().getLocalizedMessage());

                        }

                    }
                }
        );



    }

    boolean validateData(String email, String password, String repassword){
        //validate login info

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //emailEditText.setError("Invalid Email Address");
            return true;
        }
        if(password.length()<6){
            passwordEditText.setError("Password is Invalid, must be at least 6 characters");
            return false;
        }
        if(!password.equals(repassword)){
            confirmPasswordEditText.setError("Passwords do not Match");
            return false;
        }
        return true;
    }
}