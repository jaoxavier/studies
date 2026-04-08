package Syncronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreResourcePool {

    private static class ConnectionPool {
        private final Semaphore semaphore;

        public ConnectionPool(int maxConnections) {
            this.semaphore = new Semaphore(maxConnections);
        }

        public void useConnection(int threadId) throws InterruptedException{
            semaphore.acquire(); // get permission or block if unavailable
            try {
                System.out.println("Thread " + threadId + " get connection");
                Thread.sleep(2000);
                System.out.println("Thread " + threadId + " ended connection");
            }finally {
                semaphore.release();
            }
        }
    }

    static void main() {
        ConnectionPool pool = new ConnectionPool(3);

        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(()->{
                    try{
                        pool.useConnection(i);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                });
            });
        }
    }

}
