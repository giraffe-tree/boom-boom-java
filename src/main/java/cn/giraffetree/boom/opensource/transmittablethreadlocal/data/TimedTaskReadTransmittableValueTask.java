package cn.giraffetree.boom.opensource.transmittablethreadlocal.data;

import cn.giraffetree.boom.opensource.transmittablethreadlocal.TransmittableThreadLocalHolder;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedTaskReadTransmittableValueTask implements Runnable {

    private final static AtomicInteger TASK_COUNTER = new AtomicInteger(0);

    private final long sleepMills;
    private final String taskId;

    private TimedTaskReadTransmittableValueTask(int sleepMills) {
        this.sleepMills = sleepMills;
        this.taskId = String.format("%03d", TASK_COUNTER.incrementAndGet());
    }

    public static TimedTaskReadTransmittableValueTask get(int sleepMills) {
        return new TimedTaskReadTransmittableValueTask(sleepMills);
    }

    @Override
    public void run() {
        String value = TransmittableThreadLocalHolder.getValue();
        System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> start run task:" + taskId + " value:" + value);
        try {
            Thread.sleep(sleepMills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            value = TransmittableThreadLocalHolder.getValue();
            System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> end run task:" + taskId + " value:" + value);
        }
    }

}
