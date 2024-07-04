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

    public void queryReservation(int seatIndex) {
        System.out.println("Reader Thread Querying SeatNO: " + seatIndex + " Status: " + reserved);
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean getReserved() {
        return reserved;
    }
}
