package io.samin005.springbootthymeleaf.controller;

import io.samin005.springbootthymeleaf.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        return "welcome"; //view
    }

    @GetMapping("/pokemons/add")
    public String addPokemon(Model model) {
        model.addAttribute("pokemon", new Pokemon());
        return "addPokemon";
    }
    @PostMapping("/pokemons/add")
    public String addNewPokemon(@ModelAttribute Pokemon pokemon, Model model) {
        System.out.println(pokemon.getName());
        String status = "Added successfully!";
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("status", status);
        return "addPokemonResult";
    }

}
