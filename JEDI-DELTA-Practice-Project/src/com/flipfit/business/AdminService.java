package com.flipfit.business;

public interface AdminService {
    boolean validateOwner(int ownerId);
    void viewFFCustomers();
    void deleteOwner(int ownerId);
    boolean login(String username, String password);
}