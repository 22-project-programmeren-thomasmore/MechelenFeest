package be.thomasmore.screeninfo.controllers;

import be.thomasmore.screeninfo.model.*;
import be.thomasmore.screeninfo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class ShopController {
    @Autowired
    private FestivalRepository festivalRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/ticketlist/{id}")
    public String ticketList(Model model, @PathVariable Integer id) {
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        Festival festival = optionalFestival.get();
        List<Ticket> ticketList = ticketRepository.findByFestival(festival);
        model.addAttribute("tickets", ticketList);
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) shoppingCartRepository.findAll();
        model.addAttribute("cartItems", shoppingCartList);
        return "ticketlist";
    }

    @PostMapping("/addToCart/{id}/{ticketId}")
    public String listProductHandler(@PathVariable Integer id, @PathVariable Integer ticketId, @RequestParam int quantity) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findByProductId(ticketId);
        if (optionalShoppingCart.isPresent()){
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            shoppingCart.setQuantity(quantity);
            if (quantity==0){
                shoppingCartRepository.delete(shoppingCart);
            } else {
                shoppingCartRepository.save(shoppingCart);
            }
        } else {
            if (quantity!=0) {
                ShoppingCart shoppingCart = new ShoppingCart(ticket.getId(), ticket.getName(), quantity, ticket.getPrice());
                shoppingCartRepository.save(shoppingCart);
            }
        }

        return "redirect:/ticketlist/"+id;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            EndUser user = userRepository.findByUsername(currentUserName);
            model.addAttribute("emailAddress", user.getEmailAddress());
        }
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkoutPost(@RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        for (ShoppingCart s : shoppingCartRepository.findAll()) {
            shoppingCartList.add(s);
        }
        Order order;
        String currentUserName;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            EndUser user = userRepository.findByUsername(currentUserName);
            order = new Order(shoppingCartList, user);
        } else {
            order = new Order(shoppingCartList);
        }
        orderRepository.save(order);
        shoppingCartRepository.deleteAll(shoppingCartRepository.findAll());
        return "checkout";
    }


}