package model.Async;

import model.Sync.SyncSeat;

import java.util.ArrayList;
import java.util.List;

public class AsyncFlight {
    private final List<AsyncSeat> asyncSeats = new ArrayList<>();

    public AsyncFlight(int seatCount) {
        for (int i = 0; i < seatCount; i++) {
            asyncSeats.add(new AsyncSeat());
        }
    }

    public void queryReservation(int seatIndex) {
        AsyncSeat seat = getSeat(seatIndex);
        System.out.println("Reader checked seat " + seatIndex + ": " + (seat.isReserved() ? "Reserved" : "Available"));
    }

    public AsyncSeat getSeat(int index) {
        return asyncSeats.get(index);
    }

    public int getSeatCount() {
        return asyncSeats.size();
    }
}

