package io.samin005.springbootthymeleaf.model;

import org.springframework.http.HttpStatus;

public class PokemonResponse {
    private String response;
    private HttpStatus httpStatus;

    public PokemonResponse() {
    }

    public PokemonResponse(String response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
