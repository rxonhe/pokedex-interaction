package com.choinhet.pokedexinteraction.model.dtos;

public class HighlightedPokemonDto extends PokemonDto {
    private String highlight;

    public HighlightedPokemonDto(String name, String highlight) {
        super(name);
        this.highlight = highlight;
    }

    public static PokemonDto fromHighlightInfo(String name, String toHighlight){
        return new HighlightedPokemonDto(name, name.replace(toHighlight, "<pre>" + toHighlight + "</pre>"));
    }
    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
