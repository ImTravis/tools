package com.tools.link;

public abstract class FatherMethod {
    public FatherMethod(){System.out.println("\ninit 初始化方法");}


    protected abstract void refresh();

    public static void main(String args[]){
        ClassPath childOne = new ClassPath();
        childOne.refresh();
    }
}
