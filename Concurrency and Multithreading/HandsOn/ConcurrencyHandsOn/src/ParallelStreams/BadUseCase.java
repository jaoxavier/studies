package ParallelStreams;

import java.util.List;
import java.util.stream.IntStream;

public class BadUseCase {

    static void main() {
        List<Integer> ids = IntStream.rangeClosed(1, 20).boxed().toList();

        long startTime = System.currentTimeMillis();

        ids.parallelStream().forEach(id -> {
            try {
                System.out.println("searching data from ID " + id + " in " + Thread.currentThread());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("total time: " + duration + " ms.");
    }
}
