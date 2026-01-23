package com.flipfit.bean;

public class Slot {

    private int slotId;
    private int centerId;
    private int startTime;
    private int seatsAvailable;

    public Slot(int slotId, int centerId, int startTime, int seatsAvailable) {
        this.slotId = slotId;
        this.centerId = centerId;
        this.startTime = startTime;
        this.seatsAvailable = seatsAvailable;
    }

    @Override
    public String toString() {
        return "SlotId=" + slotId +
               ", StartTime=" + startTime +
               ", SeatsAvailable=" + seatsAvailable;
    }
}
