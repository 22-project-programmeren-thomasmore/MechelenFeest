package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.Ticket;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import be.thomasmore.screeninfo.repositories.TicketRepository;
import be.thomasmore.screeninfo.repositories.UserRepository;
import be.thomasmore.screeninfo.services.GoogleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FestivalRepository festivalRepository;
    @Autowired
    private GoogleService googleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping({"/festivaleditor", "/festivaleditor/{id}"})
    public String editFestival(Model model, @PathVariable(required = false) Integer id) {
        Optional<Festival> optionalFestival = festivalRepository.findById(id);;
        Festival festival;
        if (optionalFestival.isPresent()) {
            festival = optionalFestival.get();
        } else {
            optionalFestival = festivalRepository.findFirstByOrderByIdAsc();
            festival = optionalFestival.get();
        }
        model.addAttribute("festival", festival);
        Optional<Festival> optionalPrevFestival = festivalRepository.findFirstByIdLessThanOrderByIdDesc(festival.getId());
        Optional<Festival> optionalNextFestival = festivalRepository.findFirstByIdGreaterThanOrderById(festival.getId());
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
    public String editFestivalPost(@Valid Festival festival, @RequestParam(required = false) MultipartFile img){
        if(festival.getEndDate().before(festival.getStartDate())){
            festival.setEndDate(festival.getStartDate());
        }
        if(img != null){
            String imgLink = uploadFile(img);
            if(!imgLink.equals("")){
                festival.setFestivalImage(imgLink);
            }
            else{
                festival = resetFestivalImage(festival);
            }
        }
        else{
            festival = resetFestivalImage(festival);
        }

        festivalRepository.save(festival);
        return "redirect:/festivallijst";
    }
    private Festival resetFestivalImage(Festival festival){ // het verwijdert de image link voor 1 of andere rede
        Optional<Festival> optionalFestival = festivalRepository.findById(festival.id);
        if(optionalFestival.isPresent()){
            festival.setFestivalImage(optionalFestival.get().getFestivalImage());
        }
        return  festival;
    }

    @PostMapping("/addTicket/{id}")
    public String addTicketPost(@PathVariable Integer id, @RequestParam String ticketName, @RequestParam Double price ){
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        if (optionalFestival.isPresent()){
            Festival festival = optionalFestival.get();
            Ticket ticket = new Ticket(ticketName, price, festival);
            ticketRepository.save(ticket);
        }
        return "redirect:/admin/festivaleditor/"+id;
    }

    @GetMapping("/festivalcreator")
    public String addFestival(Model model) {
        model.addAttribute("festival", new Festival());
        model.addAttribute("img", new File("",""));
        return "admin/festivalcreator";
    }

    @PostMapping("/festivalcreator")
    public String addFestivalPost(Model model, @Valid Festival festival, @RequestParam(required = false) MultipartFile img) {
        boolean valid = true;
        String errorText = "";
        if(festival.getFestivalName() == null || festival.getFestivalName().trim().equals("")
                || festival.getMaxCapacity() == null || festival.getMaxCapacity() <= 0
                || img == null){
            valid = false;
            errorText += "gelieven alle velden in te vullen";
        }
        else {
            if(festival.getEndDate().before(festival.getStartDate())){
                festival.setEndDate(festival.getStartDate());
            }

            String imageLink = uploadFile(img);
            if(imageLink.equals("")){
                valid = false;
                errorText += "photo Uploaden is gefaald, probeer later opnieuw";
            }
            else{
                festival.setFestivalImage(imageLink);
            }

            festival.setPopulation(0);
        }


        if(valid){
            festivalRepository.save(festival);
            return "redirect:/festivallijst";
        }
        model.addAttribute("festival", festival);
        model.addAttribute("img",img);
        model.addAttribute("foutief", true);
        model.addAttribute("foutiefText", errorText);
        return "admin/festivalcreator";
    }

    @GetMapping("/admincontact")
    public String adminContact() {
        return "admin/admincontact";
    }
    @PostMapping("/admincontact")
    public String adminContactPost(@RequestParam String emailAddress){
        EndUser user = userRepository.findById(1).get();
        user.setEmailAddress(emailAddress);
        userRepository.save(user);
        return "admin/admincontact";
    }


    private String uploadFile(MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            fileName = fileName.concat(UUID.randomUUID().toString()).concat(fileName.substring(fileName.lastIndexOf(".")));
            File newFile = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                fos.write(file.getBytes());
            }
            String imgLink = googleService.toFirebase(newFile);
            newFile.delete();
            return imgLink;
        } catch (Exception e) {
            System.out.println("ERROR: Niet kunnen uploaden : " + e.getMessage());
            return "";
        }
    }


    @GetMapping("/test")
    public String testing(Model model) {
        System.out.println("hello?");
        File uploadFile= new File("./Dockerfile");
        try {
            System.out.println(googleService.toFirebase(uploadFile));
            System.out.println("Gelukt!");
        } catch (Exception e) {
            System.out.println("Niet gelukt : " + e.getMessage());
        }
        return "map";
    }

}
