package cn.giraffetree.boom.opensource.transmittablethreadlocal;

import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedTask;

import java.util.concurrent.*;

public class Demo01_origin_completable_future {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        test();

        EXECUTOR_SERVICE.shutdown();
    }

    private static void test() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(TimedTask.get(100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(TimedTask.get(100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f3 = CompletableFuture.allOf(f1, f2).thenRun(TimedTask.get(100));

        CompletableFuture<Void> f4 = f3.thenRunAsync(TimedTask.get(100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f5 = f3.thenRunAsync(TimedTask.get(100), EXECUTOR_SERVICE);

        CompletableFuture<Void> f6 = CompletableFuture.allOf(f4, f5).thenRun(TimedTask.get(100));

        try {
            f6.get(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
