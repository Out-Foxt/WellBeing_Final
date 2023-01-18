package com.example.wellbeingv1;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Patterns;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
*/
public class activity_log_in extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        loginButton = findViewById(R.id.loginbutton);
        signupButton = findViewById((R.id.signupbtn));

        loginButton.setOnClickListener(view -> {
            if (emailEditText.getText().toString().equals("Admin") && passwordEditText.getText().toString().equals("Password")) {
                Utilities.toaster(activity_log_in.this, "Login Successful");
                startActivity(new Intent(activity_log_in.this, activity_homescreen.class));
            } else
                Utilities.toaster(activity_log_in.this, "Login Failed");
        });

        signupButton.setOnClickListener((v)-> startActivity(new Intent(activity_log_in.this, activity_createaccount.class)));


    }

    /*void loginUser(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = validateData(email,password);
        if(!isValid){
            return;
        }
        loginAccountFirebase(email, password);

    }

    void  loginAccountFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //login successful
                    Utilities.toaster(activity_log_in.this, "Login Successful");

                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(activity_log_in.this, MainActivity.class));

                    }else{
                        Utilities.toaster(activity_log_in.this, "Email not Verified, Please check your email");
                    }
                }else{
                    Utilities.toaster(activity_log_in.this, task.getException().getLocalizedMessage());
                }
            }
        });

    }


    boolean validateData(String email, String password){
        //validate login info

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Invalid Email Address");
            return true;
        }
        if(password.length()<6){
            passwordEditText.setError("Password is Invalid, must be at least 6 characters");
            return false;
        }
        return true;
    }*/
}