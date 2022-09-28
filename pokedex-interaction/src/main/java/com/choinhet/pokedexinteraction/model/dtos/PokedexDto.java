package com.choinhet.pokedexinteraction.model.dtos;

import com.choinhet.pokedexinteraction.model.Pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class PokedexDto {

    private List<PokemonDto> result;

    public PokedexDto() {
    }

    public PokedexDto(List<Pokemon> result) {
        this.result = getPokedexFromResult(result);
    }

    private List<PokemonDto> getPokedexFromResult(List<Pokemon> result) {
        return result.stream()
                .map(pokemon -> new PokemonDto(pokemon.getName()))
                .toList();
    }

    public List<PokemonDto> getResult() {
        return result;
    }

    public void setResult(List<PokemonDto> result) {
        this.result = result;
    }

}
