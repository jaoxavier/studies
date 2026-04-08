package Syncronized;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierSolver {

    static class Worker implements Runnable{
        private final int id;
        private final CyclicBarrier barrier;

        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run(){
            try {
                System.out.println("Worker " + id + " start phase 1");
                Thread.sleep(1000 + (id * 500));
                System.out.println("Worker " + id + "  end phase 1");
                barrier.await();

                System.out.println("Worker " + id + " start phase 2");
                Thread.sleep(1000 + (id * 500));
                System.out.println("Worker " + id + "  end phase 2");
                barrier.await();

                System.out.println("Worker " + id + " ended");

            }catch (BrokenBarrierException | InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static void main() {
        int numberOfWorkers = 3;

        Runnable barrierAction = () -> System.out.println("\n All workers concluded - Barrier broken");
        CyclicBarrier barrier = new CyclicBarrier(numberOfWorkers, barrierAction);

        try(ExecutorService executor = Executors.newFixedThreadPool(numberOfWorkers)){
            for (int i = 0; i < numberOfWorkers; i++){
                executor.submit(new Worker(i, barrier));
            }
        }
    }
}
