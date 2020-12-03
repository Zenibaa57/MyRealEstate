package com.example.myrealestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myrealestate.R;
import com.example.myrealestate.front.RealEstateList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private EditText user;
    private EditText password;
    private Button login;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonPass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);


        login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RealEstateList.class);
            startActivity(intent);
        });
    }
}