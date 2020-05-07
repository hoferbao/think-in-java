package com.hofer.interview.thread;

/**
 * @author hofer.bhf
 * created on 2020/5/6 8:59 PM
 * 3个线程t1t2t3，分别向3个文件写数据，t1只能写1，t2只能写2，t3只能写3
 * 要求最终运行的结果3个文件中的内容分别为
 * 123123123…
 * 312312312…
 * 231231231…
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();

    private ExecutorService pool = Executors.newFixedThreadPool(3);

    private static int count = 0;
    private static int PRINT_LENGTH = 10;

    private static boolean file1Flag = false;
    private static boolean file2Flag = false;
    private static boolean file3Flag = false;

    private static StringBuilder file1 = new StringBuilder("文件1:");
    private static StringBuilder file2 = new StringBuilder("文件2:");
    private static StringBuilder file3 = new StringBuilder("文件3:");

    static class T1 implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < PRINT_LENGTH; i++) {
                    while (count % 3 != 0) {
                        A.await();
                    }
                    file1Flag = true;
                    if (file1Flag) {
                        file1.append(1);
                    }
                    if (file2Flag) {
                        file2.append(1);
                    }
                    if (file3Flag) {
                        file3.append(1);
                    }
                    count++;
                    B.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < PRINT_LENGTH; i++) {
                    while (count % 3 != 1) {
                        B.await();
                    }
                    file2Flag = true;
                    if (file1Flag) {
                        file1.append(2);
                    }
                    if (file2Flag) {
                        file2.append(2);
                    }
                    if (file3Flag) {
                        file3.append(2);
                    }
                    count++;
                    C.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class T3 implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < PRINT_LENGTH; i++) {
                    while (count % 3 != 2) {
                        C.await();
                    }
                    file3Flag = true;
                    if (file1Flag) {
                        file1.append(3);
                    }
                    if (file2Flag) {
                        file2.append(3);
                    }
                    if (file3Flag) {
                        file3.append(3);
                    }
                    count++;
                    A.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.pool.execute(new T1());
        main.pool.execute(new T2());
        main.pool.execute(new T3());
        main.pool.shutdown();
        while (true) {
            if (main.pool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
            System.out.println("所有的子线程还没有结束！");
            Thread.sleep(1000);
        }

        System.out.println(file1.toString());
        System.out.println(file2.toString());
        System.out.println(file3.toString());
    }


}