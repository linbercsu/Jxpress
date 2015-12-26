package com.example;

/**
 * Created by lin on 12/26/15.
 */
public class Looper {

    public static void loop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
