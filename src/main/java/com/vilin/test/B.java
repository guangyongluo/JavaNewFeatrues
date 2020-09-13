package com.vilin.test;

public class B extends A {
    protected int i = 10;
    public void incI() {	i += 5;	}
    public void incJ() {	j += 5;	}
    public static void main(String[] args) {
        A a = new B();
        a.incI();
        a.incJ();
        B b = new B();
        b.incI();
        b.incJ();
        System.out.print(a.i + ", " + a.j+ ", " + b.i+ ", " + b.j);
    }
}

class A {
    protected int i = 0;
    protected int j = 0;
    public void incI() {	i++;	}
    public void incJ() {	j++;	}
}
