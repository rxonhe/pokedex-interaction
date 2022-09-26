package com.choinhet.pokedexinteraction.services.util.comparator;

import com.choinhet.pokedexinteraction.model.Pokemon;

public interface PokemonComparator {
    Boolean compare(Pokemon firstPokemon, Pokemon secondPokemon);
}
