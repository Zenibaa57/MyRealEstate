package com.example.myrealestate.front;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myrealestate.R;

public class RealEstateList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        setContentView(R.layout.real_estate_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Menu "icone exit"
        getMenuInflater().inflate(R.menu.menu_real_estate_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Boite de dialogue lors de la suppression
        return super.onOptionsItemSelected(item);
    }
}
