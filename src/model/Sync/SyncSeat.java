package model.Sync;

public class SyncSeat {
    private boolean reserved = false;

    public boolean isReserved() {
        return reserved;
    }

    public void makeReservation() {
        if (!reserved) {
            reserved = true;
        }
    }

    public void cancelReservation() {
        if (reserved) {
            reserved = false;
        }
    }
}
