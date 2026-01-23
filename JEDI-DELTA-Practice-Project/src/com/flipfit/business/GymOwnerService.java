package com.flipfit.business;

public interface GymOwnerService{
    void addCentre(int ownerId);
    void viewCentres(int ownerId);
    void viewCustomers(int centreId);
    void viewPayments(int ownerId);
    void editDetails(int ownerId);
}