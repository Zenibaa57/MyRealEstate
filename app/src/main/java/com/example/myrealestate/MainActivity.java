package com.example.myrealestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myrealestate.adapter.AgentAdapter;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.repository.RealEstateRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView agentRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.red));
        setContentView(R.layout.activity_main);

        agentRecyclerview= findViewById(R.id.agentRecyclerview);

//        RealEstateRepository.getInstance(this).addAgent(new Agent("Beth","Harmon","female","Business Woman"));
//        RealEstateRepository.getInstance(this).addAgent(new Agent("Boby","Fisher","Male","Business Man"));
//        RealEstateRepository.getInstance(this).addAgent(new Agent("Magnus","Carlsen","Male","Business Man"));
//        RealEstateRepository.getInstance(this).addAgent(new Agent("Garry","Kasparov","Male","Business Man"));
//        RealEstateRepository.getInstance(this).addAgent(new Agent("Anatoli","Karpov","Male","Business Man"));

        initList();
    }

    private void initList()
    {
        final List<Agent> agentList = RealEstateRepository.getInstance(this).getAgent();
        final AgentAdapter agentAdapter = new AgentAdapter(agentList, this);
        agentRecyclerview.setAdapter(agentAdapter);
    }
}