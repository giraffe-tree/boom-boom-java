# VarHandle

## 什么是 VarHandle？

简单来说作用有几个
1. 代替反射
2. 代替 sun.misc.Unsafe
3. 以更灵活和高效的方式操作字段和数组元素

JEP 193 中是这样描述的

> A variable handle is a typed reference to a variable, which supports read and write access to the variable under a variety of access modes. Supported variable kinds include instance fields, static fields and array elements. 

`VarHandle` 是对变量的类型化引用，它支持在各种访问模式下对变量进行读写访问。支持的变量类型包括实例字段、静态字段和数组元素。

## VarHandle 的主要特点

1. 类型安全：VarHandle 操作是类型安全的，可以在编译时检测到错误。 
2. 内存语义：支持不同的内存访问模式，例如普通、易失性、原子更新等。 
3. 性能优化：JVM 可以针对特定硬件平台优化 VarHandle 操作，提高性能。

## 说多无益，咋们上代码




