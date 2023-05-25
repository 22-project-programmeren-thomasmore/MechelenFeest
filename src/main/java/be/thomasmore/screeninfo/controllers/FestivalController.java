package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Optional;

@Controller
public class FestivalController {

    @Autowired
    FestivalRepository festivalRepository;

    @GetMapping({"/","/festivallijst"})
    public String festivalList(Model model, Principal principal) {

        Iterable<Festival> festivals = festivalRepository.findAllByOrderByOnGoingDesc();
        model.addAttribute("festivals", festivals);

        return "festivallijst";
    }
    @GetMapping({"/festivaldetails", "/festivaldetails/{id}"})
    public String festivalDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id==null) return "festivaldetails";
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        if (optionalFestival.isPresent()) {
            model.addAttribute("festival", optionalFestival.get());
        }
        return "festivaldetails";
    }
}
