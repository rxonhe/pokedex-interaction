package com.choinhet.pokedexinteraction.services.impl;

import com.choinhet.pokedexinteraction.objects.Pokedex;
import com.choinhet.pokedexinteraction.objects.Pokemon;
import com.choinhet.pokedexinteraction.services.IPokedexService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
public class PokedexServiceImpl implements IPokedexService {

    private final Pokedex pokedex;

    public PokedexServiceImpl(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public Optional<Pokedex> findPokedexByName(String name) {

        if (name == null) {
            return Optional.of(pokedex);
        }

        Pokedex foundPokedex = new Pokedex(
                Arrays.stream(pokedex.getPokemons())
                        .filter(pokemon -> pokemon.getName().contains(name))
                        .toArray(Pokemon[]::new));

        return Optional.of(foundPokedex);
    }

    public Pokedex sortPokedex(Pokedex pokedex, String sort) {

        /*
            Time complexity O(n log(n))
            Tree part -> log(n)
            Iteration loop -> n
        */

        // a 1 element Pokédex is already sorted
        int pokedexSize = pokedex.getPokemons().length;
        if (pokedexSize <= 1) {
            return pokedex;
        } else {
            // else split it into 2 portions
            int half = pokedexSize / 2;

            Pokedex leftSidePokedex = new Pokedex(
                    Arrays.asList(pokedex.getPokemons()).subList(0, half)
                            .toArray(Pokemon[]::new));

            Pokedex rightSidePokedex = new Pokedex(
                    Arrays.asList(pokedex.getPokemons()).subList(half, pokedexSize)
                            .toArray(Pokemon[]::new));

            // recursively sort each half and merge them (both will come to length=1 at some point and will be sorted)

            leftSidePokedex = sortPokedex(leftSidePokedex, sort);
            rightSidePokedex = sortPokedex(rightSidePokedex, sort);

            return mergePokedexes(leftSidePokedex, rightSidePokedex, sort);
        }

    }

    private Pokedex mergePokedexes(Pokedex leftSidePokedex, Pokedex rightSidePokedex, String sort) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        final int leftSize = leftSidePokedex.getPokemons().length;
        final int rightSize = rightSidePokedex.getPokemons().length;
        Pokemon[] mergedResult = new Pokemon[leftSize + rightSize];

        // while still have non merged elements
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (comparePokemons(leftSidePokedex, leftIndex, rightSidePokedex, rightIndex, sort)) {
                // left is supposed to be first
                mergedResult[mergedIndex] = leftSidePokedex.getPokemons()[leftIndex];
                leftIndex++;
            } else {
                // right size is supposed to be first
                mergedResult[mergedIndex] = rightSidePokedex.getPokemons()[rightIndex];
                rightIndex++;
            }
            // one item was merged
            mergedIndex++;
        }
        // if there are non merged elements on left list, merge them
        while (leftIndex < leftSize) {
            mergedResult[mergedIndex] = leftSidePokedex.getPokemons()[leftIndex];
            leftIndex++;
            mergedIndex++;
        }
        // if there are non merged elements on right list, merge them
        while (rightIndex < rightSize) {
            mergedResult[mergedIndex] = rightSidePokedex.getPokemons()[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        return new Pokedex(mergedResult);
    }

    private boolean comparePokemons(Pokedex leftSidePokedex,
                                    int leftIndex,
                                    Pokedex rightSidePokedex,
                                    int rightIndex,
                                    String sort) {

        Pokemon leftValue = leftSidePokedex.getPokemons()[leftIndex];
        Pokemon rightValue = rightSidePokedex.getPokemons()[rightIndex];

        if (sort != null && sort.equals("length")) {
            return leftValue.getName().length() < rightValue.getName().length();
        } else {
            return leftValue.getName().compareTo(rightValue.getName()) < 0;
        }
    }


}
