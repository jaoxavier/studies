package Syncronized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceStartupLatch {

    static class ServiceInitializer implements Runnable{
        private final String serviceName;
        private final CountDownLatch latch;
        private final int startupTime;

        public ServiceInitializer(String serviceName, int startupTime, CountDownLatch latch) {
            this.serviceName = serviceName;
            this.latch = latch;
            this.startupTime = startupTime;
        }

        @Override
        public void run() {
            try{
                System.out.println("starting " + this.serviceName);
                Thread.sleep(startupTime);
                System.out.println("finishing " + this.serviceName);
                latch.countDown();;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        static void main() {
            int numberOfServices = 3;
            CountDownLatch startupLatch = new CountDownLatch(numberOfServices);

            try(ExecutorService executor = Executors.newFixedThreadPool(numberOfServices)){
                executor.submit(new ServiceInitializer("DatabaseService", 3000, startupLatch));
                executor.submit(new ServiceInitializer("CacheService", 5000, startupLatch));
                executor.submit(new ServiceInitializer("MessagingService", 700, startupLatch));

                startupLatch.await();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

}
