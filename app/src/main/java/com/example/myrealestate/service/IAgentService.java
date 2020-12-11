package com.example.myrealestate.service;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;

import java.util.List;

public interface IAgentService {

    List<Agent> getAgent();

    void addAgent(Agent agent);
}
