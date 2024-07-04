package main;

import model.Sync.SyncFlight;
import threads.Sync.SyncReader;
import threads.Sync.SyncWriter;

public class SyncThreadOperations {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Usage: java main.javaSyncThreadOperations <seat_count> <num_of_reader_threads> <num_of_writer_threads>");
            System.exit(1);
        }
        int seatCount = Integer.parseInt(args[0]);
        int readerThreadCount = Integer.parseInt(args[1]);
        int writerThreadCount = Integer.parseInt(args[2]);

        SyncFlight syncFlight = new SyncFlight(seatCount);
        Thread[] readerThreads = new Thread[readerThreadCount];
        Thread[] writerThreads = new Thread[writerThreadCount];

        for (int i = 0; i < readerThreadCount; i++) {
            SyncReader syncReader = new SyncReader(syncFlight);
            readerThreads[i] = new Thread(syncReader);
        }


        for (int i = 0; i < writerThreadCount; i++) {
            boolean reserveStatus = (i % 2 == 0);
            SyncWriter syncWriter = new SyncWriter(syncFlight, reserveStatus);
            writerThreads[i] = new Thread(syncWriter);
        }

        System.out.println("Operation will start with : " + seatCount + " seats, " + readerThreadCount + " reader threads, and " + writerThreadCount + " writer threads.");

        System.out.println("Press Enter To Start");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Thread readerThread : readerThreads) {
            readerThread.start();
        }

        for (Thread writerThread : writerThreads) {
            writerThread.start();
        }
    }
}
