package com.vilin.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorMethodRef {

    public static void main(String[] args) {
        Supplier<Person> s1 = () -> new Person();
        Supplier<Person> s2 = Person::new;

        Supplier<List> s3 = ArrayList::new;
        Supplier<Thread> s4 = Thread::new;
        Supplier<Set> s5 = TreeSet::new;
        Supplier<String> s6 = String::new;

        Consumer<Integer> c1 = (age) -> new Account(age);
        Consumer<Integer> c2 = Account::new;
        c1.accept(10);
        c1.accept(100);

        Function<String, Account> f1 = (str) -> new Account(str);
        f1.apply("hello, world");
    }
}

class Person{

}

class Account{
    public  Account(){
        System.out.println("Account()");
    }

    public Account(int age){
        System.out.println("Account(int age)");
    }

    public Account(String str){
        System.out.println("Account(String str)");
    }
}
