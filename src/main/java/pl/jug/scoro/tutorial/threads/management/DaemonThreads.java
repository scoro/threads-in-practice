package pl.jug.scoro.tutorial.threads.management;

class DaemonThreads {

    public static void main(String[] args) {

        //Start demon thread
        CountingTaskThread countingTaskThread = new CountingTaskThread(10);
        countingTaskThread.setDaemon(true);
        countingTaskThread.start();
        System.out.println("Daemon thread started.");
    }

}
