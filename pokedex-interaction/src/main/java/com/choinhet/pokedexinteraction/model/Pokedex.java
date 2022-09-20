package com.choinhet.pokedexinteraction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokedex {

    private List<Pokemon> results;

    public Pokedex() {
    }

    public Pokedex(List<Pokemon> results) {
        this.results = results;
    }

    public List<Pokemon> getPokemons() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "results=" + results.toString() +
                '}';
    }
}

