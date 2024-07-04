package model.Async;

public class AsyncSeat {
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



    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean getReserved() {
        return reserved;
    }
}
