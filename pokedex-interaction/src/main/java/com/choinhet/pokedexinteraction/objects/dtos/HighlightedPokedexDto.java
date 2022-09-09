package com.choinhet.pokedexinteraction.objects.dtos;

import com.choinhet.pokedexinteraction.objects.Pokemon;

import java.util.Arrays;

public class HighlightedPokedexDto extends PokedexDto {

    private PokemonDto[] result;

    public HighlightedPokedexDto() {
    }

    public HighlightedPokedexDto(Pokemon[] result, String toHighlight) {
        this.result = this.getPokedexFromResult(result, toHighlight);
    }


    private PokemonDto[] getPokedexFromResult(Pokemon[] result, String toHighlight) {
        return this.result = Arrays.stream(result)
                .map(pokemon -> new HighlightedPokemonDto(pokemon.getName(), toHighlight))
                .toArray(PokemonDto[]::new);

    }

    public PokemonDto[] getResult() {
        return result;
    }

    public void setResult(PokemonDto[] result) {
        this.result = result;
    }

}
