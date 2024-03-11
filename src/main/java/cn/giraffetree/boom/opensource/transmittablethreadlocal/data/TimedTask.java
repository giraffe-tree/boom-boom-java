package cn.giraffetree.boom.opensource.transmittablethreadlocal.data;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedTask implements Runnable {

    private final static AtomicInteger TASK_COUNTER = new AtomicInteger(0);

    private final long sleepMills;
    private final String taskId;

    private TimedTask(int sleepMills) {
        this.sleepMills = sleepMills;
        this.taskId = String.format("%03d", TASK_COUNTER.incrementAndGet());
    }

    public static TimedTask get(int sleepMills) {
        return new TimedTask(sleepMills);
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> start run task:" + taskId);
        try {
            Thread.sleep(sleepMills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> end run task:" + taskId);
        }
    }
}
