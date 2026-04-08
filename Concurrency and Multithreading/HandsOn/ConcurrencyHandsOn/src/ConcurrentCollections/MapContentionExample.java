package ConcurrentCollections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapContentionExample {

    static void main() {
        runTest(Collections.synchronizedMap(new HashMap<>()));
        runTest(new ConcurrentHashMap<>());
    }

    static void runTest(Map<String, Integer> map) {
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        int operationsPerThread = 100_000;

        try (ExecutorService executor = Executors.newFixedThreadPool(numThreads)){
            long startTime = System.nanoTime();

            for (int i =0; i < numThreads; i++){
                executor.submit(() -> {
                    for (int j = 0; j < operationsPerThread; j++){
                        String key = Thread.currentThread().getName() + "-" +j;
                        map.put(key, j);
                        map.get(key);
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            long endTime = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

            System.out.println("total time " + duration + " ms");
            System.out.println("total size of map " + map.size());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
