package com.dstevens.util;

import java.util.concurrent.ConcurrentHashMap;

public abstract class TimedDReaderCache<K, V> extends DReaderCache<K, V> {

    private ConcurrentHashMap<K, ExpiringValue> memory;
    private final long keepAliveInMilliseconds;

    public TimedDReaderCache(long keepAliveInMilliseconds) {
        this.keepAliveInMilliseconds = keepAliveInMilliseconds;
        this.memory = new ConcurrentHashMap<K, ExpiringValue>();
    }
    
    public V get(K key) {
        if (has(key)) {
            return memory.get(key).value();
        } 
        return null;
    }

    public V put(K key, V value) {
        memory.put(key, new ExpiringValue(System.currentTimeMillis() + keepAliveInMilliseconds, value));
        return value;
    }

    public boolean has(K key) {
        return (memory.containsKey(key) && !memory.get(key).isExpired(System.currentTimeMillis()));
    }
    
    private class ExpiringValue {
        private final long expireAt;
        private final V value;

        public ExpiringValue(long expireAt, V value) {
            this.expireAt = expireAt;
            this.value = value;
        }
        
        public boolean isExpired(long currentTime) {
            return currentTime >= expireAt;
        }
        
        public V value() {
            return value;
        }
    }
    
}
