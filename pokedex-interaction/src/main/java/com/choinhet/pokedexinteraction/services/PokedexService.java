package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.services.util.enumUtility.SortOptions;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class PokedexService {

    private final Pokedex pokedex;
    private final PokedexSorter pokedexSorter;

    public PokedexService(Pokedex pokedex, PokedexSorter pokedexSorter) {
        this.pokedex = pokedex;
        this.pokedexSorter = pokedexSorter;
    }

    public Pokedex findPokedexByName(String name) {

        if (name == null) {
            return pokedex;
        }

        return new Pokedex(pokedex.getPokemons().stream()
                .filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList()));
    }

    public Pokedex sortPokedex(Pokedex pokedex, SortOptions sortOption) {
        return this.pokedexSorter.sortPokedex(pokedex, sortOption);
    }

}
