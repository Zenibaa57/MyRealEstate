package com.example.myrealestate;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.adapter.PropertyAdapter;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.preference.UserPreferences;
import com.example.myrealestate.viewmodels.PropertyViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PropertyListActivity extends AppCompatActivity {

     private FloatingActionButton addProperty;
     private TextView what;
     private RecyclerView realEstateRecyclerView;
     private PropertyViewModel propertyViewModel;
     private View searchLayout;
     private EditText minPrice;
     private EditText maxPrice;
     private EditText minSurface;
     private EditText maxSurface;
     private EditText minRooms;
     private EditText maxRooms;
    private ImageButton imageButton;
     private double minIPrice;
     private double maxIPrice;
     private double minISurface;
     private double maxISurface;
     private int minIRooms;
     private int maxIRooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        setContentView(R.layout.real_estate_list);
        what = findViewById(R.id.what);
        addProperty = findViewById(R.id.addBusiness);
        addProperty = findViewById(R.id.addBusiness);
        searchLayout = findViewById(R.id.searchLayout);
        imageButton = findViewById(R.id.imageButton);
        realEstateRecyclerView = findViewById(R.id.realEstateList);
        restoreUserAgentProfile();

        searchLayout.setOnClickListener(view -> {
            search();
        });

        imageButton.setOnClickListener(view -> {
           initList();
           imageButton.setVisibility(View.GONE);
        });

        addProperty.setOnClickListener(view -> {
            Intent intent = new Intent(PropertyListActivity.this, PropertyFormActivity.class);
            intent.putExtra("State", PropertyFormActivity.State.ADD);
            startActivity(intent);
        });

         propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
         initList();
    }

    private void search() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                PropertyListActivity.this,R.style.BottomSheetDialog);
        View bottomView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_filter, findViewById(R.id.bottomContainer));
        minPrice =  bottomView.findViewById(R.id.filterMinPrice);
        maxPrice =  bottomView.findViewById(R.id.filterMaxPrice);
        minSurface =  bottomView.findViewById(R.id.filterMinSurface);
        maxSurface =  bottomView.findViewById(R.id.filterMaxSurface);
        minRooms =  bottomView.findViewById(R.id.filterMinRooms);
        maxRooms =  bottomView.findViewById(R.id.filterMaxRooms);
        minIPrice =0.0;
        maxIPrice=0.0;
        minISurface=0.0;
        maxISurface=0.0;
        minIRooms=0;
        maxIRooms=0;
        bottomView.findViewById(R.id.buttonFilter).setOnClickListener(view -> {

            if (!TextUtils.isEmpty(minPrice.getText()) && !TextUtils.isEmpty(maxPrice.getText())){
                minIPrice = Double.parseDouble(String.valueOf(minPrice.getText()));
                maxIPrice = Double.parseDouble(String.valueOf(maxPrice.getText()));
                if (minIPrice>maxIPrice){
                    Toast.makeText(getApplicationContext(), "Max value cannot be less than the min", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (!TextUtils.isEmpty(minSurface.getText()) && !TextUtils.isEmpty(maxSurface.getText())){
                minISurface = Double.parseDouble(String.valueOf(minSurface.getText()));
                maxISurface = Double.parseDouble(String.valueOf(maxSurface.getText()));
                if (minISurface>maxISurface){
                    Toast.makeText(getApplicationContext(), "Max value cannot be less than the min", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (!TextUtils.isEmpty(minRooms.getText()) && !TextUtils.isEmpty(maxRooms.getText())) {
                minIRooms = Integer.parseInt(String.valueOf(minRooms.getText()));
                maxIRooms = Integer.parseInt(String.valueOf(maxRooms.getText()));
                if (minIRooms>maxIRooms){
                    Toast.makeText(getApplicationContext(), "Max value cannot be less than the min", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            initListFilter();
            bottomSheetDialog.dismiss();
            imageButton.setVisibility(View.VISIBLE);
        });
        bottomSheetDialog.setContentView(bottomView);
        bottomSheetDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Boite de dialogue lors de la suppression
        if (item.getItemId() == R.id.maps)
        {
            final Intent intent = new Intent(this, MapActivity.class);
            this.startActivity(intent);
        }
        else if (item.getItemId() == android.R.id.home){
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

    @Override
    protected void onResume() {
        //Cycle de vie de l'application retour sur le parent
        super.onResume();
        initList();
    }

    private void initList() {
        propertyViewModel.property.observe(this, property -> {
            final PropertyAdapter propertyAdapter = new PropertyAdapter(property,this);
            realEstateRecyclerView.setAdapter(propertyAdapter);
        });
    }

    private void initListFilter() {
        List<Property> propertyFiltered  = new ArrayList<>();
        propertyViewModel.property.observe(this, properties -> {
            for (Property property : properties) {

                if (minIPrice!= 0){
                 if (property.price>minIPrice && property.price<maxIPrice){
                        propertyFiltered.add(property);
                    }
                }
                if (minISurface!= 0) {
                    if (property.surfaceArea>minISurface && property.surfaceArea<maxISurface) {
                        propertyFiltered.add(property);
                    }
                }
                if (minIRooms!= 0) {
                    if (property.numberOfRoom>minIRooms && property.numberOfRoom<maxIRooms) {
                        propertyFiltered.add(property);
                    }
                }
            }

            final PropertyAdapter propertyAdapter = new PropertyAdapter(propertyFiltered,this);
            realEstateRecyclerView.setAdapter(propertyAdapter);
        });
    }

    private void restoreUserAgentProfile() {
        final String userLogin = UserPreferences.getUserAgentProfile(this);
        if (TextUtils.isEmpty(userLogin) == false) {
            what.setText(userLogin); }
    }
}
