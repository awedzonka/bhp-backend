package com.awedzonka.bhpbackend.lib;


public class Sleep {

    public void run(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
