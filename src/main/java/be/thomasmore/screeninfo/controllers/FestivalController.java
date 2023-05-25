package be.thomasmore.screeninfo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.FestivalItem;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
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

        List<Festival> festivals = festivalRepository.findAllByOrderByStartDateAsc();
        List<FestivalItem> festivalItems = new ArrayList<>(); // om een makelijker manier te hebben voor html scripts

        for (Festival festival:festivals) {
            if(Date.from(java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).before(festival.getEndDate())){
                FestivalItem newFestivalItem = new FestivalItem(festival);
                festivalItems.add(newFestivalItem);
            }
        }

        model.addAttribute("festivals", festivalItems);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("festivalType",festivalType);
        model.addAttribute("festivals", festivals);

        return "festivallijst";
    }

}
