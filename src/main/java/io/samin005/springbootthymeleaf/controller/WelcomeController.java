package io.samin005.springbootthymeleaf.controller;

import io.samin005.springbootthymeleaf.model.Pokemon;
import io.samin005.springbootthymeleaf.service.PokemonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "k");
    private PokemonService pokemonService;

    public WelcomeController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

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
    public String addNewPokemon(@Valid Pokemon pokemon, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "addPokemon";
        } else {
            String response = pokemonService.postPokemon(pokemon);
            model.addAttribute("pokemon", pokemon);
            model.addAttribute("response", response);
            return "addPokemonResult";
        }
    }

    @GetMapping("/pokemons/update")
    public String updatePokemon(){
        return "updatePokemon";
    }

    @GetMapping("/pokemons/delete")
    public String deletePokemon(){
        return "deletePokemon";
    }
}
