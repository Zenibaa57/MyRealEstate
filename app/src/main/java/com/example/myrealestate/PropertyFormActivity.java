package com.example.myrealestate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.myrealestate.preference.UserPreferences;


public class PropertyFormActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardGarage;
    private CardView cardHouse;
    private CardView cardBuilding;
    private EditText price;
    private EditText address;
    private EditText surfaceArea;
    private EditText numberOfRooms;
    private EditText description;
    private TextView textViewGarage;
    private TextView textViewHouse;
    private TextView textViewBuilding;

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
        textViewGarage = findViewById(R.id.textviewGarage);
        textViewHouse = findViewById(R.id.textviewHouse);
        textViewBuilding = findViewById(R.id.textviewBuilding);
        findViewById(R.id.addProperty).setOnClickListener(this);

        cardGarage.setOnClickListener(view -> {
            cardGarage.setSelected(true);
            cardHouse.setSelected(false);
            cardBuilding.setSelected(false);
            sType="Garage";
            textViewGarage.setTextColor(Color.parseColor("#FFFFFF"));
            textViewHouse.setTextColor(getResources().getColor(R.color.dark_blue));
            textViewBuilding.setTextColor(getResources().getColor(R.color.dark_blue));
        });

        cardHouse.setOnClickListener(view -> {
            cardHouse.setSelected(true);
            cardGarage.setSelected(false);
            cardBuilding.setSelected(false);
            sType="House";
            textViewHouse.setTextColor(Color.parseColor("#FFFFFF"));
            textViewGarage.setTextColor(getResources().getColor(R.color.dark_blue));
            textViewBuilding.setTextColor(getResources().getColor(R.color.dark_blue));
        });

        cardBuilding.setOnClickListener(view -> {
            cardGarage.setSelected(false);
            cardHouse.setSelected(false);
            cardBuilding.setSelected(true);
            sType="Building";
            textViewBuilding.setTextColor(Color.parseColor("#FFFFFF"));
            textViewGarage.setTextColor(getResources().getColor(R.color.dark_blue));
            textViewHouse.setTextColor(getResources().getColor(R.color.dark_blue));
        });
    }

    @Override
    public void onClick(View view) {

//        RealEstateRepository.getInstance(this).addProperty(new Property(sType,lPrice,lSurfaceArea,
//                iNumberOfRoom,sDescription,sAddress,lLatitude,lLongitude,lDateOfTheCreationAdvert,
//                lDateOfTheUpdateAdvert,bStatus,sNameAgent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Boite de dialogue lors de la suppression
        if (item.getItemId() == R.id.logout)
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
        }
        return super.onOptionsItemSelected(item);
    }
}
