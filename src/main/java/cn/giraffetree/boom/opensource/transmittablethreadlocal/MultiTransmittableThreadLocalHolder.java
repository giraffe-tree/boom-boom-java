package cn.giraffetree.boom.opensource.transmittablethreadlocal;


import com.alibaba.ttl.TransmittableThreadLocal;

public class MultiTransmittableThreadLocalHolder {

    private final static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
    private final static TransmittableThreadLocal<String> contextB = new TransmittableThreadLocal<>();

    public static String getValue() {
        return context.get();
    }

    public static void setValue(String value) {
        context.set(value);
    }


    public static String getSecondValue() {
        return contextB.get();
    }

    public static void setSecondValue(String value) {
        contextB.set(value);
    }

}
