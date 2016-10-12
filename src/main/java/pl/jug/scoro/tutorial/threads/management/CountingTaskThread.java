package pl.jug.scoro.tutorial.threads.management;

class CountingTaskThread extends Thread {

    final CountingTask task;

    CountingTaskThread(int max) {
        this(new CountingTask(max));
    }

    private CountingTaskThread(CountingTask task) {
        super(task);
        this.task = task;
    }

}
