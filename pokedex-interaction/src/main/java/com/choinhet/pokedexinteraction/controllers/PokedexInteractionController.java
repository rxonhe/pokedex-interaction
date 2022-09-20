package com.choinhet.pokedexinteraction.controllers;

import com.choinhet.pokedexinteraction.model.dtos.HighlightedPokedexDto;
import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.model.dtos.PokedexDto;
import com.choinhet.pokedexinteraction.services.PokedexService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PokedexInteractionController {
    private final PokedexService pokedexService;

    PokedexInteractionController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping("/pokemons")
    public PokedexDto findPokemon(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sort) {

        Pokedex foundPokedex = pokedexService.findPokedexByName(name);
        if (foundPokedex.getPokemons().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return convertToDto(pokedexService.sortPokedex(foundPokedex, sort));
    }

    @GetMapping("/pokemons/highlight")
    public PokedexDto findPokemonWithHighlight(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sort) {

        Pokedex foundPokedex = pokedexService.findPokedexByName(name);
        if (foundPokedex.getPokemons().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return convertToHighlightedDto(pokedexService.sortPokedex(foundPokedex, sort), name);
    }


    private PokedexDto convertToHighlightedDto(Pokedex pokedex, String name) {
        return name != null
                ? new HighlightedPokedexDto(pokedex.getPokemons(), name)
                : new PokedexDto(pokedex.getPokemons());
    }

    private PokedexDto convertToDto(Pokedex pokedex) {
        return new PokedexDto(pokedex.getPokemons());
    }
}
