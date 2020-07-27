package com.tools.Test;

/**
 * @author xcc
 * @2020/6/3 15:12
 * 文件说明
 */
public class PeopleActionPost extends PeopleAction {
    public void eatAfter(){
        System.out.println("\n吃之后");
    }
    public void eatPost(){
        System.out.println("\n吃之前");
    }

    public static void main(String[] args){
        PeopleAction peopleActionPost = new PeopleActionPost();
        peopleActionPost.eat();
    }

}
