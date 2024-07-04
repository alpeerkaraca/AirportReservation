package model.Sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SyncFlight {
    private final List<SyncSeat> seats = new ArrayList<>();
    private final Semaphore readerSemaphore = new Semaphore(1);
    private final Semaphore writerSemaphore = new Semaphore(1);
    private int readerCount = 0;

    public SyncFlight(int seatCount) {
        for (int i = 0; i < seatCount; i++) {
            seats.add(new SyncSeat());
        }
    }

    public void queryReservation(int seatIndex) {
        try {
            readerSemaphore.acquire();
            readerCount++;
            if (readerCount == 1) {
                writerSemaphore.acquire();
            }
            readerSemaphore.release();

            SyncSeat seat = getSeat(seatIndex);
            System.out.println("Reader checked seat " + seatIndex + ": " +
                    (seat.isReserved() ? "Reserved" : "Available"));

            readerSemaphore.acquire();
            readerCount--;
            if (readerCount == 0) {
                writerSemaphore.release();
            }
            readerSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeReservation(int seatIndex) {
        try {
            writerSemaphore.acquire();
            SyncSeat seat = getSeat(seatIndex);
            seat.makeReservation();
            System.out.println("Writer reserved seat " + seatIndex);
            writerSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cancelReservation(int seatIndex) {
        try {
            writerSemaphore.acquire();
            SyncSeat seat = getSeat(seatIndex);
            seat.cancelReservation();
            System.out.println("Writer cancelled" +
                    " reservation for seat " + seatIndex);
            writerSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SyncSeat getSeat(int index) {
        return seats.get(index);
    }

    public int getSeatCount() {
        return seats.size();
    }
}

