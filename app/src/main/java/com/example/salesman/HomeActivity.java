package com.example.salesman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {
    public MaterialButton storeAc,orderAc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        storeAc = findViewById(R.id.storeAc);
        orderAc = findViewById(R.id.orderAc);
        storeAc.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));
        orderAc.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),OrderActivity.class)));
    }
}