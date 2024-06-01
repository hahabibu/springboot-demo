package com.noob.base.annoation;

/**
 *  函数式接口注解 demo
 */
public class FunctionalInterfaceAnnotationDemo {
    @FunctionalInterface
    public interface Func1<T> {
        void printMessage(T message);
    }

    /**
     * @FunctionalInterface 修饰的接口中定义两个抽象方法，编译时会报错
     * @param <T>
     */
    /*
    @FunctionalInterface
    public interface Func2<T> {
        void printMessage(T message);
        void printMessage2(T message);
    }*/

    public static void main(String[] args) {
        Func1 func1 = message -> System.out.println(message);
        func1.printMessage("Hello");
        func1.printMessage(100);
    }
}
