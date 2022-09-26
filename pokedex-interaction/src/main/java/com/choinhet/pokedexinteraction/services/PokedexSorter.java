package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.model.Pokemon;
import com.choinhet.pokedexinteraction.util.PokemonComparator;
import com.choinhet.pokedexinteraction.util.PokemonComparatorFactory;

import java.util.ArrayList;
import java.util.List;

public class PokedexSorter {
    public Pokedex sortPokedex(Pokedex pokedex, String sortMethod) {

        /*
            Time complexity O(n log(n))
            Tree part -> log(n)
            Iteration loop -> n
        */

        List<Pokemon> pokedexResult = pokedex.getPokemons();
        PokemonComparator pokemonComparator = new PokemonComparatorFactory().getComparator(sortMethod);

        int pokedexSize = pokedexResult.size();

        // a 1 element Pokédex is already sorted
        if (pokedexSize <= 1) {
            return pokedex;
        } else {
            // else split it into 2 portions
            int half = pokedexSize / 2;

            Pokedex leftSidePokedex = new Pokedex(new ArrayList<>(pokedexResult.subList(0, half)));
            Pokedex rightSidePokedex = new Pokedex(new ArrayList<>(pokedexResult.subList(half, pokedexSize)));

            // recursively sortMethod each half and merge them (both will come to length=1 at some point and will be sorted)

            leftSidePokedex = sortPokedex(leftSidePokedex, sortMethod);
            rightSidePokedex = sortPokedex(rightSidePokedex, sortMethod);

            return this.mergePokedexes(leftSidePokedex, rightSidePokedex, pokemonComparator);
        }

    }

    private Pokedex mergePokedexes(Pokedex leftSidePokedex, Pokedex rightSidePokedex, PokemonComparator pokemonComparator) {
        int leftIndex = 0;
        int rightIndex = 0;

        List<Pokemon> leftResult = leftSidePokedex.getPokemons();
        List<Pokemon> rightResult = rightSidePokedex.getPokemons();

        final int leftSize = leftResult.size();
        final int rightSize = rightResult.size();

        List<Pokemon> mergedResult = new ArrayList<>();

        // while still have non merged elements
        while (leftIndex < leftSize && rightIndex < rightSize) {

            Pokemon leftPokemon = leftSidePokedex.getPokemons().get(leftIndex);
            Pokemon rightPokemon = rightSidePokedex.getPokemons().get(rightIndex);

            if (pokemonComparator.compare(leftPokemon, rightPokemon)) {
                // left is supposed to be first
                mergedResult.add(leftResult.get(leftIndex));
                leftIndex++;
            } else {
                // right size is supposed to be first
                mergedResult.add(rightResult.get(rightIndex));
                rightIndex++;
            }
        }
        // if there are non merged elements on left list, merge them
        while (leftIndex < leftSize) {
            mergedResult.add(leftResult.get(leftIndex));
            leftIndex++;
        }
        // if there are non merged elements on right list, merge them
        while (rightIndex < rightSize) {
            mergedResult.add(rightResult.get(rightIndex));
            rightIndex++;
        }

        return new Pokedex(mergedResult);
    }
}
