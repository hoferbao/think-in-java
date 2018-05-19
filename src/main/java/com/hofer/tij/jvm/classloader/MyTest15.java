package com.hofer.tij.jvm.classloader;

/**
 * @author hofer.bhf
 * created on 2018/05/19 5:39 PM
 */
public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("1.------");

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());

        System.out.println("2.------");

        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());
    }
}
