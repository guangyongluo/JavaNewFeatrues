package com.vilin.test;

public class Component {
    private java.util.List<Component> children; //子部件
    private int weight; //自身重量
    public int getWeight(){
        int result = weight;
        if (children != null) {
            for(Component child : children) {
                if (child != null) {
                    result += child.weight;
                }
            }
        }
        return result;
    }
}
