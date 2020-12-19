package com.example.myrealestate;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myrealestate.exchangeAPI.IExchangeAPI;
import com.example.myrealestate.exchangeAPI.Retrofit;
import com.example.myrealestate.exchangeAPI.UsdToEur;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.preference.UserPreferences;
import com.example.myrealestate.repository.RealEstateRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ID = "idProperty";
    public static final String NAME = "name";

    private String agentName;
    private String type;
    private boolean status;

    private TextView priceField;
    private TextView surfaceAreaField;
    private TextView numberOfRoomsField;
    private TextView typeField;
    private TextView addressField;
    private TextView latitudeField;
    private TextView longitudeField;
    private TextView agentField;
    private TextView creationField;
    private TextView updateField;
    private TextView descriptionField;
    private CheckBox checkBoxField;
    private Property property;
    private Button convertButton;
    private Button editButton;
    private Button deleteButton;
    private ImageView typeImage;
    final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private double eurRates;
    private String defaultRate ="usd";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        Intent d = getIntent();
        Bundle b = d.getExtras();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle((String) b.get("name"));
        setContentView(R.layout.property_details);
        property = (Property) getIntent().getSerializableExtra(PropertyDetailsActivity.ID);

        priceField = findViewById(R.id.priceField);
        surfaceAreaField = findViewById(R.id.surfaceAreaField);
        numberOfRoomsField = findViewById(R.id.numberOfRoomsField);
        typeField = findViewById(R.id.typeField);
        addressField = findViewById(R.id.adressField);
        latitudeField = findViewById(R.id.latitudeField);
        longitudeField = findViewById(R.id.longitudeField);
        agentField = findViewById(R.id.agentNameField);
        creationField = findViewById(R.id.creationField);
        updateField = findViewById(R.id.updateField);
        descriptionField = findViewById(R.id.descriptionField);
        checkBoxField = findViewById(R.id.checkBoxField);
        convertButton = findViewById(R.id.convertButton);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setVisibility(View.GONE);
        typeImage = findViewById(R.id.typeImage);

        convertButton.setOnClickListener(view -> {
            if (defaultRate.equals("usd")){
                priceField.setText(formatPrice(property.price * eurRates) +" €");
                defaultRate="eur";
                convertButton.setText("CONVERT TO $");
            } else if (defaultRate.equals("eur")){
                priceField.setText("$ "+ property.price);
                defaultRate="usd";
                convertButton.setText("CONVERT TO €");
            }
        });

        editButton.setOnClickListener(view -> {
            Intent intent = new Intent(PropertyDetailsActivity.this, PropertyFormActivity.class);
            intent.putExtra("State", PropertyFormActivity.State.UPDATE);
            intent.putExtra(PropertyFormActivity.sPROPERTY, property);
            startActivity(intent);
        });

        deleteButton.setOnClickListener(view -> {
            RealEstateRepository.getInstance(this).deletePropertyById(property.id);
            finish();
        });

        try {
            initData();
            callAPI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        //Cycle de vie de l'application retour sur le parent
        super.onResume();
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() throws IOException {

        //Initialize primary data
        agentName = RealEstateRepository.getInstance(this).getAgentNameById(property.agentId);
        type = String.valueOf(RealEstateRepository.getInstance(this).getTypeById(property.typeId));
        status = RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId);

        priceField.setText("$ "+ formatPrice(property.price));
        surfaceAreaField.setText(property.surfaceArea +" m²");
        numberOfRoomsField.setText(property.numberOfRoom +" rooms");
        addressField.setText(String.valueOf(property.address));
        latitudeField.setText(String.valueOf(property.latitude));
        longitudeField.setText(String.valueOf(property.longitude));
        descriptionField.setText(String.valueOf(property.description));
        typeField.setText(type);
        agentField.setText(agentName);
        checkBoxField.setChecked(status);
        Timestamp timestampCreation = new Timestamp(property.dateOfTheCreationAdvert);
        Timestamp timestampUpdate = new Timestamp(property.dateOfTheUpdateAdvert);
        creationField.setText(String.valueOf(timestampCreation));
        updateField.setText(String.valueOf(timestampUpdate));
        typeImage.setImageResource(property.getPlace(this));

        //Initialize button
        if (agentName.equals(UserPreferences.getUserAgentProfile(this)))
            deleteButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

    }

    private void callAPI() {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        IExchangeAPI iExchangeAPI = Retrofit.getclient(okHttp).create(IExchangeAPI.class);
        Call<UsdToEur> callExchangeUsdToEurAPI = iExchangeAPI.getUsdToEur("USD");
        callExchangeUsdToEurAPI.enqueue(new Callback<UsdToEur>() {
            @Override
            public void onResponse(Call<UsdToEur> call, Response<UsdToEur> response) {
                try {
                    eurRates = Double.parseDouble(response.body().rates.EurRates);
                } catch (Exception e) { }
            }
            @Override
            public void onFailure(Call<UsdToEur> call, Throwable t) {
            }
        });
    }

    private String formatPrice(double price){
        DecimalFormat df = new DecimalFormat () ;
        df.setMaximumFractionDigits (2) ;
        df.setDecimalSeparatorAlwaysShown (true) ;
        return df.format((price));
    }
}
