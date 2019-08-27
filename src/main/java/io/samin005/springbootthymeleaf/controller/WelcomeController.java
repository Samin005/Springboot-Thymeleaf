package io.samin005.springbootthymeleaf.controller;

import io.samin005.springbootthymeleaf.service.PokemonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;
    private PokemonService pokemonService;

    public WelcomeController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public String main(Model model) {
        String connectionTestResponse = pokemonService.checkConnection();
        model.addAttribute("connectionTestResponse", connectionTestResponse);
        model.addAttribute("message", message);
        return "welcome"; //view
    }
}
