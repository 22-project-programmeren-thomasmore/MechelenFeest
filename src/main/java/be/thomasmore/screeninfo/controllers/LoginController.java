package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.*;
import be.thomasmore.screeninfo.repositories.OrderRepository;
import be.thomasmore.screeninfo.repositories.UserRepository;
import be.thomasmore.screeninfo.repositories.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping({"/login", "/user/login"})
    public String login(Principal principal, Model model) {
        if (principal != null) return "redirect:/festivallijst";
        return "user/login";
    }

    @GetMapping({"/signup", "/user/signup"})
    public String register(Principal principal) {
        if (principal != null) return "redirect:/festivallijst";
        return "user/signup";
    }

    @PostMapping({"/signup", "/user/signup"})
    public String registerPost(Principal principal, @RequestParam String emailAddress, @RequestParam String userName, @RequestParam String password, @RequestParam(required = false, defaultValue = "false") boolean getUpdates,@RequestParam(required = false, defaultValue = "nl") String language) throws MessagingException {
        language = checkLanguageCode(language);

        if (principal != null) return "redirect:/festivallijst";
        if (emailAddress == null || emailAddress.trim().equals("")) return "redirect:/festivallijst?lang="+language;
        if (userName == null || userName.trim().equals("")) return "redirect:/festivallijst?lang="+language;
        if (password == null || password.trim().equals("")) return "redirect:/festivallijst?lang="+language;
        userName = userName.trim();
        System.out.println("TESTOLD CHECK " + password);
        String encodedPassword = encoder.encode(password.trim());
        System.out.println("TESTOLD CHECK " + encodedPassword);
        EndUser user = new EndUser(emailAddress, userName, encodedPassword, "ROLE_USER", true);
        userRepository.save(user);
        VerificationToken token = new VerificationToken(user);
        verificationTokenRepository.save(token);
        emailService.sendVerificationEmail(user, token);
        autologin(userName, password.trim());
        if (getUpdates)
            emailService.sendEmailWithAttachment(emailAddress, "Welkom", "Welkom bij de Mechelen Feest app");
        return "redirect:/signup-succes?lang="+language;
    }

    @GetMapping("/signup-succes")
    public String signupSucces() {
        return "user/signup-succes";
    }

    @GetMapping("/confirm-account/{verificationToken}")
    public String confirmUserAccount(@PathVariable String verificationToken, @RequestParam(required = false, defaultValue = "nl") String language) {
        language = checkLanguageCode(language);
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        if(token != null)
        {
            EndUser user = userRepository.findByEmailAddress(token.getUser().getEmailAddress());
            user.setEnabled(true);
            userRepository.save(user);
        }
        return "/confirm-account";
    }

    private void autologin(String userName, String password) {
        SecurityContext sc = SecurityContextHolder.getContext();
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication auth = authenticationManager.authenticate(token);
            logger.info("authentication: " + auth.isAuthenticated());
            logger.info("Logging in with [{}]", auth.getPrincipal());
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            sc.setAuthentication(null);
            logger.error("Failure in autoLogin", e);
        }
    }

    @GetMapping({"/profile", "/user/profile"})
    public String profile(Principal principal,Model model) {
        EndUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Order> orders = orderRepository.findAllByUser(user);
        if (!orders.isEmpty()){
            model.addAttribute("orders", orders);
        }
        return "user/profile";
    }

    private String checkLanguageCode(String language){
        if(language.trim().equals(""))
            return "nl";
        return language;
    }
}
