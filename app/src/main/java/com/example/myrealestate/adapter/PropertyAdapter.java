package com.example.myrealestate.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.R;
import com.example.myrealestate.models.Property;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    //Adaptater pour bind vue et data (RecyclerView)
    private final List<Property> properties;

    public PropertyAdapter(List<Property> properties) {
        this.properties = properties;
    }

    public final class PropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView placeName;
        private final TextView placeLocation;
        private final ImageView house;


        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.placeName);
            placeLocation = itemView.findViewById(R.id.placeLocation);
            house = itemView.findViewById(R.id.house);
        }

        public void updateViewHolder(final Property properties) {

            //Mise à jour de des données
            placeName.setText(properties.type);
            placeLocation.setText(properties.address);
        //    house.setImageResource();

            itemView.setOnClickListener(v -> {
//                final Intent intent = new Intent(itemView.getContext(), CityWeatherInformation.class);
//                intent.putExtra(CityWeatherInformation.CITY_NAME, city);
//                itemView.getContext().startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.real_estate_list, parent,false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        holder.updateViewHolder(properties.get(position));
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
}
