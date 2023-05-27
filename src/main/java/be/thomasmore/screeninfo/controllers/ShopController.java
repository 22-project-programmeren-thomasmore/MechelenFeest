package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.ShoppingCart;
import be.thomasmore.screeninfo.model.Ticket;
import be.thomasmore.screeninfo.repositories.FestivalRepository;
import be.thomasmore.screeninfo.repositories.ShoppingCartRepository;
import be.thomasmore.screeninfo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {
    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @GetMapping("/ticketlist/{id}")
    public String ticketList(Model model, @PathVariable Integer id) {
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        Festival festival = optionalFestival.get();
        List<Ticket> ticketList = ticketRepository.findByFestival(festival);
        model.addAttribute("tickets", ticketList);
        return "ticketlist";
    }

    @PostMapping("/addToCart/{id}")
    public String listProductHandler(@PathVariable Integer id, @RequestParam int quantity) {
        Ticket ticket = ticketRepository.findById(id).get();
        ShoppingCart shoppingCart = new ShoppingCart(ticket.getId(),ticket.getName(),quantity,ticket.getPrice());
        shoppingCartRepository.save(shoppingCart);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart() {
        return "shoppingcart";
    }

}
