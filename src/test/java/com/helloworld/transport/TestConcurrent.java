package com.helloworld.transport;

import java.util.concurrent.CountDownLatch;

/**
 * Class description
 * 并发编程测试之重排序
 *
 * @author LiangHang
 * @date 2019/11/5 22:57
 */
public class TestConcurrent {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for ( ; ; ) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();
            String result = "第" + i + "次（" + x + "，" + y + "）";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.err.println(result);
            }
        }
    }
}
