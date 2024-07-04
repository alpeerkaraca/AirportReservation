package threads.Async;

import model.Async.AsyncFlight;
import model.Async.AsyncSeat;

import java.util.Random;

public class AsyncReader implements Runnable {
    private final AsyncFlight asyncFlight;

    public AsyncReader(AsyncFlight asyncFlight) {
        this.asyncFlight = asyncFlight;
    }

    @Override
    public void run() {
        while (true) {
            int seatIndex = new Random().nextInt(asyncFlight.getSeatCount());
            AsyncSeat asyncSeat = asyncFlight.getSeat(seatIndex);
            asyncFlight.queryReservation(seatIndex);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
