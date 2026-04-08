package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenCombine {

    static CompletableFuture<String> getOrderHistory() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            return "Order History";
        });
    }

    static CompletableFuture<String> getShippingPreferences() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1200);
            } catch (Exception e) {}
            return "Preferências de Envio";
        });
    }

    static void main() {
        CompletableFuture<String> customerDataFuture =
                getOrderHistory().thenCombine(getShippingPreferences(), (history, prefs) -> {
                    return "client data: \n" + history + "\n" + prefs;
                });
        String customerData = customerDataFuture.join();
        System.out.println(customerData);
    }

}
