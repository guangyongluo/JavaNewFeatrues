package com.vilin.test;

public class TestException {
    public void f1() throws Exception {
        throw new Exception("f1 exception");
    }

    public static void main(String[] args) {
        TestException te = new TestException();
        try {
            try {
                te.f1();
            } finally {
            }
            throw new Exception("main exception");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
