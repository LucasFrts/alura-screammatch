package br.com.lucasftrts.screammatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,

                          @JsonAlias("Episode") int number,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonAlias("Released") String releaseDate
) {
}
