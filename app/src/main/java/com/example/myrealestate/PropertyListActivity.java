package com.example.myrealestate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.adapter.PropertyAdapter;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.RealEstateRepository;
import com.example.myrealestate.viewmodels.PropertyViewModel;

import java.util.List;


public class PropertyListActivity extends AppCompatActivity {

    public static final String AGENT_FIRSTNAME = "agentFirstname";
     private PropertyViewModel propertyViewModel;
     private RecyclerView recyclerViewProperties;
     private String agentFirstname;

     private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.light_grey)));
        getSupportActionBar().setElevation(0);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        setContentView(R.layout.real_estate_list);
        final Agent agent = (Agent) getIntent().getSerializableExtra(PropertyListActivity.AGENT_FIRSTNAME);

        hello = findViewById(R.id.hello);
        hello.setText( getResources().getString(R.string.hello)+" "+ agent.firstname);


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
  /*      final List<Property> properties = RealEstateRepository.getInstance(this).getProperties();
        final PropertyAdapter propertyAdapter = new PropertyAdapter(properties);
        recyclerViewProperties.setAdapter(propertyAdapter);*/
    }
}
