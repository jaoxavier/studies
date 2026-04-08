package ConcurrentCollections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteListenerExample {

    interface EventListener {
        void onEvent(String event);
    }

    static class Notifier{
        private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

        public void addListener(EventListener listener){
            listeners.add(listener);
        }

        public void notifyListerners(String event){
            System.out.println("Notifying " + listeners.size() + " about event " + event);

            for (EventListener listener : listeners) {
                listener.onEvent(event);

                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static void main() {
        Notifier notifier = new Notifier();
        notifier.addListener(event -> System.out.println("Listener A get: " + event));
        notifier.addListener(event -> System.out.println("Listener B get: " + event));

        try(ExecutorService executor = Executors.newFixedThreadPool(2)){
            executor.submit(() -> {
                for (int i = 0; i < 5; i++){
                    notifier.notifyListerners("Event " + i);
                }
            });

            executor.submit(() -> {
                try{
                    Thread.sleep(50);
                    notifier.addListener(event -> System.out.println("Listener C (new) get: " + event));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
