package ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSum {

    static class SumTask extends RecursiveTask<Long>{
        private static final int THRESHOLD = 10_000;
        private final long[] array;
        private final int start;
        private final int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {
            int length = end - start;

            if (length <= THRESHOLD){
                long sum = 0;
                for (int i = start; i < end; i++){
                    sum += array[i];
                }
                return sum;
            }

            int mid = start + length/2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            leftTask.fork();
            long righResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + righResult;
        }
    }

    static void main() {
        long[] numbers = LongStream.rangeClosed(1, 1_000_000).toArray();

        try(ForkJoinPool pool = ForkJoinPool.commonPool()){
            long startTime = System.currentTimeMillis();
            long result = pool.invoke(new SumTask(numbers, 0, numbers.length));
            long endTime = System.currentTimeMillis();

            System.out.println("Resultado da soma: " + result);
            System.out.println("Tempo de execução: " + (endTime-startTime));
        }
    }
}
