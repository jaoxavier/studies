package Syncronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class DynamicPhaser {

    static class Task implements Runnable {
        private final int id;
        private final Phaser phaser;

        public Task(int id, Phaser phaser) {
            this.id = id;
            this.phaser = phaser;
        }

        @Override
        public void run(){
            System.out.println("task " + id + " phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();

            if (id % 2 == 0){
                System.out.println("task " + id + " phase " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println("task " + id + " phase " + phaser.getPhase());
                phaser.arriveAndDeregister();
            }else {
                System.out.println("task " + id + " phase " + phaser.getPhase());
                phaser.arriveAndDeregister();
            }
        }
    }

    static void main() {
        Phaser phaser = new Phaser(1);

        try(ExecutorService executor = Executors.newCachedThreadPool()){
            for (int i = 0; i < 4; i++){
                executor.submit(new Task(i, phaser));
            }

            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }
}
