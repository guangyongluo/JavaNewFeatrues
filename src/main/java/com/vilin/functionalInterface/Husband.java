package com.vilin.functionalInterface;

public class Husband {
    private void buyHouse() {
        System.out.println("买套房子");
    }

    private void marry(Richable lambda) {
        lambda.buy();
    }

    public void beHappy() {
        marry(() -> System.out.println("买套房子"));
    }

}
