package VirtualThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MassiveConcurrency {
    static void main() {
        long startTime = System.currentTimeMillis();

        // for each request its created a own virtual thread to execute
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 200_000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        } // try-with-resources makes the executor waits every task to close

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Process 200.000 tasks in " + duration + " ms.");
    }
}
