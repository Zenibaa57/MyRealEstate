package com.example.myrealestate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
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
import com.example.myrealestate.repository.RealEstateRepository;
import com.example.myrealestate.viewmodels.PropertyViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertyListActivity extends AppCompatActivity {

     private FloatingActionButton addProperty;
     private TextView who;
     private RecyclerView realEstateRecyclerView;
     private PropertyViewModel propertyViewModel;
     private View searchLayout;
     private EditText minPrice;
     private EditText maxPrice;
     private EditText minSurface;
     private EditText maxSurface;
     private EditText startDate;
     private EditText endDate;
     private CheckBox availability;
     private ImageButton imageButton;
     private double minIPrice;
     private double maxIPrice;
     private double minISurface;
     private double maxISurface;
     private Date startSDate;
     private Date endSDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        setContentView(R.layout.real_estate_list);
        who = findViewById(R.id.who);
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

    @SuppressLint("SimpleDateFormat")
    private void search() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                PropertyListActivity.this,R.style.BottomSheetDialog);
        View bottomView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_filter, findViewById(R.id.bottomContainer));
        minPrice =  bottomView.findViewById(R.id.filterMinPrice);
        maxPrice =  bottomView.findViewById(R.id.filterMaxPrice);
        minSurface =  bottomView.findViewById(R.id.filterMinSurface);
        maxSurface =  bottomView.findViewById(R.id.filterMaxSurface);
        startDate =  bottomView.findViewById(R.id.startDate);
        endDate =  bottomView.findViewById(R.id.endDate);
        availability =  bottomView.findViewById(R.id.checkAvailability);
        minIPrice =0.0;
        maxIPrice=0.0;
        minISurface=0.0;
        maxISurface=0.0;


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
            if (!TextUtils.isEmpty(startDate.getText()) && !TextUtils.isEmpty(endDate.getText())) {
                try {
                    final String DATE_PATTERN = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
                    final Pattern pattern = Pattern.compile(DATE_PATTERN);
                    Matcher matcherStartDate = pattern.matcher(String.valueOf(startDate.getText()));
                    Matcher matcherEndDate = pattern.matcher(String.valueOf(endDate.getText()));
                    if (matcherStartDate.matches() && matcherEndDate.matches()) {
                        startSDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(startDate.getText()));
                        endSDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(endDate.getText()));
                        if(startSDate.compareTo(endSDate) > 0) {
                            Toast.makeText(getApplicationContext(), "End date occurs before Start Date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
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

                if (minIPrice!= 0 && minISurface!= 0 && availability.isChecked()){
                    if ((property.price>minIPrice && property.price<maxIPrice) && (property.surfaceArea>minISurface && property.surfaceArea<maxISurface) && (RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId))){
                       if (startSDate !=null && endSDate != null){
                         Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                         if (creationDate.after(startSDate) && creationDate.before(endSDate))
                             propertyFiltered.add(property);
                       }else {
                           propertyFiltered.add(property);
                       }
                    }
                }
                else if (minIPrice!= 0 && minISurface!= 0 ){
                    if ((property.price>minIPrice && property.price<maxIPrice) && (property.surfaceArea>minISurface && property.surfaceArea<maxISurface)){
                        if (startSDate !=null && endSDate != null){
                            Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate))
                                propertyFiltered.add(property);
                        }else {
                            propertyFiltered.add(property);
                        }
                    }
                }
                else if (minISurface!= 0 && availability.isChecked()){
                    if ((property.surfaceArea>minISurface && property.surfaceArea<maxISurface) &&(RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId))){
                        if (startSDate !=null && endSDate != null){
                            Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate))
                                propertyFiltered.add(property);
                        }else {
                            propertyFiltered.add(property);
                        }
                    }
                }
                else if (minIPrice!= 0  && availability.isChecked()){
                    if ((property.price>minIPrice && property.price<maxIPrice) && (RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId))){
                        if (startSDate !=null && endSDate != null){
                            Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate))
                                propertyFiltered.add(property);
                        }else {
                            propertyFiltered.add(property);
                        }
                    }
                }
                else if (minIPrice!= 0){
                 if (property.price>minIPrice && property.price<maxIPrice){
                     if (startSDate !=null && endSDate != null){
                         Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                         if (creationDate.after(startSDate) && creationDate.before(endSDate))
                             propertyFiltered.add(property);
                     }else {
                         propertyFiltered.add(property);
                     }
                    }
                 }
                else if (minISurface!= 0) {
                    if (property.surfaceArea>minISurface && property.surfaceArea<maxISurface) {
                        if (startSDate !=null && endSDate != null){
                            Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate))
                                propertyFiltered.add(property);
                        }
                    }else {
                        propertyFiltered.add(property);
                    }
                }
                else if (availability.isChecked() == RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId)) {
                        if (startSDate != null && endSDate != null) {
                            Date creationDate = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate)) {
                                propertyFiltered.add(property);
                            }
                        } else {
                            propertyFiltered.add(property);
                        }
                }

                else if (startSDate !=null && endSDate != null) {
                            Date creationDate  = new Date(property.dateOfTheCreationAdvert);
                            if (creationDate.after(startSDate) && creationDate.before(endSDate))
                                propertyFiltered.add(property);
                }
            }
            final PropertyAdapter propertyAdapter = new PropertyAdapter(propertyFiltered,this);
            realEstateRecyclerView.setAdapter(propertyAdapter);
        });
    }

    private void restoreUserAgentProfile() {
        final String userLogin = UserPreferences.getUserAgentProfile(this);
        if (TextUtils.isEmpty(userLogin) == false) {
            who.setText(userLogin); }
    }
}
