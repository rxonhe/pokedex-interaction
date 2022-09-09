package com.choinhet.pokedexinteraction;

import com.choinhet.pokedexinteraction.objects.Pokedex;
import com.choinhet.pokedexinteraction.services.IPokedexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PokedexServiceIntegrationTest {

    @Autowired
    private IPokedexService pokedexService;

    @Test
    public void findPokedexByNameTest_thenOK() {
        Pokedex pokedex = pokedexService.findPokedexByName("pikachu").orElse(null);
        assertNotNull(pokedex);
    }

    @Test
    public void findAndSortPokedexTest_thenOK(){
        Pokedex pokedex = pokedexService.findPokedexByName("pi").orElse(null);
        pokedex = pokedexService.sortPokedex(pokedex, "length");

        assertThat(pokedex.getPokemons()[0].getName(), equalTo("tepig"));
    }

    @Test
    public void noFiltersPokedexTest_thenOK(){
        Pokedex pokedex = pokedexService.findPokedexByName(null).orElse(null);
        assertNotNull(pokedex);
    }

}
