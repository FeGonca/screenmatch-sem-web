package br.com.projetc.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer numEpisodio,
                            @JsonAlias("imdbRating") Double avaliacao,
                            @JsonAlias("Released") String dtLancamento) {
}
