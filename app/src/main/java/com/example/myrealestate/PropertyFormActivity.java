package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
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
    private EditText description;

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
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.property_details);

        cardGarage = findViewById(R.id.cardGarage);
        cardHouse = findViewById(R.id.cardHouse);
        cardBuilding = findViewById(R.id.cardBuilding);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        surfaceArea = findViewById(R.id.surfaceArea);
        numberOfRooms = findViewById(R.id.numberOfRooms);
        description = findViewById(R.id.description);
        findViewById(R.id.addProperty).setOnClickListener(this);

        cardGarage.setOnClickListener(view -> {
            cardGarage.setSelected(true);
            cardHouse.setSelected(false);
            cardBuilding.setSelected(false);
            sType="Garage";
        });

        cardHouse.setOnClickListener(view -> {
            cardHouse.setSelected(true);
            cardGarage.setSelected(false);
            cardBuilding.setSelected(false);
            sType="House";
        });

        cardBuilding.setOnClickListener(view -> {
            cardGarage.setSelected(false);
            cardHouse.setSelected(false);
            cardBuilding.setSelected(true);
            sType="Building";
        });
    }

    @Override
    public void onClick(View view) {

//        RealEstateRepository.getInstance(this).addProperty(new Property(sType,lPrice,lSurfaceArea,
//                iNumberOfRoom,sDescription,sAddress,lLatitude,lLongitude,lDateOfTheCreationAdvert,
//                lDateOfTheUpdateAdvert,bStatus,sNameAgent));
    }
}
