package cn.giraffetree.boom.opensource.transmittablethreadlocal;

import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedTask;
import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedTaskContext;
import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedWithContextTask;

import java.util.concurrent.*;

public class Demo02_origin_with_context_completable_future {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        test();

        EXECUTOR_SERVICE.shutdown();
    }

    private static void test() {
        TimedTaskContext context = new TimedTaskContext();
        context.setTraceId("aa01");

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(TimedWithContextTask.get(context, 100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(TimedWithContextTask.get(context, 100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f3 = CompletableFuture.allOf(f1, f2).thenRun(TimedWithContextTask.get(context, 100));

        CompletableFuture<Void> f4 = f3.thenRunAsync(TimedWithContextTask.get(context, 100), EXECUTOR_SERVICE);
        CompletableFuture<Void> f5 = f3.thenRunAsync(TimedWithContextTask.get(context, 100), EXECUTOR_SERVICE);

        CompletableFuture<Void> f6 = CompletableFuture.allOf(f4, f5).thenRun(TimedWithContextTask.get(context, 100));

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
