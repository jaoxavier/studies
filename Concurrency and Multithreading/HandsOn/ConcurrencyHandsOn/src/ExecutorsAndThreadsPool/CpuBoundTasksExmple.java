package ExecutorsAndThreadsPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuBoundTasksExmple {

    static void main() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Numbers of cores: " + coreCount);

        try (ExecutorService executor = Executors.newFixedThreadPool(coreCount)){
            for (int i = 0; i < coreCount * 2; i++){
                final int taskNumber = i;
                executor.submit(() -> {
                    System.out.println("Start task CPU-bound " + taskNumber + " in " + Thread.currentThread());
                    long result = performIntensiveCalculator();
                    System.out.println("Task " + taskNumber + " concluded. Result:  " + result + " in " + Thread.currentThread());
                });
            }
        }
    }

    private static long performIntensiveCalculator() {
        long sum = 0;

        for (int i = 0; i < 1_000_000_000; i++){
            sum += i;
        }
        return sum;
    }
}
