package com.company.svt.LSVdeveloper;

import java.util.Vector;

public class Main {

    private Vector data;

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        data = new Vector();
        new Thread(new RunnableNote()).start();
        new Thread(new RunnableRead()).start();
    }

    private class RunnableNote implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i<20; i++) {
                data.add(i);
                sleep(getSleepDuration());
            }
        }
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getSleepDuration() {
        return (int) (Math.random() * 1600);
    }

    private class RunnableRead implements Runnable {

        @Override
        public void run() {
            sleep(100);
            for (int i = 0; ; i++) {
                if (data.size()>i){
                    System.out.print(data.get(i)+" ");
                } else {
                    throw new RuntimeException();
                }
                sleep(1000);
            }
        }
    }
}