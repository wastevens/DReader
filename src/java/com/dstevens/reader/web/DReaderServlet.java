package com.dstevens.reader.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dstevens.reader.feeds.UserFeed;
import com.dstevens.reader.feeds.UserFeedProvider;
import com.dstevens.reader.feeds.item.UserFeedItemStatusRepository;
import com.dstevens.reader.feeds.source.UserFeedSource;
import com.dstevens.reader.feeds.source.UserFeedSourceRepository;

@Controller
@SessionAttributes("uid")
public class DReaderServlet {

    private final UserFeedProvider feedProvider;
    private final UserFeedSourceRepository repository;
    private final UserFeedItemStatusRepository statusRepository;

    @Autowired
    public DReaderServlet(UserFeedProvider feedProvider, UserFeedSourceRepository repository, UserFeedItemStatusRepository statusRepository) {
        this.feedProvider = feedProvider;
        this.repository = repository;
        this.statusRepository = statusRepository;
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String route(ModelMap model) {
        if (model.containsAttribute("uid")) {
            return "dReader";
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "/addFeed", method = RequestMethod.POST)
    public void addFeed(@RequestParam("feedUri") String feedUri, @ModelAttribute("uid") String userId, ModelMap model) {
      repository.saveFeed(new UserFeedSource(userId, feedUri));
    }
    
    @RequestMapping(value = "/deleteFeed", method = RequestMethod.POST)
    public void deleteFeed(@RequestParam("feedUri") String feedUri, @ModelAttribute("uid") String userId, ModelMap model) {
        repository.deleteFeed(userId, feedUri);
    }
    
    @RequestMapping(value = "/markAsRead", method = RequestMethod.POST)
    public void markFeedAsRead(@RequestParam("feedItemId") String feedItemId, ModelMap model) {
        statusRepository.markAsRead(feedItemId);
    }
    
    @RequestMapping(value = "/feeds", method = RequestMethod.POST)
    public @ResponseBody UserFeed getFeeds(@ModelAttribute("uid") String userId) {
      try {
          return feedProvider.forUser(userId);
      } catch(Exception e) {
          e.printStackTrace(System.out);
      }
      throw new IllegalStateException("Error getting feeds");
    }
}
