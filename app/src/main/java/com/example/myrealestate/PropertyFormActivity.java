package com.example.myrealestate;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.myrealestate.Location.GeoLocation;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.preference.UserPreferences;
import com.example.myrealestate.repository.RealEstateRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    private CheckBox checkBox;
    private Button addProperty;

    private String sType;
    private double lPrice;
    private double lSurfaceArea;
    private int iNumberOfRoom;
    private String sDescription;
    private String sAddress;;
    private double lLatitude;;
    private double lLongitude;;
    private Long lDateOfTheCreationAdvert;
    private Long lDateOfTheUpdateAdvert;
    private boolean bStatus;
    private String sNameAgent;
    private Property property;

    private int idType;
    private int idStatus;
    private int idNameAgent;
    public static final String STATE = "state";
    public static final String sPROPERTY = "property";

    public enum State {ADD, UPDATE,}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.add_property);
        State state = (State) getIntent().getSerializableExtra("State");
        property = (Property) getIntent().getSerializableExtra(PropertyFormActivity.sPROPERTY);

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
        checkBox = findViewById(R.id.checkBox);
        addProperty = findViewById(R.id.addProperty);

        addProperty.setOnClickListener(view -> {
            if (checkMandatoryField()) {
                getViewInformation();
                if (state == State.ADD){
                RealEstateRepository.getInstance(this).addProperty(new Property(lPrice, lSurfaceArea, iNumberOfRoom, sDescription, sAddress,
                        lLatitude, lLongitude, lDateOfTheCreationAdvert, lDateOfTheUpdateAdvert, idType, idStatus, idNameAgent));
                displayNotification("ADD NEW PROPERTY",UserPreferences.getUserAgentProfile(this) + "add a new property on " + property.address+"!");
                }else if (state == State.UPDATE){
                    getViewInformation();
                    Map<String, Object> propertyMap= new HashMap<>();
                    propertyMap.put("price",lPrice);
                    propertyMap.put("surfaceArea",lSurfaceArea);
                    propertyMap.put("numberOfRoom",iNumberOfRoom);
                    propertyMap.put("description",sDescription);
                    propertyMap.put("address",sAddress);
                    propertyMap.put("latitude",lLatitude);
                    propertyMap.put("longitude",lLongitude);
                    propertyMap.put("dateOfTheCreationAdvert",property.dateOfTheCreationAdvert);
                    propertyMap.put("dateOfTheUpdateAdvert",lDateOfTheUpdateAdvert);
                    propertyMap.put("type",idType);
                    propertyMap.put("status",idStatus);
                    propertyMap.put("agentName",idNameAgent);
                    propertyMap.put("id",property.id);
                    RealEstateRepository.getInstance(this).updateProperty(propertyMap);
                    displayNotification("UPDATE PROPERTY",UserPreferences.getUserAgentProfile(this) + " update a property!");
                }
                finish();
            } else {
                displayErrorMessage();
            }
        });

        cardGarage.setOnClickListener(view -> {
            initCard();
            updateCard(cardGarage,textViewGarage,"Garage");
        });

        cardHouse.setOnClickListener(view -> {
            initCard();
            updateCard(cardHouse,textViewHouse,"House");
        });

        cardBuilding.setOnClickListener(view -> {
            initCard();
            updateCard(cardBuilding,textViewBuilding,"Building");
        });


        if (state == State.ADD){
            addProperty.setText("ADD NEW PROPERTY");
        }
        else if (state == State.UPDATE){
            addProperty.setText("UPDATE PROPERTY");
           updatePropertyData();
        }

    }

    private void updatePropertyData() {

        price.setText(String.valueOf(property.price));
        surfaceArea.setText(String.valueOf(property.surfaceArea));
        numberOfRooms.setText(String.valueOf(property.numberOfRoom));
        address.setText(property.address);
        description.setText(property.description);
        sType = RealEstateRepository.getInstance(this).getTypeById(property.typeId);
        lDateOfTheCreationAdvert = property.dateOfTheCreationAdvert;
        sNameAgent = RealEstateRepository.getInstance(this).getAgentNameById(property.agentId);
        checkBox.setChecked(RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId));

        switch(sType) {
            case "Garage":
                cardGarage.setSelected(true);
                textViewGarage.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case "House":
                cardHouse.setSelected(true);
                textViewHouse.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case "Building":
                cardBuilding.setSelected(true);
                textViewBuilding.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    private void initCard(){
        cardGarage.setSelected(false);
        cardHouse.setSelected(false);
        cardBuilding.setSelected(false);
        textViewBuilding.setTextColor(getResources().getColor(R.color.dark_blue));
        textViewGarage.setTextColor(getResources().getColor(R.color.dark_blue));
        textViewHouse.setTextColor(getResources().getColor(R.color.dark_blue));
    }

    private void updateCard(CardView principalCardView, TextView principalTextView, String type){
        principalCardView.setSelected(true);
        principalTextView.setTextColor(Color.parseColor("#FFFFFF"));
        sType=type;
    }

    @Override
    public void onClick(View view) {
        if (checkMandatoryField()) {
            getViewInformation();
            RealEstateRepository.getInstance(this).addProperty(new Property(lPrice, lSurfaceArea, iNumberOfRoom, sDescription, sAddress,
                    lLatitude, lLongitude, lDateOfTheCreationAdvert, lDateOfTheUpdateAdvert, idType, idStatus, idNameAgent));
            finish();
        } else {
            displayErrorMessage();
        }
    }

    private boolean checkMandatoryField() {

        boolean allFieldAreCompleted = false;

        if ((cardGarage.isSelected() || cardHouse.isSelected() || cardBuilding.isSelected()) &&
                !TextUtils.isEmpty(price.getText()) && !TextUtils.isEmpty(surfaceArea.getText())
                && !TextUtils.isEmpty(address.getText()) && !TextUtils.isEmpty(numberOfRooms.getText())
                && !TextUtils.isEmpty(description.getText()) ){
           allFieldAreCompleted = true;
        }
        return allFieldAreCompleted;
    }

    private void displayErrorMessage(){
        Toast.makeText(getApplicationContext(), "All field are required!", Toast.LENGTH_SHORT).show();
    }

    private void getViewInformation() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        lPrice = Double.parseDouble(String.valueOf(price.getText()));
        lSurfaceArea = Double.parseDouble(String.valueOf(surfaceArea.getText()));
        iNumberOfRoom = Integer.parseInt(String.valueOf(numberOfRooms.getText()));
        sDescription = String.valueOf(description.getText());
        sAddress = String.valueOf(address.getText());
        ArrayList location = GeoLocation.getAddress(sAddress,this);
        lLatitude = (double) location.get(0);
        lLongitude = (double) location.get(1);
        lDateOfTheCreationAdvert = timestamp.getTime();
        lDateOfTheUpdateAdvert = timestamp.getTime();

        bStatus = checkBox.isChecked();
        sNameAgent = UserPreferences.getUserAgentProfile(this);

        idType = RealEstateRepository.getInstance(this).getTypeByName(sType);
        idStatus = RealEstateRepository.getInstance(this).getStatusByAvailability(bStatus);
        idNameAgent = RealEstateRepository.getInstance(this).getAgentNameByName(sNameAgent);
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

    private void displayNotification(String title,String Body)
    {
        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        final String notificationChannelId = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? "MyChannel" : null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            final NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, "My Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManagerCompat.createNotificationChannel(notificationChannel);
        }

        final Intent intent = new Intent(this, PropertyListActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, notificationChannelId);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(Body);
        notificationBuilder.setSmallIcon(R.drawable.home);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setChannelId(notificationChannelId);
        notificationBuilder.setContentIntent(pendingIntent);

        notificationManagerCompat.notify(1, notificationBuilder.build());
    }
}
