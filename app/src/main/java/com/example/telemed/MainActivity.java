package com.example.telemed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void regWindow(View view) {
        Intent regIntent = new Intent(getApplicationContext(), RegActivity.class);
        startActivity(regIntent);
    }

    public void authWindow(View view) {
        Intent authIntent = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(authIntent);
    }
}