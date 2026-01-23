package com.flipfit.business;

public interface CustomerService {
	void vewBookedSlots(int userId);
	boolean checkBookingConflicts(int userId, int slotId);
	void viewCentres();
	void makePayments(int bookingId);
	void editDetails(int userId);
}
