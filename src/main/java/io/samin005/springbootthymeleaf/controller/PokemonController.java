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
        String connectionTestResponse = pokemonService.checkConnection();
        model.addAttribute("connectionTestResponse", connectionTestResponse);
        List pokemons = pokemonService.getAllPokemons();
        model.addAttribute("pokemons", pokemons);
        return "pokemons"; //view
    }

    @GetMapping("/pokemons/add")
    public String addPokemon(Model model) {
        String connectionTestResponse = pokemonService.checkConnection();
        model.addAttribute("connectionTestResponse", connectionTestResponse);
        Pokemon pokemon = new Pokemon();
        model.addAttribute("pokemon", pokemon);
        List pokemonsList = pokemonService.getAllPokemons();
        model.addAttribute("pokemonsList", pokemonsList);
        return "addPokemon";
    }

    @PostMapping("/pokemons/add")
    public String addNewPokemon(@Valid Pokemon pokemon, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            String connectionTestResponse = pokemonService.checkConnection();
            model.addAttribute("connectionTestResponse", connectionTestResponse);
            List pokemonsList = pokemonService.getAllPokemons();
            model.addAttribute("pokemonsList", pokemonsList);
            return "addPokemon";
        } else {
            PokemonResponse pokemonResponse = pokemonService.postPokemon(pokemon);
            mapModelResponse(pokemon, model, pokemonResponse);
            return "addPokemonResult";
        }
    }

    @GetMapping("/pokemons/update")
    public String updatePokemon(Model model){
        String connectionTestResponse = pokemonService.checkConnection();
        model.addAttribute("connectionTestResponse", connectionTestResponse);
        Pokemon pokemon = new Pokemon();
        List pokemonsList = pokemonService.getAllPokemons();
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("pokemonsList", pokemonsList);
        return "updatePokemon";
    }

    @PostMapping("/pokemons/update")
    public String updatePokemon(@Valid Pokemon pokemon, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            String connectionTestResponse = pokemonService.checkConnection();
            model.addAttribute("connectionTestResponse", connectionTestResponse);
            List pokemonsList = pokemonService.getAllPokemons();
            model.addAttribute("pokemonsList", pokemonsList);
            return "updatePokemon";
        } else {
            PokemonResponse pokemonResponse = pokemonService.updatePokemon(pokemon);
            mapModelResponse(pokemon, model, pokemonResponse);
            return "updatePokemonResult";
        }
    }

    @GetMapping("/pokemons/delete")
    public String deletePokemon(Model model){
        String connectionTestResponse = pokemonService.checkConnection();
        model.addAttribute("connectionTestResponse", connectionTestResponse);
        Pokemon pokemon = new Pokemon();
        List pokemonsList = pokemonService.getAllPokemons();
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("pokemonsList", pokemonsList);
        return "deletePokemon";
    }

    @PostMapping("/pokemons/delete")
    public String deletePokemon(@Valid Pokemon pokemon, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            String connectionTestResponse = pokemonService.checkConnection();
            model.addAttribute("connectionTestResponse", connectionTestResponse);
            List pokemonsList = pokemonService.getAllPokemons();
            model.addAttribute("pokemonsList", pokemonsList);
            return "deletePokemon";
        } else {
            PokemonResponse pokemonResponse = pokemonService.deletePokemon(pokemon);
            mapModelResponse(pokemon, model, pokemonResponse);
            return "deletePokemonResult";
        }
    }

    private void mapModelResponse(@Valid Pokemon pokemon, Model model, PokemonResponse pokemonResponse) {
        String response = pokemonResponse.getResponse();
        String status = pokemonResponse.getStatus();
        String httpStatus = pokemonResponse.getHttpStatus().toString();
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("response", response);
        model.addAttribute("status", status);
        model.addAttribute("httpStatus", httpStatus);
    }
}
