package com.choinhet.pokedexinteraction.model;
import com.choinhet.pokedexinteraction.util.comparator.PokemonAlphabeticalComparator;
import com.choinhet.pokedexinteraction.util.comparator.PokemonComparator;
import com.choinhet.pokedexinteraction.util.comparator.PokemonLengthComparator;

public enum SortOptions {
    LENGTH(new PokemonLengthComparator()),
    ALPHABETICAL(new PokemonAlphabeticalComparator());

    final PokemonComparator pokemonComparator;

    SortOptions(PokemonComparator pokemonComparator) {
        this.pokemonComparator = pokemonComparator;
    }

    public PokemonComparator getPokemonComparator() {
        return pokemonComparator;
    }
}
