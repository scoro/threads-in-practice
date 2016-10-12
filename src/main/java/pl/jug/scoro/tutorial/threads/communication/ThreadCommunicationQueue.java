package pl.jug.scoro.tutorial.threads.communication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;

class ThreadCommunicationQueue {

    private static final Long POISON_PILL = Long.MIN_VALUE;

    @RequiredArgsConstructor
    private static class WorkingThread extends Thread {

        private final BlockingQueue<Long> requestQueue;

        private final BlockingQueue<Long> responseQueue;

        private long sum = 0;

        private volatile boolean cancelled = false;

        @Override
        public void run() {

            while(!cancelled) {

                try {
                    Long max = requestQueue.poll(100, TimeUnit.MILLISECONDS);

                    if(max == null)
                        continue;

                    if(POISON_PILL.equals(max)){

                        System.out.println("Received poison pill. Stopping...");
                        cancel();

                    }else {

                        for (long i = 1; i <= max; i++)
                            sum += i;

                        responseQueue.offer(sum);

                    }

                } catch (InterruptedException e) {
                    System.out.println("Cancelled due to interruption");
                    cancel();
                }

            }

            System.out.println("Worker stopped.");
        }

        public void cancel() {
            this.cancelled = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Long> requestQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Long> responseQueue = new LinkedBlockingQueue<>();

        WorkingThread wt = new WorkingThread(requestQueue, responseQueue);

        wt.start();

        long[] inputValues = new long[]{5,10,15};

        for (long input : inputValues) {
            requestQueue.put(input);
            long result = responseQueue.take();
            System.out.println(String.format("Sum of %d is %d", input, result));
        }

//        wt.cancel();
        requestQueue.put(POISON_PILL);

        System.out.println("Main thread stopped.");
    }

}
