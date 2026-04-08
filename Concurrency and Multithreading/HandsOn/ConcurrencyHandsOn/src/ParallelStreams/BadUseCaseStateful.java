package ParallelStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BadUseCaseStateful {
    static void main() {
        List<Integer> resultList = new ArrayList<>();

        // Wrong try to collect results
        // the sequence is not guarante on multiples threads
        // modify list no-thread safe
        IntStream.range(0, 1000).parallel().forEach(i -> {
            resultList.add(i);
        });

        System.out.println("List size (incorrect): " + resultList.size());

        // using correct collections thread safe
        List<Integer> correctList = IntStream.range(0, 1000)
                .parallel()
                .boxed()
                .collect(Collectors.toList());

        System.out.println("List size (correct): " + correctList.size());
    }
}
