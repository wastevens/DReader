package com.dstevens.util;

import java.util.concurrent.ConcurrentHashMap;

public abstract class DReaderCache<K, V> {

    private ConcurrentHashMap<K, V> memory;

    public DReaderCache() {
        memory = new ConcurrentHashMap<K, V>();
    }
    
    public void clear() {
        memory = new ConcurrentHashMap<K, V>();
    }

    public void evict(K key) {
        memory.remove(key);
    }

    public V get(K key) {
        return memory.get(key);
    }

    public V put(K key, V value) {
        memory.put(key, value);
        return value;
    }

    public boolean has(K key) {
        return memory.containsKey(key);
    }
    
    
}
