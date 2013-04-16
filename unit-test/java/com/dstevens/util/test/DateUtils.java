package com.dstevens.util.test;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateUtils {

    public static Date dateFor(int year, int month, int date) {
        return dateFor(year, month, date, 0);
    }
    
    public static Date dateFor(int year, int month, int date, int offset) {
        DateTime dateTime = new DateTime(DateTimeZone.forOffsetHours(offset)).
                                withYear(year).
                                withMonthOfYear(month).
                                withDayOfMonth(date).
                                withHourOfDay(0).
                                withMinuteOfHour(0).
                                withSecondOfMinute(0).
                                withMillisOfSecond(0);
        
        return dateTime.toDate();
    }
    
}
