package br.com.ti365.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodesData(@JsonAlias("Title") String title, @JsonAlias("Episode") Integer number,
		@JsonAlias("imdbRating") String rating, @JsonAlias("Released") String date) {

}
