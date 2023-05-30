package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.Spot;
import be.thomasmore.screeninfo.repositories.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {

    @Autowired
    private SpotRepository spotRepository;

    @GetMapping("/map")
    public String defaultMapView(Model model,
                                 @RequestParam(required = false) boolean filterToillet,
                                 @RequestParam(required = false) boolean filterFestival,
                                 @RequestParam(required = false) boolean filterVoedKraam) {

        // chekken dat je wel aan het fiteren zijt of dat je pas opstart
        if (!filterToillet && !filterFestival && !filterVoedKraam) {
            filterToillet = true;
            filterFestival = true;
            filterVoedKraam = true;
        }

        List<Spot> spots = spotRepository.findAll(); // ik haal alle spots in om, daar na de spots te verdelen

        List<Spot> toilletes = new ArrayList<Spot>();
        List<Spot> festivals = new ArrayList<Spot>();
        List<Spot> voedselKraampjes = new ArrayList<Spot>();

        for (Spot spot : spots) {
            System.out.println(spot.getSpotType());
            if (spot.getSpotType().equals("TOILET")) {
                toilletes.add(spot);
            } else if (spot.getSpotType().equals("FESTIVAL")) {
                festivals.add(spot);
            } else if (spot.getSpotType().equals("VOEDSELKRAAM")) {
                voedselKraampjes.add(spot);
            }
        }

        //gaan chekken dat je filters gebruikt
        if (filterToillet) {
            model.addAttribute("toilletes", toilletes);
        }
        model.addAttribute("filterToillet", filterToillet);
        if (filterFestival) {
            model.addAttribute("festivals", festivals);
        }
        model.addAttribute("filterFestival", filterFestival);
        if (filterVoedKraam) {
            model.addAttribute("voedselKraampjes", voedselKraampjes);
        }
        model.addAttribute("filterVoedKraam", filterVoedKraam);

        return "map";
    }

    @GetMapping("/map2")
    public String map2(){
        return "map2";
    }
}
