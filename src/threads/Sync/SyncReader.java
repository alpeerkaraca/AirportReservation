package threads.Sync;

import model.Sync.SyncFlight;

import java.util.Random;

public class SyncReader implements Runnable {
    private final SyncFlight flight;

    public SyncReader(SyncFlight flight) {
        this.flight = flight;
    }

    @Override
    public void run() {
        while (true) {
            int seatIndex = new Random().nextInt(flight.getSeatCount());
            flight.queryReservation(seatIndex);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
