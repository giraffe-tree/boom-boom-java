package cn.giraffetree.boom.opensource.transmittablethreadlocal;

import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedTask;
import cn.giraffetree.boom.opensource.transmittablethreadlocal.data.TimedTaskReadTransmittableValueTask;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo03_transmittable_thread_local {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        TransmittableThreadLocalHolder.setValue("hello transmittable thread local");
        Runnable task = TimedTaskReadTransmittableValueTask.get(100);
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);
        EXECUTOR_SERVICE.submit(ttlRunnable);
        
        // end
        EXECUTOR_SERVICE.shutdown();
    }
}
