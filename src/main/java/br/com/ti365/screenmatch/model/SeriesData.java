package br.com.ti365.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Integer seasons,
		@JsonAlias("imdbRating") String rating) {

}
