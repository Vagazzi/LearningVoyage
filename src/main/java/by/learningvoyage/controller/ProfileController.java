package by.learningvoyage.controller;


import by.learningvoyage.model.User;
import by.learningvoyage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public String showProfileInfo(Model model){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) userService.loadUserByUsername(login);
        model.addAttribute("user", user);
        return "ShowProfile";
    }
}
