package io.samin005.springbootthymeleaf.service;

import io.samin005.springbootthymeleaf.model.Pokemon;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    public String postPokemon(Pokemon pokemon){
        String status;
        HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange("http://localhost:8080/pokemons", HttpMethod.POST, pokemonHttpEntity, String.class);
        status = responseEntity.getBody();
        System.out.println(status);
        return status;
    }
}
