package com.example.myrealestate;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;


public class PropertyFormActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardGarage;
    private CardView cardHouse;
    private CardView cardBuilding;
    private EditText price;
    private EditText address;
    private EditText surfaceArea;
    private EditText numberOfRooms;
    private EditText latitude;
    private EditText longitude;
    private EditText description;
    private CheckBox status;

    private String sType;
    private long lPrice;
    private long lSurfaceArea;
    private int iNumberOfRoom;
    private String sDescription;
    private String sAddress;;
    private long lLatitude;;
    private long lLongitude;;
    private long lDateOfTheCreationAdvert;
    private long lDateOfTheUpdateAdvert;
    private boolean bStatus;
    private String sNameAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setElevation(0);
        Window window = this.getWindow();
        getSupportActionBar().hide();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.light_grey));
        setContentView(R.layout.property_details);

        cardGarage = findViewById(R.id.cardGarage);
        cardHouse = findViewById(R.id.cardHouse);
        cardBuilding = findViewById(R.id.cardBuilding);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        surfaceArea = findViewById(R.id.surfaceArea);
        numberOfRooms = findViewById(R.id.numberOfRooms);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);
        findViewById(R.id.addProperty).setOnClickListener(this);

        cardGarage.setOnClickListener(view -> {
            cardGarage.setSelected(true);
        });
    }

    @Override
    public void onClick(View view) {

//        RealEstateRepository.getInstance(this).addProperty(new Property(sType,lPrice,lSurfaceArea,
//                iNumberOfRoom,sDescription,sAddress,lLatitude,lLongitude,lDateOfTheCreationAdvert,
//                lDateOfTheUpdateAdvert,bStatus,sNameAgent));
    }
}
