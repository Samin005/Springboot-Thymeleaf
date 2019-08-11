package io.samin005.springbootthymeleaf.controller;

import io.samin005.springbootthymeleaf.model.Pokemon;
import io.samin005.springbootthymeleaf.model.PokemonResponse;
import io.samin005.springbootthymeleaf.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class PokemonController {
    private PokemonService pokemonService;
//    private List<String> pokemonsString = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "k");

    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public String getPokemons(Model model) {
        List<Pokemon> pokemons = pokemonService.getAllPokemons();
        model.addAttribute("pokemons", pokemons);
        return "pokemons"; //view
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
            PokemonResponse pokemonResponse = pokemonService.postPokemon(pokemon);
            String response = pokemonResponse.getResponse();
            String status = pokemonResponse.getHttpStatus().toString();
            model.addAttribute("pokemon", pokemon);
            model.addAttribute("response", response);
            model.addAttribute("status", status);
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
