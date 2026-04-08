package ExecutorsAndThreadsPool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTasksExample {

    static void main() {
        try(ScheduledExecutorService executor = Executors.newScheduledThreadPool(2)){
            // Execute one time after 3 seconds
            executor.schedule(() -> {
                System.out.println("Unique execution task executed at " + LocalTime.now());
            }, 3, TimeUnit.SECONDS);

            // Execute for each five seconds with a second of delay
            executor.scheduleAtFixedRate(() -> {
                System.out.println("Periodic task (fixed rate) executed at " + LocalTime.now());
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }, 1, 5, TimeUnit.SECONDS);

            executor.scheduleWithFixedDelay(() -> {
                System.out.println("Periodic task (fixed delay) executed at " + LocalTime.now());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }, 1, 5, TimeUnit.SECONDS);
        }


    }
}
