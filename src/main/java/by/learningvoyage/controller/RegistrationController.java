package by.learningvoyage.controller;


import by.learningvoyage.model.Roles;
import by.learningvoyage.model.User;
import by.learningvoyage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
//        System.out.printf("deb");
        User userFromDb = userRepo.findByLogin(user.getLogin());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setRole(Collections.singleton(Roles.DEFAULT_USER));
        userRepo.save(user);
        return "redirect:/";
    }
}
