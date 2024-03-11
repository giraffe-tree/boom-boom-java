package cn.giraffetree.boom.opensource.transmittablethreadlocal.data;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedWithContextTask implements Runnable {

    private final static AtomicInteger TASK_COUNTER = new AtomicInteger(0);

    private final long sleepMills;
    private final String taskId;

    private TimedTaskContext context;

    private TimedWithContextTask(TimedTaskContext context,int sleepMills) {
        this.context = context;
        this.sleepMills = sleepMills;
        this.taskId = String.format("%03d", TASK_COUNTER.incrementAndGet());
    }

    public static TimedWithContextTask get(TimedTaskContext context, int sleepMills) {
        return new TimedWithContextTask(context, sleepMills);
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> start run task:" + taskId + " traceId:" + context.getTraceId());
        try {
            Thread.sleep(sleepMills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " -> end run task:" + taskId + " traceId:" + context.getTraceId());
        }
    }
}
