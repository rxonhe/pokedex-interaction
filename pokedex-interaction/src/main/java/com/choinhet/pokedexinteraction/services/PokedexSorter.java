package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.model.Pokemon;
import com.choinhet.pokedexinteraction.util.comparator.PokemonComparator;
import com.choinhet.pokedexinteraction.model.SortOptions;

import java.util.ArrayList;
import java.util.List;

public class PokedexSorter {
    public Pokedex sortPokedex(Pokedex pokedex, SortOptions sortOption) {

        /*
            Time complexity O(n log(n))
            Tree part -> log(n)
            Iteration loop -> n
        */

        List<Pokemon> pokedexResult = pokedex.getPokemons();
        PokemonComparator pokemonComparator = sortOption.getPokemonComparator();

        int pokedexSize = pokedexResult.size();

        // a 1 element Pokédex is already sorted
        if (pokedexSize <= 1) {
            return pokedex;
        } else {
            // else split it into 2 portions
            int half = pokedexSize / 2;

            Pokedex leftSidePokedex = new Pokedex(pokedexResult.subList(0, half));
            Pokedex rightSidePokedex = new Pokedex(pokedexResult.subList(half, pokedexSize));

            // recursively sortOption each half and merge them (both will come to length=1 at some point and will be sorted)

            leftSidePokedex = sortPokedex(leftSidePokedex, sortOption);
            rightSidePokedex = sortPokedex(rightSidePokedex, sortOption);

            return this.mergePokedexes(leftSidePokedex, rightSidePokedex, pokemonComparator);
        }

    }

    private Pokedex mergePokedexes(Pokedex leftSidePokedex, Pokedex rightSidePokedex, PokemonComparator pokemonComparator) {

        int leftIndex = 0;
        int rightIndex = 0;

        final int leftSize = leftSidePokedex.getPokemons().size();
        final int rightSize = rightSidePokedex.getPokemons().size();

        List<Pokemon> mergedResult = new ArrayList<>();

        // while still have non merged elements
        while (leftIndex < leftSize && rightIndex < rightSize) {

            Pokemon leftPokemon = leftSidePokedex.getPokemons().get(leftIndex);
            Pokemon rightPokemon = rightSidePokedex.getPokemons().get(rightIndex);

            if (pokemonComparator.compare(leftPokemon, rightPokemon)) {
                // left is supposed to be first
                mergedResult.add(leftPokemon);
                leftIndex++;
            } else {
                // right size is supposed to be first
                mergedResult.add(rightPokemon);
                rightIndex++;
            }
        }
        // if there are non merged elements on left list, merge them
        while (leftIndex < leftSize) {
            mergedResult.add(leftSidePokedex.getPokemons().get(leftIndex));
            leftIndex++;
        }
        // if there are non merged elements on right list, merge them
        while (rightIndex < rightSize) {
            mergedResult.add(rightSidePokedex.getPokemons().get(rightIndex));
            rightIndex++;
        }

        return new Pokedex(mergedResult);
    }
}
