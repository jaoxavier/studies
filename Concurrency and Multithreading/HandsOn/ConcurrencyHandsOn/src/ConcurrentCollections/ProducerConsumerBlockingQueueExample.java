package ConcurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerBlockingQueueExample {

    static void main() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            //producer
            executor.submit(()->{
                try{
                    for (int i = 0; i < 100; i++){
                        queue.put(i); // block if queue is full
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();;
                }
            });

            //consumer
            executor.submit(()-> {
               try{
                   while(true){
                       Integer value = queue.take();
                       Thread.sleep(100);
                       if (value == 99) break;
                   }
               }catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
            });
        }
    }
}
