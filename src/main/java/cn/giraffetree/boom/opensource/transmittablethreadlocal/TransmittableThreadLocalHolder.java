package cn.giraffetree.boom.opensource.transmittablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TransmittableThreadLocalHolder {

    private final static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static String getValue() {
        return context.get();
    }

    public static void setValue(String value) {
        context.set(value);
    }

}
