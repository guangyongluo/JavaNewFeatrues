package com.vilin.enumeration.test;

public enum Gender implements IGender{
    MALE {
        @Override
        public void fun() {
            System.out.println("MALE fun()");
        }

        @Override
        public Gender nextGender() {
            return Gender.FEMAEL;
        }
    },
    FEMAEL{
        @Override
        public void fun() {
            System.out.println("FEMAILE fun()");
        }

        @Override
        public Gender nextGender() {
            return Gender.MALE;
        }
    };

    private Gender(){
        System.out.println("Gender()");
    }

    private Gender(int index){
        System.out.println("Gender( " + index + ")" );
    }

    public abstract Gender nextGender();

    class InnerClass{

    }
}
