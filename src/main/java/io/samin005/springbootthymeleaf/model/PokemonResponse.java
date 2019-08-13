package io.samin005.springbootthymeleaf.model;

import org.springframework.http.HttpStatus;

public class PokemonResponse {
    private String response;
    private String status;
    private HttpStatus httpStatus;

    public PokemonResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
