package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PokedexService {

    private final Pokedex pokedex;

    public PokedexService(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public Pokedex findPokedexByName(String name) {

        if (name == null) {
            return pokedex;
        }

        return new Pokedex(pokedex.getPokemons().stream()
                .filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList()));
    }

    public Pokedex sortPokedex(Pokedex pokedex, String sort) {

        /*
            Time complexity O(n log(n))
            Tree part -> log(n)
            Iteration loop -> n
        */

        List<Pokemon> pokedexResult = pokedex.getPokemons();
        int pokedexSize = pokedexResult.size();

        // a 1 element Pokédex is already sorted
        if (pokedexSize <= 1) {
            return pokedex;
        } else {
            // else split it into 2 portions
            int half = pokedexSize / 2;

            Pokedex leftSidePokedex = new Pokedex(new ArrayList<>(pokedexResult.subList(0, half)));

            Pokedex rightSidePokedex = new Pokedex(new ArrayList<>(pokedexResult.subList(half, pokedexSize)));

            // recursively sort each half and merge them (both will come to length=1 at some point and will be sorted)

            leftSidePokedex = sortPokedex(leftSidePokedex, sort);
            rightSidePokedex = sortPokedex(rightSidePokedex, sort);

            return mergePokedexes(leftSidePokedex, rightSidePokedex, sort);
        }

    }

    private Pokedex mergePokedexes(Pokedex leftSidePokedex, Pokedex rightSidePokedex, String sort) {
        int leftIndex = 0;
        int rightIndex = 0;

        List<Pokemon> leftResult = leftSidePokedex.getPokemons();
        List<Pokemon> rightResult = rightSidePokedex.getPokemons();

        final int leftSize = leftResult.size();
        final int rightSize = rightResult.size();

        List<Pokemon> mergedResult = new ArrayList<>();

        // while still have non merged elements
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (comparePokemons(leftSidePokedex, leftIndex, rightSidePokedex, rightIndex, sort)) {
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

    private boolean comparePokemons(Pokedex leftSidePokedex,
                                    int leftIndex,
                                    Pokedex rightSidePokedex,
                                    int rightIndex,
                                    String sort) {

        Pokemon leftValue = leftSidePokedex.getPokemons().get(leftIndex);
        Pokemon rightValue = rightSidePokedex.getPokemons().get(rightIndex);

        if (sort != null && sort.equals("length")) {
            return leftValue.getName().length() < rightValue.getName().length();
        } else {
            return leftValue.getName().compareTo(rightValue.getName()) < 0;
        }
    }


}
