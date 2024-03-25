package br.com.ti365.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodes {

	private Integer season;
	private String title;
	private Integer episodeNumber;
	private Double rating;
	private LocalDate releaseDate;

	public Episodes(Integer seasonNumber, EpisodesData episodeData) {
		this.season = seasonNumber;
		this.title = episodeData.title();
		this.episodeNumber = episodeData.number();
		try {
			this.rating = Double.valueOf(episodeData.rating());
			this.releaseDate = LocalDate.parse(episodeData.date());
		} catch (NumberFormatException e) {
			this.rating = 0.0;
		} catch (DateTimeParseException e) {
			this.releaseDate = null;
		}
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Override
	public String toString() {
		return "season=" + this.season +
				", title=" + this.title + '\'' +
				", numberEpisode=" + this.episodeNumber +
				", rating=" + this.rating +
				", releaseDate=" + this.releaseDate;
	}
}
