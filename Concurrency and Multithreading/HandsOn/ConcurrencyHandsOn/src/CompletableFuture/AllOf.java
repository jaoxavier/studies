package CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AllOf {
    static CompletableFuture<String> downloadData(String source) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int delay = 1000 + (int) (Math.random() * 1000);
                Thread.sleep(delay);
                System.out.println("Data from " + source + " downloaded in " + delay + "ms.");
            } catch (Exception e) {}
            return "Data from " + source;
        });
    }

    static void main() {
        List<String> sources = List.of("API_1", "API_2", "API_3", "API_4");

        List<CompletableFuture<String>> futures = sources.stream()
                .map(AllOf::downloadData)
                .collect(Collectors.toList());

        // CompletableFuture.allOf returns CompletableFuture<Void>.
        // Just to sinalize that every task has been completed
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // To get the results, need to process the original list of futures
        // after the conclusion of allDoneFuture
        CompletableFuture<List<String>> allResultsFuture = allDoneFuture.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join) // join() is safe because everyone concluded
                        .collect(Collectors.toList())
        );

        List<String> results = allResultsFuture.join();
        System.out.println("\nall downloads concluded. results:");
        results.forEach(System.out::println);
    }
}
