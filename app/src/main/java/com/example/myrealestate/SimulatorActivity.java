package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SimulatorActivity extends AppCompatActivity {

    public static final String PRICE = "price";
    private double priceProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        setContentView(R.layout.simulator);
        priceProperty = (double) getIntent().getSerializableExtra(PRICE);
    }

}
