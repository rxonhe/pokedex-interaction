package com.choinhet.pokedexinteraction.util;

import com.choinhet.pokedexinteraction.model.Pokemon;

public interface PokemonComparator {
    Boolean compare(Pokemon firstPokemon, Pokemon secondPokemon);
}
