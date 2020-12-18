package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.RealEstateRepository;

import java.sql.Timestamp;

public class PropertyDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ID = "idProperty";

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
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

        initData();
    }

    private void initData() {

        priceField.setText(String.valueOf(property.price));
        surfaceAreaField.setText(String.valueOf(property.surfaceArea));
        numberOfRoomsField.setText(String.valueOf(property.numberOfRoom));
        addressField.setText(String.valueOf(property.address));
        latitudeField.setText(String.valueOf(property.latitude));
        longitudeField.setText(String.valueOf(property.longitude));
        descriptionField.setText(String.valueOf(property.description));
        typeField.setText(String.valueOf(RealEstateRepository.getInstance(this).getTypeById(property.typeId)));
        agentField.setText(String.valueOf(RealEstateRepository.getInstance(this).getAgentNameById(property.agentId)));
        checkBoxField.setChecked(RealEstateRepository.getInstance(this).getStatusById(property.propertyStatusId));
        Timestamp timestampCreation = new Timestamp(property.dateOfTheCreationAdvert);
        Timestamp timestampUpdate = new Timestamp(property.dateOfTheUpdateAdvert);
        creationField.setText(String.valueOf(timestampCreation));
        updateField.setText(String.valueOf(timestampUpdate));
    }

    @Override
    public void onClick(View view) {

    }
}
