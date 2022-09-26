package com.choinhet.pokedexinteraction.util;

import java.util.HashMap;
import java.util.Map;

public class PokemonComparatorFactory {
    Map<String, PokemonComparator> availableComparators;

    public PokemonComparatorFactory() {
        this.availableComparators = new HashMap<>();
        availableComparators.put("length", new PokemonLengthComparator());
        availableComparators.put("alphabetical", new PokemonAlphabeticalComparator());
    }

    public PokemonComparator getComparator(String sortMethod) {
        return this.availableComparators.getOrDefault(sortMethod, new PokemonAlphabeticalComparator());
    }
}
