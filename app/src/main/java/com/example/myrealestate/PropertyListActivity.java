package com.example.myrealestate;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.adapter.PropertyAdapter;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.preference.UserPreferences;
import com.example.myrealestate.repository.RealEstateRepository;
import com.example.myrealestate.viewmodels.PropertyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class PropertyListActivity extends AppCompatActivity {

     private PropertyViewModel propertyViewModel;
     private RecyclerView recyclerViewProperties;
     private String agentFirstname;
     private FloatingActionButton addProperty;
     private TextView what;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.light_grey));
        setContentView(R.layout.real_estate_list);
        what = findViewById(R.id.what);
        addProperty = findViewById(R.id.addBusiness);
        restoreUserAgentProfile();

        addProperty.setOnClickListener(view -> {
            Intent intent = new Intent(PropertyListActivity.this, PropertyFormActivity.class);
            startActivity(intent);
        });



         propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
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
        if (item.getItemId() == R.id.quit)
        {
            new AlertDialog.Builder(this)
                    .setTitle("WARNINGS!")
                    .setMessage("Are you sure you want to sign out?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        final Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        UserPreferences.saveUserAgentProfile(this, "");
                        this.startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (item.getItemId() == android.R.id.home){
            new AlertDialog.Builder(this)
                    .setTitle("WARNINGS!")
                    .setMessage("Are you sure you want to sign out?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        final Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        UserPreferences.saveUserAgentProfile(this, "");
                        this.startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


        return super.onOptionsItemSelected(item);
    }
    private void initList()
    {
  /*      final List<Property> properties = RealEstateRepository.getInstance(this).getProperties();
        final PropertyAdapter propertyAdapter = new PropertyAdapter(properties);
        recyclerViewProperties.setAdapter(propertyAdapter);*/
    }

    private void restoreUserAgentProfile()
    {
        final String userLogin = UserPreferences.getUserAgentProfile(this);
        if (TextUtils.isEmpty(userLogin) == false) {
            what.setText(userLogin); }
    }


}
