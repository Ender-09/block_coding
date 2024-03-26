package com.ender09.block_coding.util.events;

public interface EventListener<T> {
    void onEventFire(String eventType, T[] args);
}
