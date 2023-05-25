package be.thomasmore.screeninfo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class FestivalController {

    private Logger logger = LoggerFactory.getLogger(FestivalController.class);
    @Autowired
    private FestivalRepository festivalRepository;

    @GetMapping({"/","/festivallijst","/festivallijst/{filter}"})
    public String festivalList(Model model, Principal principal,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String festivalType) {
        Iterable<Festival> festivals;

        if (keyword == null && festivalType==null) {
            festivals = festivalRepository.findAllByOrderByOnGoingDesc();
        }else {
            festivals = festivalRepository.findByQuery(keyword, festivalType);

        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("festivalType",festivalType);
        model.addAttribute("festivals", festivals);

        return "festivallijst";
    }

//    @GetMapping("/festivallijst/filter")
//    public String festivalListWithFilter(Model model,@RequestParam(required = false) String keyword){
//        logger.info("festivalListWithFilter -- keyword=" + keyword);
//        model.addAttribute("keyword", keyword);
//        List<Festival> festivals = festivalRepository.findByFestivalNameContainingIgnoreCase(keyword);
//        model.addAttribute("festivals",festivals);
//        return "festivallijst";
//    }

}
