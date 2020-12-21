package com.example.myrealestate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.RealEstateRepository;
import com.example.myrealestate.viewmodels.PropertyViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class MapActivity  extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {


    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    private Task<Location> task;
    private PropertyViewModel propertyViewModel;
    private TextView mapPrice;
    private TextView mapSurfaceArea;
    private TextView mapNumberOfRooms;
    private TextView mapAgentName;
    private TextView descriptionInfo;
    private TextView mapAddress;
    private LinearLayout layout1;
    private LinearLayout layout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.google_map);
        propertyViewModel= new ViewModelProvider(this).get(PropertyViewModel.class);
        mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapPrice = findViewById(R.id.mapPrice);
        mapSurfaceArea = findViewById(R.id.mapSurfaceArea);
        mapNumberOfRooms = findViewById(R.id.mapNumberOfRooms);
        mapAgentName = findViewById(R.id.mapAgentName);
        mapAddress = findViewById(R.id.mapAddress);
        layout1 = findViewById(R.id.infoLayout1);
        layout2 = findViewById(R.id.infoLayout2);
        descriptionInfo = findViewById(R.id.descriptionInfo);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            task = client.getLastLocation();
           getCurrentLocation();
           getPropertyLocation();
        }
    }

    private void getCurrentLocation() {
        task.addOnSuccessListener(location -> {
            if (location != null){
                mapFragment.getMapAsync(googleMap -> {
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    MarkerOptions options = new MarkerOptions().position(latLng).title("You are there");
                    options .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    googleMap.addMarker(options);
                });
            }
        });
    }

    private void getPropertyLocation() {

        propertyViewModel.property.observe(this, properties -> {
            for (Property property : properties) {
                mapFragment.getMapAsync(googleMap -> {
                    LatLng sydney = new LatLng(property.latitude,property.longitude);
                    if (!RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId))
                        googleMap.addMarker(new MarkerOptions().position(sydney).title(property.buildPropertyName(this))
                                .snippet("Lat:"+property.latitude+ "/Lng:"+property.longitude));
                    else
                        googleMap.addMarker(new MarkerOptions().position(sydney).title(property.buildPropertyName(this))
                                .snippet("Lat:"+property.latitude+ " Lng:"+property.longitude)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    googleMap.setOnMarkerClickListener(this);
                });
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        propertyViewModel.property.observe(this, properties -> {
            for (Property property : properties) {
                if (marker.getTitle().equals(property.buildPropertyName(this)) ){
                    displayInformations();
                    mapPrice.setText(property.price + " dollars");
                    mapSurfaceArea.setText(property.surfaceArea + " m²");
                    mapNumberOfRooms.setText("Rooms : "+property.numberOfRoom);
                    mapAgentName.setText(RealEstateRepository.getInstance(this).getAgentNameById(property.agentId));
                    descriptionInfo.setText("Description : "+property.description);
                    mapAddress.setText(property.address);
                }else if (marker.getTitle().equals("You are there")) {
                    hideInformations();
                    descriptionInfo.setText("SELECT A PROPERTY TO DISPLAY INFORMATION");
                }
            }
        });
        return false;
    }

    private void displayInformations(){
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.VISIBLE);
    }

    private void hideInformations(){
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
    }
}
