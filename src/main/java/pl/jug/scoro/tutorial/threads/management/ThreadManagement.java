package pl.jug.scoro.tutorial.threads.management;

import lombok.SneakyThrows;

class ThreadManagement {

    public static void main(String[] args) {

        //Start and wait
        CountingTaskThread countingTaskThread = new CountingTaskThread(10);
        countingTaskThread.start();
        System.out.println("Thread started. We let it go till the end.");

        waitForCompletion(countingTaskThread);


        //Start and interrupt after while
        countingTaskThread = new CountingTaskThread(10);
        countingTaskThread.start();
        System.out.println("Thread started. We are going to interrupt it after 5s");

        sleep(5_000);
        countingTaskThread.interrupt();

        waitForCompletion(countingTaskThread);


        //Start and interrupt after while
        countingTaskThread = new CountingTaskThread(10);
        countingTaskThread.start();
        System.out.println("Thread started. We are going to cancel it after 3s");

        sleep(3_000);
        countingTaskThread.task.cancel();

        waitForCompletion(countingTaskThread);
    }

    @SneakyThrows
    private static void waitForCompletion(CountingTaskThread countingTaskThread) {
        while(!countingTaskThread.task.isFinished()){
            Thread.sleep(100);
        }
    }

    @SneakyThrows
    private static void sleep(long millis){
        Thread.sleep(millis);
    }

}
