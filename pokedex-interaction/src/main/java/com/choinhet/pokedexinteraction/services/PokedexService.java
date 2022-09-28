package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.model.SortOptions;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class PokedexService {

    private final RequestsService requestsService;
    private final PokedexSorter pokedexSorter;

    private Pokedex pokedex;


    public PokedexService(RequestsService requestsService, PokedexSorter pokedexSorter) {
        this.requestsService = requestsService;
        this.pokedexSorter = pokedexSorter;
    }

    private Pokedex getPokedex() {
        if (pokedex == null) {
            pokedex = requestsService.requestPokedex();
        }
        return pokedex;
    }

    public Pokedex findPokedexByName(@Nullable String name) {

        pokedex = getPokedex();

        if (name == null) {
            return pokedex;
        }

        return new Pokedex(pokedex.getPokemons().stream()
            .filter(pokemon -> pokemon.
                getName()
                .toLowerCase()
                .contains(name.toLowerCase())
            ).collect(Collectors.toList()));
    }

    public Pokedex sortPokedex(Pokedex pokedex, SortOptions sortOption) {
        return this.pokedexSorter.sortPokedex(pokedex, sortOption);
    }

}
