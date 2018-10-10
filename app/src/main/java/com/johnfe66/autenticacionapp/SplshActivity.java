package com.johnfe66.autenticacionapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johnfe66.autenticacionapp.services.FCMToken;

public class SplshActivity extends AppCompatActivity {


    final int  TIEMPO_SPLASH=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh);
        getSupportActionBar().hide();

        FCMToken fcmToken = new FCMToken();
        fcmToken.onTokenRefresh();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplshActivity.this, LoginActivity.class));
                finish();

            }
        }, TIEMPO_SPLASH);





    }
}
