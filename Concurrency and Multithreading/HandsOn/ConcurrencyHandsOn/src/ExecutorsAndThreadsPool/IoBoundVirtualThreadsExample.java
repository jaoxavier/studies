package ExecutorsAndThreadsPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class IoBoundVirtualThreadsExample {

    static void main() {
        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0, 100_000).forEach(i -> {
                executor.submit(() -> {
                    try{
                        System.out.println("Starting task I/O Bound " + i + " in " + Thread.currentThread());
                        Thread.sleep(1000);
                        System.out.println("Task I/O-Bound " + i + " concluded");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        }
    }
}
