package cn.giraffetree.boom.jdk.concurrent.completable;

import java.util.concurrent.CompletableFuture;

public class AsyncOnceTest {

    public static void main(String[] args) {
        testSupplyAsync();

    }

    private static void testSupplyAsync() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
        CompletableFuture<Void> f2 = f1.thenAccept(System.out::println);
        f2.join();
    }


}
