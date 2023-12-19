package by.learningvoyage.controller;


import by.learningvoyage.model.Roles;
import by.learningvoyage.model.User;
import by.learningvoyage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/")
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("avatar") MultipartFile avatar,
                          Model model) throws SQLException, IOException {


        User user = new User();

        user.setLogin(login);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(Set.of(new Roles(1L,Roles.DEFAULT_USER)));
        user.setAvatar(userService.castToBlobs(avatar));

        if(!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        log.info("User {} was created", user);

        return "redirect:/";
    }
}
