package by.learningvoyage.controller;


import by.learningvoyage.model.Subcategory;
import by.learningvoyage.model.User;
import by.learningvoyage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/display/avatar")
    public ResponseEntity<byte[]> displayUserAvatar() throws SQLException {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) userService.loadUserByUsername(login);
        byte[] avatarBytes = user.getAvatar().getBytes(1, (int) user.getAvatar().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(avatarBytes);
    }
}
