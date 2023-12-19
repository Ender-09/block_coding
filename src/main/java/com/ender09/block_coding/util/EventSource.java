package com.ender09.block_coding.util;

import java.util.ArrayList;
import java.util.List;

public class EventSource<T> {
    private List<EventListener<T>> listeners = new ArrayList<>();

    public void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(EventListener<T> listener) {
        listeners.remove(listener);
    }

    @SafeVarargs
    public final void fireEvent(T... eventData) {
        for (EventListener<T> listener : listeners) {
            listener.onEventFire(eventData);
        }
    }
}