package com.choinhet.pokedexinteraction.util.enumUtility;
import org.springframework.core.convert.converter.Converter;

public class StringToSortOptions implements Converter<String, SortOptions> {
    @Override
    public SortOptions convert(String source) {
        try {
            return SortOptions.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return SortOptions.ALPHABETICAL;
        }
    }
}
