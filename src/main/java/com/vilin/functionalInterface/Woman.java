package com.vilin.functionalInterface;

public class Woman extends Human {
    @Override
    public void sayHello() {
        method(super::sayHello);
    }

    private void method(Greetable lambda) {
        lambda.greet();
        System.out.println("I'm a woman!");
    }

}
