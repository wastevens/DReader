package com.dstevens.reader.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.dstevens.reader.users.User;
import com.dstevens.reader.users.UserRepository;

@Controller
@SessionAttributes("uid")
public class AuthorizationServlet {

    private final UserRepository userRepository;

    @Autowired
    public AuthorizationServlet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @RequestMapping(value = "/login")
    public String login(ModelMap model) {
        model.remove("uid");
        return "login";
    }
    
    @RequestMapping(value="/persona/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout(ModelMap model) {
        model.remove("uid");
        return "login";
    }
    
    @RequestMapping(value = "/persona/auth", method = RequestMethod.POST)
    @ResponseBody
    public String authenticateWithPersona(@RequestParam("assertion") String assertion, ModelMap model)
            throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        
        Map<String, String> request = new HashMap<String, String>();
        request.put("assertion", assertion);
        request.put("audience", "http://localhost:8080");
        
        @SuppressWarnings("unchecked")
        Map<Object, Object> responseObject = restTemplate.postForObject("https://verifier.login.persona.org/verify", request, Map.class);
        PersonaAuthorization response = PersonaAuthorization.from(responseObject);
        if (response.isOkay()) {
            User user = userRepository.findBy(response.getEmail());
            if (user != null) {
                model.put("uid", user.getId());
            } else {
                model.put("uid", createUser(response).getId());
            }
            return "view";
        } else {
            return "login";
        }
    }

    private User createUser(PersonaAuthorization response) {
        User newUser = new User(null, response.getEmail());
        userRepository.save(newUser);
        User savedUser = userRepository.findBy(response.getEmail());
        return savedUser;
    }
    
}
