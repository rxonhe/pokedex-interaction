package com.choinhet.pokedexinteraction.objects.dtos;

public class PokemonDto {

    private String name;

    public PokemonDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
