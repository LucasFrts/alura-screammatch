package br.com.lucasftrts.screammatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") int totalSeasons,
        @JsonAlias("imdbRating") String avaliacao
) {
}
