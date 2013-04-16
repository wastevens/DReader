package com.dstevens.util;

public class Pair<K, V> {

    private final K first;
    private final V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public static <K, V> Pair<K, V> pair(K first, V second) {
        return new Pair<K, V>(first, second);
    }
    
    public K first() {
        return first;
    }
    
    public V second() {
        return second;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            @SuppressWarnings("rawtypes")
            Pair that = (Pair) o;
            return this.first.equals(that.first) &&
                   this.second.equals(that.second);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = 17;  
        result = 37*result + ((this.first != null) ? this.first.hashCode() : 0);  
        result = 37*result + ((this.second != null) ? this.second.hashCode() : 0);  
        return result;  
    }
}
