package com.dstevens.reader.deserialization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static com.dstevens.util.test.DateUtils.dateFor;

public class DateFormatterTest {

    @Test
    public void testFormatFrom() {
        DateFormatter formatter = new DateFormatter();
        
        assertEquals(dateFor(2013, 3, 22, -4), formatter.formatFrom("Fri, 22 Mar 2013 00:00:00 -0400"));
        assertEquals(dateFor(2013, 4, 2, -4), formatter.formatFrom("Tue, 2 Apr 2013 00:00:00 EST"));
        assertEquals(dateFor(2013, 3, 20, 0), formatter.formatFrom("2013-03-20T00:00:00Z"));
        assertEquals(dateFor(2013, 3, 22, 0), formatter.formatFrom("2013-03-22T00:00:00Z"));
        assertEquals(dateFor(2013, 3, 25, -4), formatter.formatFrom("25 Mar 2013 00:00:00 EST"));
        assertEquals(dateFor(2013, 4, 9, 0), formatter.formatFrom("2013-04-09T00:00:00Z"));
        assertEquals(dateFor(2013, 4, 11, -4), formatter.formatFrom("2013-04-11"));
        assertEquals(dateFor(2013, 4, 11, -4), formatter.formatFrom("2013-04-11 00:00:00"));
        assertEquals(dateFor(2011, 10, 7, -4), formatter.formatFrom("2011-10-07T00:00:00.000-04:00"));
    }
    
}
