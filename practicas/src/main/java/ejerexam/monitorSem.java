package ejerexam;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;;

public class monitorSem {
    private static int permits;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();

    public monitorSem(int i) {
        permits = i;
    }

    public void waitSem() {
        lock.lock();
        try {
            while (permits == 0) {
                try {
                    cond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void signalSem() {
        lock.lock();
        try {
            permits++;
            cond.signal();
        } finally {
            lock.unlock();
        }
    }

}