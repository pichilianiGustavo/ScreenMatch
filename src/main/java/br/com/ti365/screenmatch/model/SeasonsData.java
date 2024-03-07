package br.com.ti365.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record SeasonsData(@JsonAlias("Season") Integer number, @JsonAlias("Episodes") List<EpisodesData> episodes) {

}
