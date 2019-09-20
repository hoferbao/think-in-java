package com.hoferbao.tij.jvm.ssy.bytecode;

/**
 * @author hofer.bhf
 * created on 2019/9/12 11:06 AM
 * $ cd out/production/classes
 * $ javap -c com.hofer.tij.jvm.ssy.bytecode.MyTest1
 * Compiled from "MyTest1.java"
 * public class com.hofer.tij.jvm.ssy.bytecode.MyTest1 {
 *   public com.hofer.tij.jvm.ssy.bytecode.MyTest1();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: aload_0
 *        5: iconst_1
 *        6: putfield      #2                  // Field a:I
 *        9: return
 *
 *   public int getA();
 *     Code:
 *        0: aload_0
 *        1: getfield      #2                  // Field a:I
 *        4: ireturn
 *
 *   public void setA(int);
 *     Code:
 *        0: aload_0
 *        1: iload_1
 *        2: putfield      #2                  // Field a:I
 *        5: return
 * }
 *
 */
public class MyTest1 {
    private int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

