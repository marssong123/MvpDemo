package com.ssjj.androidmvpdemo.training;

/**
 * Created by songyu on 2018/4/18.
 */

public class BlockModel {
    private static Integer count = 0;
    private final Integer FULL = 5;
    private static Object lock = new Object();


    public static void main(String[] args) {
        BlockModel t = new BlockModel();
        new Thread(t.new Producer()).start();
        new Thread(t.new Consumer()).start();
        new Thread(t.new Producer()).start();
        new Thread(t.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (lock) {
                    while (count == FULL) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产者" + Thread.currentThread().getName()
                            + "已生产完成，商品数量：" + count);
                    lock.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (lock) {
                    while (count == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者" + Thread.currentThread().getName()
                            + "已消费，剩余商品数量：" + count);
                    lock.notifyAll();
                }
            }
        }

    }


}
