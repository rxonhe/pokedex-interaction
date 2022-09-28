package com.choinhet.pokedexinteraction;

import com.choinhet.pokedexinteraction.model.Pokedex;
import com.choinhet.pokedexinteraction.services.PokedexService;
import com.choinhet.pokedexinteraction.model.SortOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PokedexServiceIntegrationTest {

    @Autowired
    private PokedexService pokedexService;

    @Test
    public void findPokedexByNameTest_thenOK() {
        Pokedex pokedex = pokedexService.findPokedexByName("pikachu");
        assertNotNull(pokedex);
    }

    @Test
    public void findAndSortPokedexTest_thenOK() {
        Pokedex pokedex = pokedexService.findPokedexByName("pi");
        pokedex = pokedexService.sortPokedex(pokedex, SortOptions.LENGTH);

        assertThat(pokedex.getPokemons().get(0).getName(), equalTo("tepig"));
    }

    @Test
    public void noFiltersPokedexTest_thenOK() {
        Pokedex pokedex = pokedexService.findPokedexByName(null);
        assertNotNull(pokedex);
    }

}
