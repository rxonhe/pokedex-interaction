package com.choinhet.pokedexinteraction.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokedex {

    private Pokemon[] results;

    public Pokedex() {
    }

    public Pokedex(Pokemon[] results) {
        this.results = results;
    }

    public Pokemon[] getPokemons() {
        return results;
    }

    public void setResults(Pokemon[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}

