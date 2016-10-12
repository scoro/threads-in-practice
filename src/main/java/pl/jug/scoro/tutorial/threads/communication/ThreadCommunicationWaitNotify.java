package pl.jug.scoro.tutorial.threads.communication;

class ThreadCommunicationWaitNotify {

    private static class WorkingThread extends Thread {

        private int sum = 0;

        @Override
        public void run() {

            synchronized (this) {

                System.out.println("Computing");
                for (int i = 1; i <= 5; i++)
                    sum += i;

                System.out.println("Computation completed");
                notify();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        WorkingThread wt = new WorkingThread();
        synchronized (wt) {
            wt.start();
            System.out.println("Started");
            Thread.sleep(2_000);
            System.out.println("Waiting");
            wt.wait();
            System.out.println("Sum is: " + wt.sum);
        }
    }

}
