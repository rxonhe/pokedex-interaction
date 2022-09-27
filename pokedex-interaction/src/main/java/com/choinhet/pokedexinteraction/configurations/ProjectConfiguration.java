package com.choinhet.pokedexinteraction.configurations;
import com.choinhet.pokedexinteraction.services.PokedexSorter;
import com.choinhet.pokedexinteraction.util.enumUtility.StringToSortOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ProjectConfiguration implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    PokedexSorter getPokedexSorter(){
        return new PokedexSorter();
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSortOptions());
    }
}
