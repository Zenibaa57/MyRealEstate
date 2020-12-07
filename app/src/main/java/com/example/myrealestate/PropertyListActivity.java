package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.adapter.PropertyAdapter;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.PropertyRepository;
import com.example.myrealestate.viewmodels.PropertyViewModel;

import java.util.List;


public class PropertyListActivity extends AppCompatActivity {

     private PropertyViewModel propertyViewModel;
     private RecyclerView recyclerViewProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        setContentView(R.layout.real_estate_list);

         propertyViewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Menu "icone exit"
        getMenuInflater().inflate(R.menu.menu_real_estate_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Boite de dialogue lors de la suppression
        return super.onOptionsItemSelected(item);
    }
    private void initList()
    {
        final List<Property> properties = PropertyRepository.getInstance(this).getProperties();
        final PropertyAdapter propertyAdapter = new PropertyAdapter(properties);
        recyclerViewProperties.setAdapter(propertyAdapter);
    }
}
