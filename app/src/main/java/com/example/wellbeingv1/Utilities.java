package com.example.wellbeingv1;

import android.content.Context;
import android.widget.Toast;

public class Utilities {

    static void toaster (Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
