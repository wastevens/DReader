package com.dstevens.util.test;

import static org.junit.Assert.assertEquals;

public class EqualityTester {

    private final Object source;

    private EqualityTester(Object source) {
        this.source = source;
    }
    
    public static EqualityTester testing(Object o) {
        return new EqualityTester(o);
    }
    
    public EqualityTester assertEqualTo(Object o) {
        assertEquals("Expected " + source + " and " + o + " to be equal, but they weren't.", true, source.equals(o));
        assertEquals("Expected " + source + " and " + o + " to have the same hashcode, but they didn't.", true, source.hashCode() == o.hashCode());
        return this;
    }
    
    public EqualityTester assertNotEqualTo(Object o) {
        assertEquals("Expected " + source + " and " + o + " to not be equal, but they were.", false, source.equals(o));
        return this;
    }
    
}
