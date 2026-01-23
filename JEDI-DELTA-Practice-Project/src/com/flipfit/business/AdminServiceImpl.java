package com.flipfit.business;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateOwner(int ownerId) {
        System.out.println("Owner " + ownerId + " has been approved by Admin.");
        return true;
    }

    @Override
    public void viewFFCustomers() {
        System.out.println("Fetching all registered customers...");
    }

    @Override
    public void deleteOwner(int ownerId) {
        System.out.println("Owner account " + ownerId + " deleted.");
    }

    @Override
    public boolean login(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }
}