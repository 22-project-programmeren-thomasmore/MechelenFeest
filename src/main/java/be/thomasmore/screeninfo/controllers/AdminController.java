package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FestivalRepository festivalRepository;

    @GetMapping({"/festivaleditor", "/festivaleditor/{id}"})
    public String editFestival(Model model, @PathVariable(required = false) Integer id) {
        Optional<Festival> optionalFestival;
        if (id != null) {
            optionalFestival = festivalRepository.findById(id);
        } else {
            optionalFestival = festivalRepository.findFirstByOrderByIdAsc();
        }
        Festival festival = optionalFestival.get();
        model.addAttribute("festival", festival);
        Optional<Festival> optionalPrevFestival = festivalRepository.findFirstByIdLessThanOrderByIdDesc(festival.id);
        Optional<Festival> optionalNextFestival = festivalRepository.findFirstByIdGreaterThanOrderById(festival.id);
        if (optionalPrevFestival.isPresent()) {
            model.addAttribute("prev", optionalPrevFestival.get().getId());
        } else {
            model.addAttribute("prev", festivalRepository.findFirstByOrderByIdDesc().get().getId());
        }
        if (optionalNextFestival.isPresent()) {
            model.addAttribute("next", optionalNextFestival.get().getId());
        } else {
            model.addAttribute("next", festivalRepository.findFirstByOrderByIdAsc().get().getId());
        }

        return "admin/festivaleditor";
    }

    @PostMapping({"/festivaleditor", "/festivaleditor/{id}"})
    public String editFestivalPost(@Valid Festival festival){
        if(festival.getEndDate().before(festival.getStartDate())){
            festival.setEndDate(festival.getStartDate());
        }
        festivalRepository.save(festival);
        return "redirect:/festivallijst";
    }

    @GetMapping("/festivalcreator")
    public String addFestival(Model model) {
        model.addAttribute("festival", new Festival());
        return "admin/festivalcreator";
    }

    @PostMapping("/festivalcreator")
    public String addFestivalPost(Model model, @Valid Festival festival) {
        boolean valid = true;
        if(festival.getFestivalName() == null || festival.getFestivalName().trim() == ""
                || festival.getFestivalImage() == null || festival.getFestivalImage().trim() == ""
                || festival.getMaxCapacity() == null ||festival.getMaxCapacity() <= 0){
            valid = false;
        }

        if(festival.getEndDate().before(festival.getStartDate())){
            festival.setEndDate(festival.getStartDate());
        }

        festival.setPopulation(0);

        if(valid){
            festivalRepository.save(festival);
            return "redirect:/festivallijst";
        }
        model.addAttribute("festival", festival);
        return "admin/festivalcreator";
    }
}
