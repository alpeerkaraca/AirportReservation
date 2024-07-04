package main;

import model.Async.AsyncFlight;
import threads.Async.AsyncReader;
import threads.Async.AsyncWriter;

public class AsyncThreadOperations {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Usage: java main.AsyncThreadOperations <seat_count> <num_of_reader_threads> <num_of_writer_threads>");
            System.exit(1);
        }

        int seatCount = Integer.parseInt(args[0]);
        int readerThreadCount = Integer.parseInt(args[1]);
        int writerThreadCount = Integer.parseInt(args[2]);

        AsyncFlight asyncFlight = new AsyncFlight(seatCount);
        Thread[] readerThreads = new Thread[readerThreadCount];
        Thread[] writerThreads = new Thread[writerThreadCount];

        for(int i = 0; i < readerThreadCount; i++) {
            AsyncReader asyncReader = new AsyncReader(asyncFlight);
            readerThreads[i] = new Thread(asyncReader);
        }

        for(int i = 0; i < writerThreadCount; i++) {
            boolean reserveStatus = (i % 2 == 0);
            AsyncWriter asyncWriter = new AsyncWriter(asyncFlight, reserveStatus);
            writerThreads[i] = new Thread(asyncWriter);
        }

        System.out.println("Operation will start with : " + seatCount + " seats, " + readerThreadCount + " reader threads, and " + writerThreadCount + " writer threads.");
        System.out.println("Press Enter To Start");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Thread readerThread : readerThreads) {
            readerThread.start();
        }

        for(Thread writerThread : writerThreads) {
            writerThread.start();
        }


    }
}