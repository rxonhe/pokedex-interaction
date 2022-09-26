package com.choinhet.pokedexinteraction.util;

import com.choinhet.pokedexinteraction.model.Pokemon;

public class PokemonLengthComparator implements PokemonComparator {
    @Override
    public Boolean compare(Pokemon firstPokemon, Pokemon secondPokemon) {
        return firstPokemon.getName().length() < secondPokemon.getName().length();
    }
}
