package by.learningvoyage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SuctionController {

    @GetMapping("/suction")
    public String suction(){
        return "suction";
    }
}
