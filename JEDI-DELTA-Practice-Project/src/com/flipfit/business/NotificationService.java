package com.flipfit.business;

public interface NotificationService{
    public void sendBookingConfirmation(int userId, int slotId);

    public void sendWaitlistPromotion(int userId, int slotId);
    
}
