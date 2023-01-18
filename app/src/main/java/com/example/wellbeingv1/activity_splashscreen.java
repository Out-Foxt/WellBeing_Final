package com.example.wellbeingv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.window.SplashScreen;
import android.window.SplashScreenView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class activity_splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser==null){
                    startActivity(new Intent( activity_splashscreen.this,activity_log_in.class));
                }else{
                    startActivity(new Intent( activity_splashscreen.this,MainActivity.class));
                }
                finish();

            }
        } ,1000);
    }
}