package com.example.myrealestate.service;

public interface IPropertyStatusService {


    //Interface, contrat défini
    int getStatusByAvailability(boolean status);

    boolean getStatusById(int id);
}
