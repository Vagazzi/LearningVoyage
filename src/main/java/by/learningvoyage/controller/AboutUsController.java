package by.learningvoyage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/about")
    public String showPageAboutUs(){
        return "ShowPageAboutUs";
    }
}
