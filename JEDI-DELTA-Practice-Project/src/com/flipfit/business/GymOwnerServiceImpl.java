package com.flipfit.business;

public class GymOwnerServiceImpl implements GymOwnerService {
    @Override
    public void addCentre(int ownerId) {
        System.out.println("Gym Centre added for Owner: " + ownerId);
    }

    @Override
    public void viewCentres(int ownerId) {
        System.out.println("Listing all centres for owner...");
    }

    @Override
    public void viewCustomers(int centreId) {
        System.out.println("Listing customers for centre ID: " + centreId);
    }

    @Override
    public void viewPayments(int ownerId) {
        System.out.println("Displaying payment history...");
    }

    @Override
    public void editDetails(int ownerId) {
        System.out.println("Gym Owner details updated.");
    }
}