package com.vilin.lambda;

@FunctionalInterface
public interface MyFunction<T> {
    public T getValue(T num);
}
