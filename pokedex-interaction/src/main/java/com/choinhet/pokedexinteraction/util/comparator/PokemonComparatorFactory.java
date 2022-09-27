package com.choinhet.pokedexinteraction.util.comparator;

import com.choinhet.pokedexinteraction.util.enumUtility.SortOptions;

import java.util.HashMap;
import java.util.Map;

public class PokemonComparatorFactory {
    Map<SortOptions, PokemonComparator> availableComparators;

    public PokemonComparatorFactory() {
        this.availableComparators = new HashMap<>();
        availableComparators.put(SortOptions.LENGTH, new PokemonLengthComparator());
        availableComparators.put(SortOptions.ALPHABETICAL, new PokemonAlphabeticalComparator());
    }

    public PokemonComparator getComparator(SortOptions sortMethod) {
        return this.availableComparators.getOrDefault(sortMethod, new PokemonAlphabeticalComparator());
    }
}
