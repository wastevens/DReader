package com.dstevens.reader.feeds;

import org.junit.Before;
import org.junit.Test;

import com.dstevens.reader.feeds.source.UserFeedSource;
import com.dstevens.reader.feeds.source.UserFeedSourceMongoTranslator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import static org.junit.Assert.assertEquals;

public class UserFeedDefinitionMongoTranslatorTest {

    private UserFeedSourceMongoTranslator translator;

    @Before
    public void setUp() {
        translator = new UserFeedSourceMongoTranslator();
    }
    
    @Test
    public void testToDBObject() {
        String userIdentifier = "user";
        String uriFeed = "uri";
        UserFeedSource definition = new UserFeedSource(userIdentifier, uriFeed);
        
        DBObject dbObject = translator.from(definition);
        
        DBObject expectedDBObject = new BasicDBObject("ui",  userIdentifier).append("uri", uriFeed); 
        assertEquals(expectedDBObject, dbObject);
    }
    
    @Test
    public void testToUserFeedDefinitionDBObject() {
        String userIdentifier = "user";
        String uriFeed = "uri";
        DBObject dbObject = new BasicDBObject("ui",  userIdentifier).append("uri", uriFeed); 
        
        UserFeedSource definition = translator.from(dbObject);
        
        UserFeedSource expectedDefinition = new UserFeedSource(userIdentifier, uriFeed);
        assertEquals(expectedDefinition, definition);
    }
    
}
