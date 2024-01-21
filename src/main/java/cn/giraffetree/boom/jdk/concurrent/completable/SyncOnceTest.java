package cn.giraffetree.boom.jdk.concurrent.completable;

import java.util.concurrent.CompletableFuture;

public class SyncOnceTest {

    public static void main(String[] args) {
        testThenApply();

    }

    private static void testThenApply() {
        CompletableFuture<String> f1 = CompletableFuture.completedFuture("a");
        CompletableFuture<String> f2 = f1.thenApply(x -> x + 1);
        //
    }

    private static void testThenRun() {
        CompletableFuture<String> f1 = CompletableFuture.completedFuture("a");
        CompletableFuture<Void> f2 = f1.thenAccept(System.out::println);
        // f2.result = AltResult(null)
        // f2.stack = null
    }

}
