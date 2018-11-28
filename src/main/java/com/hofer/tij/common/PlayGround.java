package com.hofer.tij.common;

/**
 * @author hofer.bhf
 * created on 2018/05/19 5:28 PM
 */
public class PlayGround {
    public static void main(String[] args) {
        System.out.println(11);
        helloKitty();
        System.out.println(22);
    }

    private static void helloKitty() {
        try {
            System.out.println(1);
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        } finally {
            System.out.println(2);
        }
    }
}
