package com.choinhet.pokedexinteraction.services;

import com.choinhet.pokedexinteraction.objects.Pokedex;

import java.util.Optional;

public interface IPokedexService {
    Optional<Pokedex> findPokedexByName(String name);
    Pokedex sortPokedex(Pokedex pokedex, String sort);
}
