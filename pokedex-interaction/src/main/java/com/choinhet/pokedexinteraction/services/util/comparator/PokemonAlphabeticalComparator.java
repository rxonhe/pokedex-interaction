package com.choinhet.pokedexinteraction.services.util.comparator;

import com.choinhet.pokedexinteraction.model.Pokemon;

public class PokemonAlphabeticalComparator implements PokemonComparator {
    @Override
    public Boolean compare(Pokemon firstPokemon, Pokemon secondPokemon) {
        return firstPokemon.getName().compareTo(secondPokemon.getName()) < 0;
    }
}
