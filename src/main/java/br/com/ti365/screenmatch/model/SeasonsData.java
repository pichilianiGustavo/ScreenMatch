package br.com.ti365.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonsData(@JsonAlias("Season") Integer number, @JsonAlias("Episodes") List<EpisodesData> episodes) {

}
