package com.choinhet.pokedexinteraction.model.dtos;

import com.choinhet.pokedexinteraction.model.Pokemon;

import java.util.List;

public class HighlightedPokedexDto extends PokedexDto {

    private List<PokemonDto> result;

    public HighlightedPokedexDto(List<PokemonDto> result) {
        this.result = result;
    }

    public static HighlightedPokedexDto fromPokemonList(List<Pokemon> result, String toHighlight) {
        return new HighlightedPokedexDto(
            result
                .stream()
                .map(pokemon -> HighlightedPokemonDto.fromHighlightInfo(pokemon.getName(), toHighlight))
                .toList()
        );

    }

    public List<PokemonDto> getResult() {
        return result;
    }

    public void setResult(List<PokemonDto> result) {
        this.result = result;
    }

}
