package be.thomasmore.screeninfo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/start")
    public String getInternational() {
        return "start";
    }

    @GetMapping("/contact")
    public String getContactPagina() {
        return "contact";
    }
}
