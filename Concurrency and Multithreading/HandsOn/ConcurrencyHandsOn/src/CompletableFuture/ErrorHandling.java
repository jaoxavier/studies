package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ErrorHandling {
    static void main() {
        // 1. Using exceptionally() to return a fallback value (as a 'catch')
        CompletableFuture<String> futureWithFallback = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Simulation failed");
            }
            return "Success!";
        }).exceptionally(ex -> {
            System.err.println("error: " + ex.getMessage());
            return "fallback value";
        });

        System.out.println("fallback: " + futureWithFallback.join());
        System.out.println("---");

        // 2. Using handle() to process sucess or failure as a finally
        CompletableFuture<String> futureHandled = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Simulation failed");
            }
            return "Success";
        }).handle((result, ex) -> {
            if (ex!= null) {
                System.err.println("error: " + ex.getMessage());
                return "result error process";
            }
            return "result sucess process: " + result;
        });

        System.out.println("result with handle: " + futureHandled.join());
    }
}
