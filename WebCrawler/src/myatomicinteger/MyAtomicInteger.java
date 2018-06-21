package myatomicinteger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyAtomicInteger {
    private volatile Integer currentValue;
    private final Lock myLock = new ReentrantLock();

    public MyAtomicInteger() {
        this.currentValue = 0;
    }

    public MyAtomicInteger(Integer value) {
        this.currentValue = value;
    }

    public int increment() {
        myLock.lock();
        int modifiedValue = currentValue + 1;
        currentValue = modifiedValue;
        myLock.unlock();
        return modifiedValue;
    }

    public int decrement() {
        myLock.lock();
        int modifiedValue = currentValue - 1;
        currentValue = modifiedValue;
        myLock.unlock();
        return modifiedValue;
    }

    public String toString() {
        return this.currentValue.toString();
    }
}
