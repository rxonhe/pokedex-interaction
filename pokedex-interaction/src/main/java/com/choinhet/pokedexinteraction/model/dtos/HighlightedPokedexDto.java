package com.choinhet.pokedexinteraction.model.dtos;

import com.choinhet.pokedexinteraction.model.Pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class HighlightedPokedexDto extends PokedexDto {

    private List<PokemonDto> result;

    public HighlightedPokedexDto() {
    }

    public HighlightedPokedexDto(List<Pokemon> result, String toHighlight) {
        this.result = this.getPokedexFromResult(result, toHighlight);
    }


    private List<PokemonDto> getPokedexFromResult(List<Pokemon> result, String toHighlight) {
        return this.result = result.stream()
                .map(pokemon -> new HighlightedPokemonDto(pokemon.getName(), toHighlight))
                .collect(Collectors.toList());

    }

    public List<PokemonDto> getResult() {
        return result;
    }

    public void setResult(List<PokemonDto> result) {
        this.result = result;
    }

}
