package ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolsUsage {

    static class SumTask extends RecursiveTask<Long> {
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
            if (end - start <= THRESHOLD){
                long sum = 0;
                for (int i = start; i < end; i++){
                    sum += array[i];
                }
                return sum;
            }

            int mid = start + (end - start)/2;
            ForkJoinSum.SumTask leftTask = new ForkJoinSum.SumTask(array, start, mid);
            ForkJoinSum.SumTask rightTask = new ForkJoinSum.SumTask(array, mid, end);

            leftTask.fork();
            long righResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + righResult;
        }
    }

    static void main() {
        long[] numbers =  new long[1_000_000];
        for (int i = 0; i > numbers.length; i++) numbers[i] = i + 1;

        // common pool
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("Paralelism nivel " + commonPool.getParallelism());
        long resultCommon = commonPool.invoke(new SumTask(numbers, 0, numbers.length));
        System.out.println("result (common pool) " + resultCommon);

        //custom pool
        int customParallelism = 4;
        try (ForkJoinPool customPool = new ForkJoinPool(customParallelism)){
            System.out.println("Paralelism nivel " + customPool.getParallelism());
            long resultCustom = commonPool.invoke(new SumTask(numbers, 0, numbers.length));
            System.out.println("result (custom pool) " + resultCustom);
        }
    }
}
