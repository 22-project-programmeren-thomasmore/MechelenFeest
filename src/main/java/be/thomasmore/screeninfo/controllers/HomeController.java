package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.EmailService;
import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.model.VerificationToken;
import be.thomasmore.screeninfo.repositories.UserRepository;
import be.thomasmore.screeninfo.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @GetMapping("/start")
    public String getInternational() {
        return "start";
    }

    @GetMapping("/contact")
    public String getContactPagina() {
        return "contact";
    }

    @GetMapping("/reset-password")
    public String getResetPassword() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPasswordPost(@RequestParam String emailAddress) {
        EndUser user = userRepository.findByEmailAddress(emailAddress);
        VerificationToken token = new VerificationToken(user);
        verificationTokenRepository.save(token);
        emailService.sendPasswordResetEmail(user, token);
        return "reset-password";
    }
}
