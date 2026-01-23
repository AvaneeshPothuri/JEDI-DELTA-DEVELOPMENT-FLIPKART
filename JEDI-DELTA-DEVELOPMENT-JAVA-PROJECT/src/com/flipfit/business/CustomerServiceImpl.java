/**
 * 
 */
package com.flipfit.business;

/**
 * 
 */
public class CustomerServiceImpl implements CustomerService{
	public void viewBookedSlots(int userId) {};
	public boolean checkBookingConflicts(int userId, int slotId) {
		return false;
	}
	public void makePayment(int bookingId) {};
	public void editDetails(int userId);
}
