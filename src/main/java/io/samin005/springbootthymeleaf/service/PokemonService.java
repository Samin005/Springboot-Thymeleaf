package io.samin005.springbootthymeleaf.service;

import io.samin005.springbootthymeleaf.model.Pokemon;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class PokemonService {

    private Logger LOGGER = Logger.getLogger(PokemonService.class.getName());

    public String postPokemon(Pokemon pokemon){
        HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange("http://localhost:8080/pokemons", HttpMethod.POST, pokemonHttpEntity, String.class);
        String response = responseEntity.getBody();
        LOGGER.info(response);
        return response;
    }
}
