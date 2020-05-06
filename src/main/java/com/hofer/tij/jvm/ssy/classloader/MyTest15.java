package com.hofer.tij.jvm.ssy.classloader;

/**
 * @author hofer.bhf
 * created on classloader/05/19 5:39 PM
 */
public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println("new String[2].getClass().getClassLoader()=" + strings.getClass().getClassLoader());

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println("new MyTest15[2].getClass().getClassLoader()=" + myTest15s.getClass().getClassLoader());

        int[] ints = new int[2];
        System.out.println("new int[2].getClass().getClassLoader()=" + ints.getClass().getClassLoader());
    }
}
