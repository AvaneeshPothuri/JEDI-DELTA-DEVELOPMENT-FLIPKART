package com.flipfit.business;

public interface GymOwnerService {
	void addCentre(FlipFitGymCentre centre);
	void viewCentre(int ownerId);
	void viewFFCustomers();
	void viewPayments();
	void editDetails(int ownerId);
}
