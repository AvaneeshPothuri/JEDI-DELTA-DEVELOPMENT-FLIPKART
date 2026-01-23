package com.flipfit.business;

import java.util.List;
import java.util.ArrayList;
import com.flipfit.bean.Slot;

public class UserServiceImpl implements UserService {

    @Override
    public boolean makeBooking(int userId, int centreId, int slotId) {
        // Logic: Check if user has enough balance/credits
        // Logic: Call DAO to insert record into Booking table
        // Logic: Update Slot table to decrement availability
        System.out.println("Booking created for User: " + userId + " at Centre: " + centreId);
        return true;
    }

    @Override
    public boolean cancelBooking(int userId, int bookingId) {
        // Logic: Check if booking belongs to this userId
        // Logic: Call DAO to delete/update status in Booking table
        // Logic: Increment Slot availability
        System.out.println("Booking " + bookingId + " cancelled for User: " + userId);
        return true;
    }

    @Override
    public List<Slot> findAvailableSlots(int centreId) {
        List<Slot> availableSlots = new ArrayList<>();
        // Logic: Call GymCentreDAO to fetch slots where centreId matches and capacity > 0
        return availableSlots;
    }
}