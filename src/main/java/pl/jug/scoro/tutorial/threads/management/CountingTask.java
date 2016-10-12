package pl.jug.scoro.tutorial.threads.management;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CountingTask implements Runnable {

    private final int max;

    @Getter
    private boolean finished = false;

    private boolean cancelled = false;

    public void run() {

        int counter = 0;

        try {

            while (!cancelled && counter++ < max) {

                System.out.println(counter);

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted. Exiting...");
                    return;
                }
            }

        } finally {
            finished = true;
        }

    }

    void cancel() {
        this.cancelled = true;
    }
}
