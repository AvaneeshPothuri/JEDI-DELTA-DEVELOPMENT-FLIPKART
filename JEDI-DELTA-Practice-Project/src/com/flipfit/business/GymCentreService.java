package com.flipfit.business;

import java.util.List;
import com.flipfit.bean.Booking; // Assuming you have a Booking bean/model

public interface BookingService {

    void createBooking(Booking booking);

    List<Booking> getBookingsByUserId(int userId);

    boolean cancelBooking(int bookingId);
}