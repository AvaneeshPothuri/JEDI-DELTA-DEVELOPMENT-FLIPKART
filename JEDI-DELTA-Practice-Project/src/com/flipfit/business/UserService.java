package com.flipfit.business;

import java.util.List;
import com.flipfit.bean.Slot;

public interface UserService {

    boolean makeBooking(int userId, int centreId, int slotId);

    boolean cancelBooking(int userId, int bookingId);

    List<Slot> findAvailableSlots(int centreId);
}