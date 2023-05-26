package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.EmailService;
import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.VerificationToken;
import be.thomasmore.screeninfo.repositories.UserRepository;
import be.thomasmore.screeninfo.repositories.VerificationTokenRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder encoder;
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

    @GetMapping("/new-password/{verificationToken}")
    public String newPassword(Model model,@PathVariable String verificationToken) {
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        if(token != null)
        {
            Optional<EndUser> user = userRepository.findById(token.getUser().getId());
            model.addAttribute("User", user);
            model.addAttribute("Token", token.getToken());
        }
        return "new-password";
    }

    @PostMapping({"/new-password/{verificationToken}"})
    public String newPasswordPost(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword, @PathVariable String verificationToken){
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        Optional<EndUser> foundUser = userRepository.findById(token.getUser().getId());
        EndUser user = foundUser.get();
        String oldEncodedPassword = encoder.encode(oldPassword.trim());

            if (newPassword.equals(confirmNewPassword)){
                System.out.println("TEST3 CHECK ");
                String newEncodedPassword = encoder.encode(confirmNewPassword.trim());
                user.setPassword(newEncodedPassword);
                userRepository.save(user);
            }

        return "new-password";
    }
}
