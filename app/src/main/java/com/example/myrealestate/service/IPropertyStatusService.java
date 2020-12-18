package com.example.myrealestate.service;

public interface IPropertyStatusService {

    int getStatusByAvailability(boolean status);

    boolean getStatusById(int id);
}
