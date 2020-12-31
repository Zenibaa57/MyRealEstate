package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class SimulatorActivity extends AppCompatActivity {

    public static final String PRICE = "price";
    private double priceProperty;
    private TextView pPrice;
    private EditText interest;
    private EditText lPeriod;
    private TextView monthly;
    private TextView total;
    private Button calculate;



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

        pPrice = findViewById(R.id.priceText);
        interest = findViewById(R.id.interest);
        lPeriod = findViewById(R.id.lPeriod);
        monthly = findViewById(R.id.monthly);
        total = findViewById(R.id.total);
        calculate = findViewById(R.id.calculate);

        pPrice.setText("Property Price : $" + priceProperty);

        calculate.setOnClickListener(view -> {
            double dInterest = Integer.parseInt(interest.getText().toString());
            double period = Integer.parseInt(lPeriod.getText().toString());
            double r = dInterest/1200;
            double r1 = Math.pow(r+1,period);

            double monthlyPayment = (double) ((r+ (r/(r1-1))) * priceProperty);
            double totalPayment = monthlyPayment * period;

            monthly.setText("Monthly Payment : " + new DecimalFormat("##.##").format(monthlyPayment));
            total.setText("Total Payment : " +new DecimalFormat("##.##").format(totalPayment));
        });

    }

}
