package com.choinhet.pokedexinteraction.configurations;
import com.choinhet.pokedexinteraction.services.PokedexSorter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ProjectConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    PokedexSorter getPokedexSorter(){
        return new PokedexSorter();
    }
}
