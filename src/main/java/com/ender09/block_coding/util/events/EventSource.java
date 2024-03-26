package com.ender09.block_coding.util.events;

import java.util.ArrayList;
import java.util.List;

public class EventSource<T> {
    private String eventType;
    private List<EventListener<T>> listeners = new ArrayList<>();

    public EventSource(String eventType) {
        this.eventType = eventType;
    }

    public void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }
    public void removeListener(EventListener<T> listener) {
        listeners.remove(listener);
    }

    @SafeVarargs
    public final void fireEvent(T... eventData) {
        for (EventListener<T> listener : listeners) {
            listener.onEventFire(eventType, eventData);
        }
    }
}