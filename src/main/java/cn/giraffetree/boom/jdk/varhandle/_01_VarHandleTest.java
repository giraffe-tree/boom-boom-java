package cn.giraffetree.boom.jdk.varhandle;

import cn.giraffetree.boom.common.Apple;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class _01_VarHandleTest {

    public static void main(String[] args) {
        testAppleSize();

    }

    private static void testAppleSize() {
        // 假设 Apple 类中的 size 是为非 public 属性， 则会抛出 IllegalAccessException 异常
        VarHandle sizeHandle;
        try {
            sizeHandle = MethodHandles.lookup()
                    .in(Apple.class)
                    .findVarHandle(Apple.class, "size", int.class);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            //  java.lang.IllegalAccessException: member is private: cn.giraffetree.boom.common.Apple.size/int/getField
            throw new RuntimeException(e);
        }
        Apple apple = new Apple();
        boolean accessModeSupportedSet = sizeHandle.isAccessModeSupported(VarHandle.AccessMode.SET);
        if (accessModeSupportedSet) {
            sizeHandle.set(apple, 10);
            System.out.println(apple);
        } else {
            System.out.println("no access to set apple size");
        }

        boolean accessModeSupportedSetVolatile = sizeHandle.isAccessModeSupported(VarHandle.AccessMode.SET_VOLATILE);
        if (accessModeSupportedSetVolatile) {
            sizeHandle.set(apple, 20);
            System.out.println(apple);
        } else {
            System.out.println("no access to set volatile apple size");
        }


    }




}
