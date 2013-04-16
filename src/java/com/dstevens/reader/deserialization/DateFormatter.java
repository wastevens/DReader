package com.dstevens.reader.deserialization;

import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import static com.dstevens.util.CollectionUtils.list;

@Service
public class DateFormatter {

    public Date formatFrom(String stringDate) {
        if (stringDate == null) {
            return null;
        }
        List<DateTimeFormatter> formats = list(DateTimeFormat.forPattern("E, d MMM y HH:mm:ss Z"),
                                               DateTimeFormat.forPattern("E, d MMM y HH:mm:ss.SSS Z"),
                                               DateTimeFormat.forPattern("E, d MMM y HH:mm:ss z"),
                                               DateTimeFormat.forPattern("E, d MMM y HH:mm:ss.SSS z"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss Z"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"),
                                               DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"),
                                               DateTimeFormat.forPattern("d MMM yyyy HH:mm:ss z"),
                                               DateTimeFormat.forPattern("d MMM yyyy HH:mm:ss.SSS z"));
        
        for (DateTimeFormatter format : formats) {
            try {
                return format.parseDateTime(stringDate).toDate();
            } catch(IllegalArgumentException e) {
            }
        }
        return null;
    }
}
