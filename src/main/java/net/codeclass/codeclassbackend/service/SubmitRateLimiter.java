package net.codeclass.codeclassbackend.service;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SubmitRateLimiter {

    private static final int MAX_PER_MINUTE = 10;
    private static final long WINDOW_MS = 60_000;

    private final ConcurrentHashMap<String, ArrayDeque<Long>> windows = new ConcurrentHashMap<>();

    public boolean tryAcquire(String key) {
        long now = System.currentTimeMillis();
        ArrayDeque<Long> window = windows.computeIfAbsent(key, k -> new ArrayDeque<>());
        synchronized (window) {
            window.removeIf(t -> now - t > WINDOW_MS);
            if (window.size() >= MAX_PER_MINUTE) return false;
            window.addLast(now);
            return true;
        }
    }
}
