package model.Async;

import java.util.ArrayList;
import java.util.List;

public class AsyncFlight {
    private final List<AsyncSeat> asyncSeats = new ArrayList<>();

    public AsyncFlight(int seatCount) {
        for (int i = 0; i < seatCount; i++) {
            asyncSeats.add(new AsyncSeat());
        }
    }

    public AsyncSeat getSeat(int index) {
        return asyncSeats.get(index);
    }

    public int getSeatCount() {
        return asyncSeats.size();
    }
}

