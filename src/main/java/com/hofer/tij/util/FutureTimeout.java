package com.hofer.tij.util;

import java.util.concurrent.*;

/**
 * @author hofer.bhf
 * created on 2020/4/8 8:42 PM
 */
public class FutureTimeout {

    public static void main(String[] args)  {
        Callable<String> task = () -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(task);
        try {
            String result = future.get(1, TimeUnit.SECONDS);
            System.out.println("result:" + result);
        } catch (Exception e) {
            System.out.println("timeout");
        }
        executorService.shutdown();
    }

}


