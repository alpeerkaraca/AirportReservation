package threads.Sync;

import model.Sync.SyncFlight;

import java.util.Random;

public class SyncWriter implements Runnable {
    private SyncFlight flight;
    private boolean reserve;

    public SyncWriter(SyncFlight flight, boolean reserve) {
        this.flight = flight;
        this.reserve = reserve;
    }

    @Override
    public void run() {
        while (true) {
            int seatIndex = new Random().nextInt(flight.getSeatCount());
            if (reserve) {
                flight.makeReservation(seatIndex);
            } else {
                flight.cancelReservation(seatIndex);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
