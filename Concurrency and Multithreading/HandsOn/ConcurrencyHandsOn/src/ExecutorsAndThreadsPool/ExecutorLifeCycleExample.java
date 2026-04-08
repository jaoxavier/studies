package ExecutorsAndThreadsPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorLifeCycleExample {
    // Java 19 ExecutorService implements AutoClosable

    static void main() {
        try(ExecutorService executor = Executors.newFixedThreadPool(2)){
            executor.submit(() -> System.out.println("task executing"));
        }

        ExecutorService manualExecutor = Executors.newFixedThreadPool(2);

        try{
            manualExecutor.submit(() -> {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
            System.out.println("Task in manual executor concluded");
        }finally {
            shutdownAndAwaitTermination(manualExecutor);
        }
    }

    static void shutdownAndAwaitTermination (ExecutorService pool){
        pool.shutdown();

        try {
            if (!pool.awaitTermination(20, TimeUnit.SECONDS)){
                pool.shutdownNow();
                if (!pool.awaitTermination(20, TimeUnit.SECONDS)){
                    System.err.println("The POOL didnt finish");
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
