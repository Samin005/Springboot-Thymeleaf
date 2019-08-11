package io.samin005.springbootthymeleaf.service;

import io.samin005.springbootthymeleaf.model.Pokemon;
import io.samin005.springbootthymeleaf.model.PokemonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PokemonService {

    private Logger LOGGER = Logger.getLogger(PokemonService.class.getName());

    public PokemonResponse postPokemon(Pokemon pokemon){
        PokemonResponse pokemonResponse = new PokemonResponse();
        String response;
        HttpStatus httpStatus;
        try{
            HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
            ResponseEntity<String> responseEntity = new RestTemplate().exchange("http://localhost:8080/pokemons", HttpMethod.POST, pokemonHttpEntity, String.class);
            httpStatus = responseEntity.getStatusCode();
            LOGGER.info("HTTP STATUS: "+httpStatus.toString());
            response = responseEntity.getBody();
            LOGGER.info("RESPONSE: "+response);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString());
            response = e.getMessage();
            httpStatus = HttpStatus.GATEWAY_TIMEOUT;
        }
        pokemonResponse.setResponse(response);
        pokemonResponse.setHttpStatus(httpStatus);
        return pokemonResponse;
    }

    public List<Pokemon> getAllPokemons(){
        List<Pokemon> allPokemons = null;
        try{
            HttpEntity<List> requestEntity = new HttpEntity<>(allPokemons);
            ResponseEntity<List> responseEntity = new RestTemplate().exchange("http://localhost:8080/pokemons", HttpMethod.GET, requestEntity, List.class);
            allPokemons = responseEntity.getBody();
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return allPokemons;
    }
}
