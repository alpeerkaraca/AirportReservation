package threads.Async;

import model.Async.AsyncFlight;
import model.Async.AsyncSeat;

import java.util.Random;

public class AsyncWriter implements Runnable {
    private final AsyncFlight asyncFlight;
    private final boolean reserve;

    public AsyncWriter(AsyncFlight asyncFlight, boolean reserve) {
        this.asyncFlight = asyncFlight;
        this.reserve = reserve;
    }

    @Override
    public void run() {
        while (true) {
            int seatIndex = new Random().nextInt(asyncFlight.getSeatCount());
            AsyncSeat asyncSeat = asyncFlight.getSeat(seatIndex);

            if (reserve) {
                asyncSeat.makeReservation();
                System.out.println("Writer Thread Reserved Seat: " + seatIndex);
            } else {
                asyncSeat.cancelReservation();
                System.out.println("Writer Thread Canceled Seat: " + seatIndex);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
