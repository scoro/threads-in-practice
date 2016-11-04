package pl.jug.scoro.tutorial.threads.communication;

import java.util.concurrent.*;

public class ThreadCommunicationFuture {

    private static class HelloTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(1_000);
            return "Hello!";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        long started = System.currentTimeMillis();

        Future<String> helloFuture = executor.submit(new HelloTask());
        String result = helloFuture.get();

        System.out.println(String.format("Result: %s, time: %dms", result, System.currentTimeMillis() - started));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

    }

}
