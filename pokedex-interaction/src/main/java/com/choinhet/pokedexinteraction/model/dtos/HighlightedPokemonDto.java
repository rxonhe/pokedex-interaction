package com.choinhet.pokedexinteraction.model.dtos;

public class HighlightedPokemonDto extends PokemonDto {
    private String highlight;

    public HighlightedPokemonDto(String name, String toHighlight) {
        super(name);
        this.highlight = name.replace(toHighlight, "<pre>" + toHighlight + "</pre>");
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
