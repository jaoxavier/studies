package ParallelStreams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class GoodUseCase {
    static void main() {
        List<Long> numbers = LongStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());

        // task: find the sum of square from prime numbers in list
        // its CPU-bound, stateless and (ArrayList) is effectively divisible

        // Sequencial execution
        long startTimeSeq = System.nanoTime();
        long sumSeq = numbers.stream()
                .filter(GoodUseCase::isPrime)
                .mapToLong(n -> n * n)
                .sum();
        long durationSeq = (System.nanoTime() - startTimeSeq) / 1_000_000;
        System.out.println("Sequencial Result: " + sumSeq + " in " + durationSeq + " ms.");

        // Parallel execution
        long startTimePar = System.nanoTime();
        long sumPar = numbers.parallelStream()
                .filter(GoodUseCase::isPrime)
                .mapToLong(n -> n * n)
                .sum();
        long durationPar = (System.nanoTime() - startTimePar) / 1_000_000;
        System.out.println("Parallel result: " + sumPar + " in " + durationPar + " ms.");
    }

    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
