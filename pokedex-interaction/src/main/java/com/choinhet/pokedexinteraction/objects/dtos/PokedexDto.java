package com.choinhet.pokedexinteraction.objects.dtos;

import com.choinhet.pokedexinteraction.objects.Pokemon;

import java.util.Arrays;

public class PokedexDto {

    private PokemonDto[] result;

    public PokedexDto() {
    }

    public PokedexDto(Pokemon[] result) {
        this.result = getPokedexFromResult(result);
    }

    private PokemonDto[] getPokedexFromResult(Pokemon[] result){
        return Arrays.stream(result)
                .map(pokemon -> new PokemonDto(pokemon.getName()))
                .toArray(PokemonDto[]::new);
    }

    public PokemonDto[] getResult() {
        return result;
    }

    public void setResult(PokemonDto[] result) {
        this.result = result;
    }

}
