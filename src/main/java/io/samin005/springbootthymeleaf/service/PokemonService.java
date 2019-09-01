package io.samin005.springbootthymeleaf.service;

import io.samin005.springbootthymeleaf.model.Pokemon;
import io.samin005.springbootthymeleaf.model.PokemonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PokemonService {

    private Logger LOGGER = Logger.getLogger(PokemonService.class.getName());
    private String url = "http://localhost:5000/pokemons";
    private RestTemplate restTemplate = new RestTemplate();
    private PokemonResponse pokemonResponse = new PokemonResponse();
    private String response;
    private String status;
    private HttpStatus httpStatus;

    public String checkConnection(){
        String response;
        try{
            HttpEntity<String> requestEntity = new HttpEntity<>("");
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:5000/", HttpMethod.GET, requestEntity, String.class);
            httpStatus = responseEntity.getStatusCode();
            LOGGER.info("HTTP STATUS: "+httpStatus.toString());
            response = responseEntity.getBody();
            LOGGER.info("RESPONSE: "+ response);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString());
            response = e.getMessage();
        }
        return response;
    }

    public List getAllPokemons(){
        List allPokemons = new ArrayList();
        try{
            HttpEntity<List> requestEntity = new HttpEntity<>(allPokemons);
            ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class);
            httpStatus = responseEntity.getStatusCode();
            LOGGER.info("HTTP STATUS: "+httpStatus.toString());
            allPokemons = responseEntity.getBody();
            LOGGER.info("RESPONSE: "+ (allPokemons != null ? allPokemons.toString() : null));
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return allPokemons;
    }

    public PokemonResponse postPokemon(Pokemon pokemon){
        try{
            HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, pokemonHttpEntity, String.class);
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
        if(response.equals("Pokemon '" + pokemon.getName() + "' added successfully!")){
            status = "success";
        } else if(response.equals("Pokemon '" + pokemon.getName() + "' with dex no " + pokemon.getDexNo() + " already existed and it was updated!")){
            status = "warning";
        } else {
            status = "error";
        }
        pokemonResponse.setStatus(status);
        pokemonResponse.setHttpStatus(httpStatus);
        return pokemonResponse;
    }

    public PokemonResponse updatePokemon(Pokemon pokemon){
        try{
            HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, pokemonHttpEntity, String.class);
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
        if(response.equals(pokemon.getName() + " with dex no " + pokemon.getDexNo() + " has been updated!")){
            status = "success";
        } else if(response.equals("Pokedex no " + pokemon.getDexNo() + " does not exist. Try a POST request.")){
            status = "warning";
        } else {
            status = "error";
        }
        pokemonResponse.setStatus(status);
        pokemonResponse.setHttpStatus(httpStatus);
        return pokemonResponse;
    }

    public PokemonResponse deletePokemon(Pokemon pokemon){
        try{
            HttpEntity<Pokemon> pokemonHttpEntity = new HttpEntity<>(pokemon);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url+"/delete/" + pokemon.getDexNo(), HttpMethod.DELETE, pokemonHttpEntity, String.class);
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
        if(response.equals("Pokemon with dex no " + pokemon.getDexNo() + " has been deleted!")){
            status = "success";
        } else if(response.equals("Pokedex no " + pokemon.getDexNo() + " does not exist.")){
            status = "warning";
        } else {
            status = "error";
        }
        pokemonResponse.setStatus(status);
        pokemonResponse.setHttpStatus(httpStatus);
        return pokemonResponse;
    }
}
