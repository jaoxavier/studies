package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyVsThenCompose {

    record User(int id, String name) {};
    record Profile(int userId, String details){};

    static CompletableFuture<User> getUserById(int id){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Searching user " + id);
            return new User(id, "User" + id);
        });
    }

    static CompletableFuture<Profile> getProfileByUser(User user){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Searchin perfil for " + user.name());
            return new Profile(user.id(), "Perfil details");
        });
    }

    static void main() {
        // incorrect use
        CompletableFuture<CompletableFuture<Profile>> nestedFuture =
                getUserById(101).thenApply(user -> getProfileByUser(user));
        System.out.println("return type of thenApply:  " + nestedFuture.getClass().getSimpleName());

        CompletableFuture<Profile> flatFuture =
                getUserById(102).thenCompose(user -> getProfileByUser(user));
        System.out.println("return type of thenCompose:  " + flatFuture.getClass().getSimpleName());

        Profile profile = flatFuture.join();
        System.out.println(profile);
    }
}
