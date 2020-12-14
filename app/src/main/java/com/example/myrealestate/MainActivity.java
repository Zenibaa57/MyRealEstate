package com.example.myrealestate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealestate.adapter.AgentAdapter;
import com.example.myrealestate.preference.UserPreferences;
import com.example.myrealestate.viewmodels.AgentViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView agentRecyclerview;
    private AgentViewModel agentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.dark_blue));
        setContentView(R.layout.activity_main);

        agentRecyclerview= findViewById(R.id.agentRecyclerview);
        agentViewModel= new ViewModelProvider(this).get(AgentViewModel.class);
        initList();
        checkPreference();
    }

    private void initList()
    {
        agentViewModel.agents.observe(this, agents -> {
            final AgentAdapter agentAdapter = new AgentAdapter(agents);
            agentRecyclerview.setAdapter(agentAdapter);
        });
    }

    private void checkPreference()
    {
        final String userLogin = UserPreferences.getUserAgentProfile(this);
        if ( !TextUtils.isEmpty(userLogin)) {
            final Intent intent = new Intent(this, PropertyListActivity.class);
            this.startActivity(intent);
           finish();
        }
    }

}