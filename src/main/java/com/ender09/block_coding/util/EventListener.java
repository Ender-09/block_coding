package com.ender09.block_coding.util;

public interface EventListener<T> {
    void onEventFire(T[] args);
}
